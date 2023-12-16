package com.example.MedicalEquipmentPlatform.service;

import java.util.List;

import com.example.MedicalEquipmentPlatform.model.Appointment;
import com.example.MedicalEquipmentPlatform.model.dto.AppointmentDTO;

public interface AppointmentService {

    public List<AppointmentDTO> findAvailableAppointments(Long companyId);

    public Boolean reserveAppointment(Appointment appointment);

}
