package com.example.MedicalEquipmentPlatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.Company;
import com.example.MedicalEquipmentPlatform.repository.CompanyRepository;
import com.example.MedicalEquipmentPlatform.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Override
    public Company findById(Long id){
        return companyRepository.findById(id).get();
    }

    @Override
    public List<Company> findAll(){
        return companyRepository.findAll();
    }
    
}
