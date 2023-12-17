package com.example.MedicalEquipmentPlatform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.Appointment;
import com.example.MedicalEquipmentPlatform.model.Equipment;
import com.example.MedicalEquipmentPlatform.model.ReservedEquipment;
import com.example.MedicalEquipmentPlatform.model.dto.ReservedEquipmentDTO;
import com.example.MedicalEquipmentPlatform.repository.ReservedEquipmentRepository;
import com.example.MedicalEquipmentPlatform.service.ReservedEquipmentService;

@Service
public class ReservedEquipmentServiceImpl implements ReservedEquipmentService{
    
    @Autowired
    private ReservedEquipmentRepository reservedEquipmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReservedEquipment> reserveEquipment(List<ReservedEquipmentDTO> reservedEquipmentDTOs, Appointment appointment){
        List<ReservedEquipment> reservedEquipments = new ArrayList<>();
        for(ReservedEquipmentDTO reservedEquipmentDTO : reservedEquipmentDTOs){
            ReservedEquipment reservedEquipment = this.modelMapper.map(reservedEquipmentDTO, ReservedEquipment.class);
            Equipment equipment = this.modelMapper.map(reservedEquipmentDTO.getEquipmentDTO(), Equipment.class);

            reservedEquipment.setEquipment(equipment);
            reservedEquipment.setAppointment(appointment);

            reservedEquipments.add(reservedEquipmentRepository.save(reservedEquipment));
        }
        return reservedEquipments;
    }
}
