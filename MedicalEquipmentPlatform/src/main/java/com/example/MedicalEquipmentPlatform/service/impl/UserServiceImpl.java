package com.example.MedicalEquipmentPlatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.ConfirmationToken;
import com.example.MedicalEquipmentPlatform.model.User;
import com.example.MedicalEquipmentPlatform.model.dto.UserDTO;
import com.example.MedicalEquipmentPlatform.repository.ConfirmationTokenRepository;
import com.example.MedicalEquipmentPlatform.repository.UserRepository;
import com.example.MedicalEquipmentPlatform.service.EmailService;
import com.example.MedicalEquipmentPlatform.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserDTO userDTO){

        User existingUser = userRepository.findByEmail(userDTO.getEmail());

        if(existingUser == null){
            User user = new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getCity(), userDTO.getCountry(), userDTO.getPhoneNumber(), userDTO.getProfession(), userDTO.getCompanyInformation());
            User newUser = userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(newUser);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(newUser.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "+"http://localhost:8080/api/user/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailService.sendEmail(mailMessage);

            return newUser;
        } else{

            return null;
        }
        
    }

    @Override
    public User confirmEmail(String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null){
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setIsEnabled(true);
            return userRepository.save(user);
        }
        return null;
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
