package com.vti.rw41.FinalExam.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.rw41.FinalExam.entity.Department;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AccountDto {

    private Integer id;

    @NonNull
    private String username;

    @NonNull
    private String fullname;

    @NonNull
    private String role;

    @NonNull
    private String  departmentName;


}
