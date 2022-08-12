package com.vti.rw41.FinalExam.dto.request;

import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.enumurations.RoleAcccount;
import com.vti.rw41.FinalExam.validation.Password;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class AccountRequest {
    @NotNull
    private String user_name;

    private String first_name;

    private String last_name;

    @Password
    private String password;

    private RoleAcccount role;

    private Department department;
}
