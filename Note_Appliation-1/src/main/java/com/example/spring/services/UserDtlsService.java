package com.example.spring.services;


import com.example.spring.entity.UserDtls;
import com.example.spring.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDtlsService {

	@Autowired
    private UserRepository userRepository;

    public UserDtlsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDtls addUser(UserDtls user) {
  
        return userRepository.save(user);
    }

    public UserDtls updateUser(UserDtls user) {
        
        Optional<UserDtls> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            // Update the user fields
            UserDtls updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setAbout(user.getAbout());
            updatedUser.setRole(user.getRole());

            return userRepository.save(updatedUser);
        } else {
            throw new IllegalArgumentException("User not found with id: " + user.getId());
        }
    }

    public void removeUser(int userId) {
        
        Optional<UserDtls> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            
            userRepository.deleteById(userId);
        } else {
            
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }
}
