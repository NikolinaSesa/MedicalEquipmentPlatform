package com.example.MedicalEquipmentPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MedicalEquipmentPlatform.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{

    ConfirmationToken findByConfirmationToken(String confirmationToken);
    
}
