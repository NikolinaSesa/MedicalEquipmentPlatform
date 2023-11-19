package com.example.MedicalEquipmentPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "regular_users")
@PrimaryKeyJoinColumn(name = "user_id")
public class RegularUser extends User {

    @Column
    private String profession;

    @Column
    private String companyInformation;

    @Column 
    private Boolean isEnabled;

    public RegularUser(){
        super();
    }

    public RegularUser(String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role, String profession, String companyInformation){
        super(email, password, firstName, lastName, city, country, phoneNumber, role);
        this.profession = profession;
        this.companyInformation = companyInformation;
    }

    public RegularUser(Long id, String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role, String profession, String companyInformation){
        super(id, email, password, firstName, lastName, city, country, phoneNumber, role);
        this.profession = profession;
        this.companyInformation = companyInformation;
    }

    public RegularUser(Long id, String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role, String profession, String companyInformation, Boolean isEnabled){
        super(id, email, password, firstName, lastName, city, country, phoneNumber, role);
        this.profession = profession;
        this.companyInformation = companyInformation;
        this.isEnabled = isEnabled;
    }
    
}
