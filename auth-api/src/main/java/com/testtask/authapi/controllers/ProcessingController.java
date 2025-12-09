package com.testtask.authapi.controllers;

import com.testtask.authapi.dto.TransformationRequest;
import com.testtask.authapi.dto.TransformationResponse;
import com.testtask.authapi.service.ProcessingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessingController {
    private final ProcessingService processingService;

    @PostMapping("/process")
    ResponseEntity<TransformationResponse> process(@Valid @RequestBody TransformationRequest transformationRequest) {
        TransformationResponse transformationResponse = processingService.process(transformationRequest);
        return ResponseEntity.ok(transformationResponse);
    }
}
