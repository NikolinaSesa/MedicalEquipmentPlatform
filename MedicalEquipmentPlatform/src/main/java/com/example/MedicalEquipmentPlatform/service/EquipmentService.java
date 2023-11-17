package com.example.MedicalEquipmentPlatform.service;

import java.util.List;

import com.example.MedicalEquipmentPlatform.model.Equipment;

public interface EquipmentService {
    
    List<Equipment> findAll();

    Equipment findById(Long id);
}
