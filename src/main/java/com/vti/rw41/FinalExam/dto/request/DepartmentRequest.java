package com.vti.rw41.FinalExam.dto.request;

import com.vti.rw41.FinalExam.enumurations.RoleDepartment;
import lombok.Data;

import java.time.LocalDate;
@Data

public class DepartmentRequest {
    private Integer id;

    private String name;

    private String type;

    private Integer total_member;

    private LocalDate create_date;
}
