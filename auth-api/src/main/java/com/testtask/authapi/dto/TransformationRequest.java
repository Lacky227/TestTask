package com.testtask.authapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransformationRequest {
    @NotNull(message = "Text cannot be empty")
    private String text;
}
