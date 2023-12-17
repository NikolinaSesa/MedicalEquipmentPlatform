package com.example.MedicalEquipmentPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MedicalEquipmentPlatform.model.ReservedEquipment;

public interface ReservedEquipmentRepository extends JpaRepository<ReservedEquipment, Long>{
    List<ReservedEquipment> findAll();
    
}
