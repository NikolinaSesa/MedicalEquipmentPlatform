package com.example.MedicalEquipmentPlatform.service;

import com.example.MedicalEquipmentPlatform.model.RegularUser;
import com.example.MedicalEquipmentPlatform.model.dto.RegularUserDTO;

public interface RegularUserService {

    public RegularUser register(RegularUserDTO regularUserDTO);

    public RegularUser confirmEmail(String confirmationToken);
 
    public RegularUser findById(Long id);
}
