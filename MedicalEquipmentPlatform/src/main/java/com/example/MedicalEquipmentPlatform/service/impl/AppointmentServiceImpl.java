package com.example.MedicalEquipmentPlatform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.Appointment;
import com.example.MedicalEquipmentPlatform.model.dto.AppointmentDTO;
import com.example.MedicalEquipmentPlatform.repository.AppointmentRepository;
import com.example.MedicalEquipmentPlatform.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AppointmentDTO> findAvailableAppointments(Long companyId) {
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getCompanyAdmin().getCompany().getId() == companyId &&
                    appointment.getRegularUser() == null) {
                // appointmentDTOs.add(this.modelMapper.map(appointment, AppointmentDTO.class));
            }
        }

        return appointmentDTOs;
    }

    @Override
    public Boolean reserveAppointment(Appointment appointment) {
        // TODO
        return true;
    }
}
