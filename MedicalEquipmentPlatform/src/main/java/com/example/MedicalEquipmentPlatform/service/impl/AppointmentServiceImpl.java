package com.example.MedicalEquipmentPlatform.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.Appointment;
import com.example.MedicalEquipmentPlatform.model.ReservedEquipment;
import com.example.MedicalEquipmentPlatform.model.dto.AppointmentDTO;
import com.example.MedicalEquipmentPlatform.model.dto.CompanyAdminDTO;
import com.example.MedicalEquipmentPlatform.model.dto.EquipmentDTO;
import com.example.MedicalEquipmentPlatform.model.dto.RegularUserDTO;
import com.example.MedicalEquipmentPlatform.model.dto.ReservedEquipmentDTO;
import com.example.MedicalEquipmentPlatform.repository.AppointmentRepository;
import com.example.MedicalEquipmentPlatform.service.AppointmentService;
import com.example.MedicalEquipmentPlatform.service.EmailService;
import com.example.MedicalEquipmentPlatform.service.QRCodeService;
import com.example.MedicalEquipmentPlatform.service.RegularUserService;
import com.example.MedicalEquipmentPlatform.service.ReservedEquipmentService;
import com.google.zxing.WriterException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RegularUserService regularUserService;

    @Autowired
    private ReservedEquipmentService reservedEquipmentService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public List<AppointmentDTO> findAvailableAppointments(Long companyId) {
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getCompanyAdmin().getCompany().getId() == companyId &&
                    appointment.getRegularUser() == null && appointment.getDate().isAfter(LocalDate.now())) {
                AppointmentDTO appointmentDTO = this.modelMapper.map(appointment, AppointmentDTO.class);
                appointmentDTO
                        .setCompanyAdminDTO(this.modelMapper.map(appointment.getCompanyAdmin(), CompanyAdminDTO.class));
                appointmentDTOs.add(appointmentDTO);
            }
        }

        return appointmentDTOs;

    }

    @Override
    public AppointmentDTO reserveAppointment(AppointmentDTO appointmentDTO) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentDTO.getId());
        if (!appointment.isPresent())
            return null;

        appointment.get().setRegularUser(regularUserService.findById(appointmentDTO.getRegularUserDTO().getId()));

        Appointment updatedAppointment = appointmentRepository.save(appointment.get());

        reservedEquipmentService.reserveEquipment(appointmentDTO.getReservedEquipmentDTOs(), updatedAppointment);

        AppointmentDTO updatedAppointmentDTO = this.modelMapper.map(updatedAppointment, AppointmentDTO.class);
        updatedAppointmentDTO
                .setCompanyAdminDTO(this.modelMapper.map(updatedAppointment.getCompanyAdmin(), CompanyAdminDTO.class));

        updatedAppointmentDTO
                .setRegularUserDTO(this.modelMapper.map(updatedAppointment.getRegularUser(), RegularUserDTO.class));
        List<ReservedEquipmentDTO> reservedEquipmentDTOs = new ArrayList<>();
        for (ReservedEquipment reservedEquipment : updatedAppointment.getReservedEquipments()) {
            ReservedEquipmentDTO reservedEquipmentDTO = this.modelMapper.map(reservedEquipment,
                    ReservedEquipmentDTO.class);
            reservedEquipmentDTO
                    .setEquipmentDTO(this.modelMapper.map(reservedEquipment.getEquipment(), EquipmentDTO.class));
            reservedEquipmentDTOs.add(reservedEquipmentDTO);
        }
        updatedAppointmentDTO.setReservedEquipmentDTOs(reservedEquipmentDTOs);

        String QRCodeImagePath = "./MedicalEquipmentPlatform/src/main/resources/QRCodes/QR.png";
                //+ updatedAppointmentDTO.getId() + ".png";
        try {
            String reservationInfo = "Your name: %s.\nYour email: %s.\n\nAppointment details:\nDate: %s\nCompany admin: %s\n ";
            QRCodeService.generateQRCodeImage(
                    String.format(reservationInfo,
                            updatedAppointmentDTO.getRegularUserDTO().getLastName()
                                    + updatedAppointmentDTO.getRegularUserDTO().getLastName(),
                            updatedAppointmentDTO.getRegularUserDTO().getEmail(),
                            updatedAppointmentDTO.getDate().toString(),
                            updatedAppointmentDTO.getCompanyAdminDTO().getFirstName()
                                    + updatedAppointmentDTO.getCompanyAdminDTO().getLastName()),
                    250, 250, QRCodeImagePath);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(updatedAppointmentDTO.getRegularUserDTO().getEmail());
        mailMessage.setSubject("Reservation info.");
        mailMessage.setText("Your reservation...");
        emailService.sendEmail(mailMessage);

        return updatedAppointmentDTO;
    }

    @Override
    public List<AppointmentDTO> findReservedAppointmentsByUser(Long userId) {
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getRegularUser() != null && appointment.getRegularUser().getId() == userId
                    && appointment.getDate().isAfter(LocalDate.now())) {
                AppointmentDTO appointmentDTO = this.modelMapper.map(appointment, AppointmentDTO.class);
                appointmentDTO
                        .setCompanyAdminDTO(this.modelMapper.map(appointment.getCompanyAdmin(), CompanyAdminDTO.class));

                appointmentDTO
                        .setRegularUserDTO(this.modelMapper.map(appointment.getRegularUser(), RegularUserDTO.class));
                List<ReservedEquipmentDTO> reservedEquipmentDTOs = new ArrayList<>();
                for (ReservedEquipment reservedEquipment : appointment.getReservedEquipments()) {
                    ReservedEquipmentDTO reservedEquipmentDTO = this.modelMapper.map(reservedEquipment,
                            ReservedEquipmentDTO.class);
                    reservedEquipmentDTO.setEquipmentDTO(
                            this.modelMapper.map(reservedEquipment.getEquipment(), EquipmentDTO.class));
                    reservedEquipmentDTOs.add(reservedEquipmentDTO);
                }
                appointmentDTO.setReservedEquipmentDTOs(reservedEquipmentDTOs);
                appointmentDTOs.add(appointmentDTO);
            }
        }
        return appointmentDTOs;
    }

    @Override
    public AppointmentDTO findById(Long id) {
        if (appointmentRepository.findById(id) != null) {
            Appointment appointment = this.modelMapper.map(appointmentRepository.findById(id), Appointment.class);
            AppointmentDTO appointmentDTO = this.modelMapper.map(appointment, AppointmentDTO.class);
            appointmentDTO
                    .setCompanyAdminDTO(this.modelMapper.map(appointment.getCompanyAdmin(), CompanyAdminDTO.class));

            appointmentDTO.setRegularUserDTO(this.modelMapper.map(appointment.getRegularUser(), RegularUserDTO.class));
            List<ReservedEquipmentDTO> reservedEquipmentDTOs = new ArrayList<>();
            for (ReservedEquipment reservedEquipment : appointment.getReservedEquipments()) {
                ReservedEquipmentDTO reservedEquipmentDTO = this.modelMapper.map(reservedEquipment,
                        ReservedEquipmentDTO.class);
                reservedEquipmentDTO
                        .setEquipmentDTO(this.modelMapper.map(reservedEquipment.getEquipment(), EquipmentDTO.class));
                reservedEquipmentDTOs.add(reservedEquipmentDTO);
            }
            appointmentDTO.setReservedEquipmentDTOs(reservedEquipmentDTOs);
            return appointmentDTO;
        }
        return null;
    }

    @Override
    public AppointmentDTO quitAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = findAppointment(appointmentDTO.getId());

        LocalDate date = LocalDate.now().plusDays(1);

        if (date.equals(appointmentDTO.getDate())) {
            regularUserService.updatePenalNumber(appointmentDTO.getRegularUserDTO().getId(), 2);
        } else {
            regularUserService.updatePenalNumber(appointmentDTO.getRegularUserDTO().getId(), 1);
        }
        appointment.setRegularUser(null);
        reservedEquipmentService.quitReservation(appointmentDTO.getReservedEquipmentDTOs());
        appointment.setReservedEquipments(null);

        Appointment freededAppointment = appointmentRepository.save(appointment);

        AppointmentDTO freededAppointmentDTO = this.modelMapper.map(freededAppointment, AppointmentDTO.class);
        freededAppointmentDTO
                .setCompanyAdminDTO(this.modelMapper.map(freededAppointment.getCompanyAdmin(), CompanyAdminDTO.class));

        return freededAppointmentDTO;
    }

    @Override
    public Appointment findAppointment(Long id) {
        Appointment appointment = this.modelMapper.map(appointmentRepository.findById(id), Appointment.class);
        return appointment;
    }
}
