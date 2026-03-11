package com.finbit.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FriendRequest(
        @NotBlank String name,
        @Email String email,
        String phone
) {}
