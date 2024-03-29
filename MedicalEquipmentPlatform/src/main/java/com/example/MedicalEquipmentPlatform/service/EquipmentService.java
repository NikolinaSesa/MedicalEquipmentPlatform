package com.example.MedicalEquipmentPlatform.service;

import java.util.List;

import com.example.MedicalEquipmentPlatform.model.Equipment;

public interface EquipmentService {
    
    List<Equipment> findAll();

    Equipment findById(Long id);

    Boolean reduceEquipmentQuantity(Long id, Integer reservedQunatity);

    Boolean updateEquipmentQuantity(Long id, Integer quantity);
}
