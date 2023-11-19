package com.example.MedicalEquipmentPlatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MedicalEquipmentPlatform.model.RegularUser;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Long>{
    
    Optional<RegularUser> findById(Long id);

    List<RegularUser> findAll();

    RegularUser findByEmail(String email);
}
