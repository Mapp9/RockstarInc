package com.rockstarinc.backend.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rockstarinc.backend.entity.User;
import com.rockstarinc.backend.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registUser(User user) throws Exception {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("El nombre de usuario ya esta en uso");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
