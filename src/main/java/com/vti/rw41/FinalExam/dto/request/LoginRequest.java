package com.vti.rw41.FinalExam.dto.request;

import com.vti.rw41.FinalExam.validation.Password;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull
    @NotBlank
    private String username;

    @NotBlank
    @NotNull

    private String password;
}
