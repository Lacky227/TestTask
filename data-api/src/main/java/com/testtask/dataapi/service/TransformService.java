package com.testtask.dataapi.service;

import com.testtask.dataapi.dto.TransformationRequest;
import com.testtask.dataapi.dto.TransformationResponse;

public interface TransformService {
    TransformationResponse transform(TransformationRequest request);
}
