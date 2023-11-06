package com.example.MedicalEquipmentPlatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MedicalEquipmentPlatform.model.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
    
    Optional<Equipment> findById(Long id);

    List<Equipment> findAll();
}
