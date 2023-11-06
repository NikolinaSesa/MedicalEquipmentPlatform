package com.example.MedicalEquipmentPlatform.service;

import java.util.List;

import com.example.MedicalEquipmentPlatform.model.Company;

public interface CompanyService {
    
    public Company findById(Long id);

    public List<Company> findAll();
}
