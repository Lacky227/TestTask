package com.testtask.authapi.controllers;

import com.testtask.authapi.dto.AuthResponseDTO;
import com.testtask.authapi.dto.MessageResponseDTO;
import com.testtask.authapi.dto.UserLoginRequestDTO;
import com.testtask.authapi.dto.UserRegisterRequestDTO;
import com.testtask.authapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<MessageResponseDTO> register(@Valid @RequestBody UserRegisterRequestDTO request) {
        MessageResponseDTO response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO request) {
        AuthResponseDTO response = userService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
