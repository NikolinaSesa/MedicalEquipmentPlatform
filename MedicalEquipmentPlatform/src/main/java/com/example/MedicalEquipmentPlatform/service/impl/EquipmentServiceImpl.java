package com.example.MedicalEquipmentPlatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.Equipment;
import com.example.MedicalEquipmentPlatform.repository.EquipmentRepository;
import com.example.MedicalEquipmentPlatform.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Equipment findById(Long id){
        return equipmentRepository.findById(id).get();
    }

    @Override
    public List<Equipment> findAll(){
        return equipmentRepository.findAll();
    }

    @Override
    public Boolean reduceEquipmentQuantity(Long id, Integer reservedQuantity){
        Equipment equipment = findById(id);
        equipment.setQuantity(equipment.getQuantity() - reservedQuantity);

        if(equipmentRepository.save(equipment) != null) return true;
        
        return false;
    }

    @Override
    public Boolean updateEquipmentQuantity(Long id, Integer quantity){
        Equipment equipment = findById(id);
        equipment.setQuantity(equipment.getQuantity() + quantity);

        if(equipmentRepository.save(equipment) != null) return true;

        return false;
    }
}
