package com.testtask.dataapi.service.impl;

import com.testtask.dataapi.dto.TransformationRequest;
import com.testtask.dataapi.dto.TransformationResponse;
import com.testtask.dataapi.service.TransformService;
import org.springframework.stereotype.Service;

@Service
public class TransformServiceImpl implements TransformService {
    @Override
    public TransformationResponse transform(TransformationRequest request) {
        return TransformationResponse.builder()
                .result(request.getText().toUpperCase())
                .build();
    }
}
