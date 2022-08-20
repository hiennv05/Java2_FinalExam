package com.vti.rw41.FinalExam.dto.request;


import com.vti.rw41.FinalExam.validation.Password;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AccountRequest {

    @NotNull
    private String username;

    private String firstname;

    private String lastname;

    private String role;

    private Integer departmentId;
}
