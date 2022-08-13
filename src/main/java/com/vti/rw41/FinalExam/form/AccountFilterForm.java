package com.vti.rw41.FinalExam.form;

import com.vti.rw41.FinalExam.enumurations.RoleAcccount;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountFilterForm {
    private Integer minId;
    private Integer maxId;
    private RoleAcccount role;
    private String departmentName;
}
