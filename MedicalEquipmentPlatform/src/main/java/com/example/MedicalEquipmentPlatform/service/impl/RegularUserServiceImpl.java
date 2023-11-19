package com.example.MedicalEquipmentPlatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.ConfirmationToken;
import com.example.MedicalEquipmentPlatform.model.RegularUser;
import com.example.MedicalEquipmentPlatform.model.dto.RegularUserDTO;
import com.example.MedicalEquipmentPlatform.repository.ConfirmationTokenRepository;
import com.example.MedicalEquipmentPlatform.repository.RegularUserRepository;
import com.example.MedicalEquipmentPlatform.service.EmailService;
import com.example.MedicalEquipmentPlatform.service.RegularUserService;

@Service
public class RegularUserServiceImpl implements RegularUserService{
    
    private final RegularUserRepository regularUserRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;


    @Autowired
    public RegularUserServiceImpl(RegularUserRepository regularUserRepository){
        this.regularUserRepository = regularUserRepository;
    }

    @Override
    public RegularUser register(RegularUserDTO regularUserDTO){

        RegularUser existingUser = regularUserRepository.findByEmail(regularUserDTO.getEmail());

        if(existingUser == null){
            RegularUser regularUser = new RegularUser(regularUserDTO.getEmail(), regularUserDTO.getPassword(), regularUserDTO.getFirstName(), regularUserDTO.getLastName(), regularUserDTO.getCity(), regularUserDTO.getCountry(), regularUserDTO.getPhoneNumber(), "ROLE_USER", regularUserDTO.getProfession(), regularUserDTO.getCompanyInformation());
            RegularUser newRegularUser = regularUserRepository.save(regularUser);

            ConfirmationToken confirmationToken = new ConfirmationToken(newRegularUser);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(newRegularUser.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "+"http://localhost:8080/api/user/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailService.sendEmail(mailMessage);

            return newRegularUser;
        } else{

            return null;
        }
        
    }

    @Override
    public RegularUser confirmEmail(String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null){
            RegularUser regularUser = regularUserRepository.findByEmail(token.getUser().getEmail());
            regularUser.setIsEnabled(true);
            return regularUserRepository.save(regularUser);
        }
        return null;
    }

}
