package com.testtask.authapi.service.impl;

import com.testtask.authapi.dto.AuthResponseDTO;
import com.testtask.authapi.dto.MessageResponseDTO;
import com.testtask.authapi.dto.UserLoginRequestDTO;
import com.testtask.authapi.dto.UserRegisterRequestDTO;
import com.testtask.authapi.exceptions.InvalidCredentialsException;
import com.testtask.authapi.exceptions.UserNotFoundByEmailException;
import com.testtask.authapi.exceptions.UserWithEmailAlreadyExistsException;
import com.testtask.authapi.models.User;
import com.testtask.authapi.repository.UserRepository;
import com.testtask.authapi.service.UserService;
import com.testtask.authapi.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public MessageResponseDTO register(UserRegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserWithEmailAlreadyExistsException("Email already exists");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return new MessageResponseDTO("Register successfully");
    }

    @Override
    @Transactional
    public AuthResponseDTO login(UserLoginRequestDTO request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new UserNotFoundByEmailException("User not found");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return new AuthResponseDTO(jwtUtils.generateToken(user.getEmail()));
    }
}
