package com.vti.rw41.FinalExam.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordRequest {
    @NotNull
    @NotBlank
    private String otp;

    @NotNull
    private String password;

    @Email
    @NotNull
    private String userName;

}
