package com.finbit.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank String fullName,
        @Email @NotBlank String email,
        String phone,
        @NotBlank String password
) {}
