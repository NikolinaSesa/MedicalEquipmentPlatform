package com.example.MedicalEquipmentPlatform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalEquipmentPlatform.model.Company;
import com.example.MedicalEquipmentPlatform.model.Equipment;
import com.example.MedicalEquipmentPlatform.model.dto.AddressDTO;
import com.example.MedicalEquipmentPlatform.model.dto.CompanyDTO;
import com.example.MedicalEquipmentPlatform.model.dto.EquipmentDTO;
import com.example.MedicalEquipmentPlatform.service.CompanyService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {
    
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> findById(@PathVariable Long id){
        
        Company company = companyService.findById(id);

        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AddressDTO addressDTO = new AddressDTO(company.getAddress().getId(), company.getAddress().getAddress(), company.getAddress().getCity(), company.getAddress().getZipCode(), company.getAddress().getCountry());

        List<EquipmentDTO> equipmentDTOs = new ArrayList<EquipmentDTO>();
        for(Equipment equipment : company.getEquipment()){
            EquipmentDTO equipmentDTO = new EquipmentDTO(equipment.getId(), equipment.getName(), equipment.getQuantity());
            equipmentDTOs.add(equipmentDTO);

        }
        CompanyDTO companyDTO = new CompanyDTO(company.getId(), company.getCompanyName(), company.getDescription(), company.getAverageRating(), addressDTO, equipmentDTOs);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDTO>> findAll(){

        List<Company> companies = companyService.findAll();

        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for(Company company : companies){

            AddressDTO addressDTO = new AddressDTO(company.getAddress().getId(), company.getAddress().getAddress(), company.getAddress().getCity(), company.getAddress().getZipCode(), company.getAddress().getCountry());

            List<EquipmentDTO> equipmentDTOs = new ArrayList<EquipmentDTO>();
            for(Equipment equipment : company.getEquipment()){
                EquipmentDTO equipmentDTO = new EquipmentDTO(equipment.getId(), equipment.getName(), equipment.getQuantity());
                equipmentDTOs.add(equipmentDTO);
            }
            CompanyDTO companyDTO = new CompanyDTO(company.getId(), company.getCompanyName(), company.getDescription(), company.getAverageRating(), addressDTO, equipmentDTOs);
            companyDTOs.add(companyDTO);
        }
        return new ResponseEntity<>(companyDTOs, HttpStatus.OK);
    }

}
