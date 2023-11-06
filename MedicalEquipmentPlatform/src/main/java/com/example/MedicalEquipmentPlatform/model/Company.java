package com.example.MedicalEquipmentPlatform.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String companyName;

    @Column
    private String description;

    @Column
    private Double averageRating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "company")
    private List<Equipment> equipments;
    
    public Company(){}

    public Company(String companyName, String description, Double averageRating, Address address){
        this.companyName = companyName;
        this.description = description;
        this.averageRating = averageRating;
        this.address = address;
    }

    public Company(Long id, String companyName, String description, Double averageRating, Address address, List<Equipment> equipments){
        this.id = id;
        this.companyName = companyName;
        this.description = description;
        this.averageRating = averageRating;
        this.address = address;
        this.equipments = equipments;
    }
}
