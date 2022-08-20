package com.vti.rw41.FinalExam.dto.request;
import com.vti.rw41.FinalExam.enumurations.RoleDepartment;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data

public class DepartmentRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private RoleDepartment type;

    private Integer totalMember;

    @CreationTimestamp
    private LocalDate createDate;

}
