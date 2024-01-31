package com.example.MedicalEquipmentPlatform.service;

import java.util.List;

import com.example.MedicalEquipmentPlatform.model.Appointment;
import com.example.MedicalEquipmentPlatform.model.dto.AppointmentDTO;

public interface AppointmentService {

    public Appointment findAppointment(Long id);

    public AppointmentDTO findById(Long id);

    public List<AppointmentDTO> findAvailableAppointments(Long companyId);

    public AppointmentDTO reserveAppointment(AppointmentDTO appointmentDTO);

    public List<AppointmentDTO> findReservedAppointmentsByUser(Long userId);

    public AppointmentDTO quitAppointment(AppointmentDTO appointmentDTO);
}
