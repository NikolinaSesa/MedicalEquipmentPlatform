package com.example.MedicalEquipmentPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column 
    private String password;

    @Column
    private String firstName;

    @Column 
    private String lastName;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String phoneNumber;

    @Column
    private String profession;

    @Column
    private String companyInformation;

    public User(){}

    public User(Long id, String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String profession, String companyInformation){
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.companyInformation = companyInformation;
    }
}
