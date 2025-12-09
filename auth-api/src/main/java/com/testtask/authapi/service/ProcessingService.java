package com.testtask.authapi.service;

import com.testtask.authapi.dto.TransformationRequest;
import com.testtask.authapi.dto.TransformationResponse;
import com.testtask.authapi.models.ProcessingLog;
import com.testtask.authapi.models.User;
import com.testtask.authapi.repository.ProcessingRepository;
import com.testtask.authapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ProcessingService {
    private final RestClient restClient = RestClient.create();
    @Value("${app.internal.token}")
    private String internalToken;
    private final UserRepository userRepository;
    private final ProcessingRepository processingRepository;

    @Transactional
    public TransformationResponse process(TransformationRequest request) {
        TransformationResponse response = restClient.post()
                .uri("http://data-api:8081/api/transform")
                .header("X-Internal-Token", internalToken)
                .body(request)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(TransformationResponse.class);
        User user = getCurrentUser();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        ProcessingLog processingLog = ProcessingLog.builder()
                .user(user)
                .inputText(request.getText())
                .outputText(response.getResult())
                .build();
        processingRepository.save(processingLog);
        return response;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Authentication object is null");
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElse(null);
    }
}
