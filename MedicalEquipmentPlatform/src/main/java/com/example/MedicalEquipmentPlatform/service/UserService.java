package com.example.MedicalEquipmentPlatform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MedicalEquipmentPlatform.model.User;
import com.example.MedicalEquipmentPlatform.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
  
        Optional<User> user = userRepository.findByEmail(email); 
  
        return user.map(UserInfoDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email)); 
    } 

    public User findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email); 

        if (user.isPresent()){
            return new User(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getFirstName(), user.get().getLastName(), user.get().getCity(), user.get().getCountry(), user.get().getPhoneNumber(), user.get().getRole());
        }else{
            return null;
        }
    }
    
}
