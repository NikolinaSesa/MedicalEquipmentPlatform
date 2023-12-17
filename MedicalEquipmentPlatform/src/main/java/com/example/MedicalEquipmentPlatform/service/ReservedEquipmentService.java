package com.example.MedicalEquipmentPlatform.service;

import java.util.List;

import com.example.MedicalEquipmentPlatform.model.Appointment;
import com.example.MedicalEquipmentPlatform.model.ReservedEquipment;
import com.example.MedicalEquipmentPlatform.model.dto.ReservedEquipmentDTO;

public interface ReservedEquipmentService {
    
    List<ReservedEquipment> reserveEquipment(List<ReservedEquipmentDTO> reservedEquipmentDTOs, Appointment appointment);
}
