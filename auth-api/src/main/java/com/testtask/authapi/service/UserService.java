package com.testtask.authapi.service;

import com.testtask.authapi.dto.AuthResponseDTO;
import com.testtask.authapi.dto.MessageResponseDTO;
import com.testtask.authapi.dto.UserLoginRequestDTO;
import com.testtask.authapi.dto.UserRegisterRequestDTO;

public interface UserService {
    MessageResponseDTO register(UserRegisterRequestDTO request);
    AuthResponseDTO login(UserLoginRequestDTO request);
}
