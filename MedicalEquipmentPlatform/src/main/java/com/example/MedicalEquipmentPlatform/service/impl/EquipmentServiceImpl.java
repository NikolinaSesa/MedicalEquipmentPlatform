package com.example.MedicalEquipmentPlatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MedicalEquipmentPlatform.model.Equipment;
import com.example.MedicalEquipmentPlatform.repository.EquipmentRepository;
import com.example.MedicalEquipmentPlatform.service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {
    
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository){
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment findById(Long id){
        return equipmentRepository.findById(id).get();
    }

    @Override
    public List<Equipment> findAll(){
        return equipmentRepository.findAll();
    }
}
