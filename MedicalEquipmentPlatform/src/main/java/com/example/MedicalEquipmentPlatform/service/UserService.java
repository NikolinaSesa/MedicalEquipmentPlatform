package com.example.MedicalEquipmentPlatform.service;

import com.example.MedicalEquipmentPlatform.model.User;
import com.example.MedicalEquipmentPlatform.model.dto.UserDTO;

public interface UserService {

    public User create(UserDTO userDTO);

    public User confirmEmail(String confirmationToken);

    public User findByEmailAndPassword(String email, String password);
    
}
