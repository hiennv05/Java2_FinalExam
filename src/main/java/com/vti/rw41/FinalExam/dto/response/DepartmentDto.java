package com.vti.rw41.FinalExam.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private Integer id;

    @NonNull
    private String name;

    private String type;

    private Integer total_member;

    private LocalDate create_date;

    private List<AccountDTO> accounts;
    @Data
    @NoArgsConstructor
    static class AccountDTO{
        private Integer id;
        private String user_name;
    }

}

