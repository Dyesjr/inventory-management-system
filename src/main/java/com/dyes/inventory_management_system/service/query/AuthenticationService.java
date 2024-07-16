package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.dto.LoginUserDto;
import com.dyes.inventory_management_system.dto.RegisterUserDto;
import com.dyes.inventory_management_system.model.User;
import com.dyes.inventory_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//public class AuthenticationService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public AuthenticationService(UserRepository userRepository,
//                                 PasswordEncoder passwordEncoder,
//                                 AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//    }
//
//    public User signup(RegisterUserDto input){
//        User user = new User();
//        user.setUserName(input.getUserName());
//        user.setEmail(input.getEmail());
//        user.setPassword(passwordEncoder.encode(input.getPassword()));
//
//        return userRepository.save(user);
//    }
//
//    public User authenticate(LoginUserDto input){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getEmail(),
//                        input.getPassword()
//                )
//        );
//
//        return userRepository.findByEmail(input.getEmail())
//                .orElseThrow();
//    }
//}
