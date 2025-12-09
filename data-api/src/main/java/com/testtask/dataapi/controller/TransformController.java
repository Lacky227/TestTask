package com.testtask.dataapi.controller;

import com.testtask.dataapi.dto.TransformationRequest;
import com.testtask.dataapi.dto.TransformationResponse;
import com.testtask.dataapi.service.TransformService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransformController {
    private final TransformService transformService;
    @Value("${app.internal.token}")
    private String internalToken;

    @PostMapping("/transform")
    public ResponseEntity<TransformationResponse> transform(
            @RequestHeader("X-Internal-Token") String tokenHeader,
            @RequestBody TransformationRequest transformationRequest)
    {
        if (!internalToken.equals(tokenHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(transformService.transform(transformationRequest));
    }
}
