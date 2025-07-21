package com.jobportal.dto;

import com.jobportal.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @jakarta.validation.constraints.NotBlank
    private String name;

    @jakarta.validation.constraints.NotBlank
    @jakarta.validation.constraints.Email
    private String email;

    @jakarta.validation.constraints.NotBlank
    @Size(min = 6)
    private String password;

    @jakarta.validation.constraints.NotNull
    private Role role;
}

