package com.example.MedicalEquipmentPlatform.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    PasswordEncoder encoder;

    @Autowired
    public RegularUserServiceImpl(RegularUserRepository regularUserRepository){
        this.regularUserRepository = regularUserRepository;
    }

    @Override
    public RegularUser register(RegularUserDTO regularUserDTO){

        RegularUser existingUser = regularUserRepository.findByEmail(regularUserDTO.getEmail());

        if(existingUser == null){
            RegularUser regularUser = new RegularUser(regularUserDTO.getEmail(), regularUserDTO.getPassword(), regularUserDTO.getFirstName(), regularUserDTO.getLastName(), regularUserDTO.getCity(), regularUserDTO.getCountry(), regularUserDTO.getPhoneNumber(), "ROLE_USER", regularUserDTO.getProfession(), regularUserDTO.getCompanyInformation());
            regularUser.setPassword(encoder.encode(regularUserDTO.getPassword()));
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

    @Override
    public RegularUser findById(Long id){
        Optional<RegularUser> regularUser = regularUserRepository.findById(id);
        if(!regularUser.isPresent()) return null;

        return new RegularUser(regularUser.get().getId(),  regularUser.get().getEmail(), regularUser.get().getPassword(), regularUser.get().getFirstName(), regularUser.get().getLastName(), regularUser.get().getCity(), regularUser.get().getCountry(), regularUser.get().getPhoneNumber(), regularUser.get().getRole(), regularUser.get().getProfession(), regularUser.get().getCompanyInformation());

    }

}
