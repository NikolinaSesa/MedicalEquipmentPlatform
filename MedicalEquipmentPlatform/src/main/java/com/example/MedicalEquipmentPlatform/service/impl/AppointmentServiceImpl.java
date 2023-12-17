package com.example.MedicalEquipmentPlatform.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.MedicalEquipmentPlatform.service.RegularUserService;
import com.example.MedicalEquipmentPlatform.service.ReservedEquipmentService;

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

    @Override
    public List<AppointmentDTO> findAvailableAppointments(Long companyId) {
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getCompanyAdmin().getCompany().getId() == companyId &&
                    appointment.getRegularUser() == null && appointment.getDate().isAfter(LocalDate.now())) {
                AppointmentDTO appointmentDTO = this.modelMapper.map(appointment, AppointmentDTO.class);
                appointmentDTO.setCompanyAdminDTO(this.modelMapper.map(appointment.getCompanyAdmin(), CompanyAdminDTO.class));
                appointmentDTOs.add(appointmentDTO);
            }
        }

        return appointmentDTOs;
        
    }

    @Override
    public AppointmentDTO reserveAppointment(AppointmentDTO appointmentDTO) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentDTO.getId());
        if(!appointment.isPresent()) return null;
    
        appointment.get().setRegularUser(regularUserService.findById(appointmentDTO.getRegularUserDTO().getId()));
        appointment.get().setReservedEquipments(reservedEquipmentService.reserveEquipment(appointmentDTO.getReservedEquipmentDTOs(), appointment.get()));

        Appointment updatedAppointment = appointmentRepository.save(appointment.get());
        AppointmentDTO updatedAppointmentDTO = this.modelMapper.map(updatedAppointment, AppointmentDTO.class);
        updatedAppointmentDTO.setCompanyAdminDTO(this.modelMapper.map(updatedAppointment.getCompanyAdmin(), CompanyAdminDTO.class));
        
        updatedAppointmentDTO.setRegularUserDTO(this.modelMapper.map(updatedAppointment.getRegularUser(), RegularUserDTO.class));
        List<ReservedEquipmentDTO> reservedEquipmentDTOs = new ArrayList<>();
        for(ReservedEquipment reservedEquipment : updatedAppointment.getReservedEquipments()){
            ReservedEquipmentDTO reservedEquipmentDTO = this.modelMapper.map(reservedEquipment, ReservedEquipmentDTO.class);
            reservedEquipmentDTO.setEquipmentDTO(this.modelMapper.map(reservedEquipment.getEquipment(), EquipmentDTO.class));
            reservedEquipmentDTOs.add(reservedEquipmentDTO);
        }
        updatedAppointmentDTO.setReservedEquipmentDTOs(reservedEquipmentDTOs);

        return updatedAppointmentDTO;
    }

    @Override
    public List<AppointmentDTO> findReservedAppointmentsByUser(Long userId){
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for(Appointment appointment : appointmentRepository.findAll()){
            if(appointment.getRegularUser().getId() == userId && appointment.getDate().isAfter(LocalDate.now())){
                AppointmentDTO appointmentDTO = this.modelMapper.map(appointment, AppointmentDTO.class);
                appointmentDTO.setCompanyAdminDTO(this.modelMapper.map(appointment.getCompanyAdmin(), CompanyAdminDTO.class));
        
                appointmentDTO.setRegularUserDTO(this.modelMapper.map(appointment.getRegularUser(), RegularUserDTO.class));
                List<ReservedEquipmentDTO> reservedEquipmentDTOs = new ArrayList<>();
                for(ReservedEquipment reservedEquipment : appointment.getReservedEquipments()){
                    ReservedEquipmentDTO reservedEquipmentDTO = this.modelMapper.map(reservedEquipment, ReservedEquipmentDTO.class);
                    reservedEquipmentDTO.setEquipmentDTO(this.modelMapper.map(reservedEquipment.getEquipment(), EquipmentDTO.class));
                    reservedEquipmentDTOs.add(reservedEquipmentDTO);
                }
                appointmentDTO.setReservedEquipmentDTOs(reservedEquipmentDTOs);
                appointmentDTOs.add(appointmentDTO);
            }
        }
        return appointmentDTOs;
    }
}
