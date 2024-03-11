package com.java_project.controller;

import lombok.RequiredArgsConstructor;
import com.java_project.dto.account.AuthResponseDto;
import com.java_project.dto.account.LoginDto;
import com.java_project.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginDto dto) {
        try {
            var auth = service.login(dto);
            return ResponseEntity.ok(auth);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body("Невірно введені дані! Спробуйте ще раз!");
        }
    }
}