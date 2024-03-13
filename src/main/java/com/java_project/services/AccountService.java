package com.java_project.services;

import lombok.RequiredArgsConstructor;
import com.java_project.configuration.security.JwtService;
import com.java_project.dto.account.AuthResponseDto;
import com.java_project.dto.account.LoginDto;
import com.java_project.dto.account.UserRegistrationDto;
import com.java_project.entities.RoleEntity;
import com.java_project.entities.UserEntity;
import com.java_project.entities.UserRoleEntity;
import com.java_project.repositories.RoleRepository;
import com.java_project.repositories.UserRepository;
import com.java_project.repositories.UserRoleRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDto login(LoginDto request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var isValid = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!isValid) {
            throw new UsernameNotFoundException("User not found");
        }
        var jwtToekn = jwtService.generateAccessToken(user);
        return AuthResponseDto.builder()
                .token(jwtToekn)
                .build();
    }

    public void registerUser(UserRegistrationDto registrationDto) {
    // Створюємо екземпляр користувача
    UserEntity user = new UserEntity();
    user.setFirstName(registrationDto.getFirstName());
    user.setLastName(registrationDto.getLastName());
    user.setEmail(registrationDto.getEmail());
    user.setPhone(registrationDto.getPhone());
    user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

    userRepository.save(user);

    UserRoleEntity userRole = new UserRoleEntity();
    userRole.setUser(user); 
    RoleEntity role = roleRepository.findByName("user"); 
    userRole.setRole(role);
    userRoleRepository.save(userRole); 
    }
}