package com.example.MedicalEquipmentPlatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.User;
import com.example.MedicalEquipmentPlatform.model.dto.UserDTO;
import com.example.MedicalEquipmentPlatform.repository.UserRepository;
import com.example.MedicalEquipmentPlatform.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserDTO userDTO){
        User user = new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getCity(), userDTO.getCountry(), userDTO.getPhoneNumber(), userDTO.getProfession(), userDTO.getCompanyInformation());
        User newUser = userRepository.save(user);

        return newUser;
    }

    @Override
    public User findByEmailAndPassword(String email, String password){

        for(User user : userRepository.findAll()){
            if(user.getEmail() == email && user.getPassword() == password){
                return user;
            }
        }
        return null;
    }
}
