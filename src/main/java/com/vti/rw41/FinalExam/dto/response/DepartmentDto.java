package com.vti.rw41.FinalExam.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private Integer id;

    @NonNull
    private String name;

    private String type;

    private Integer total_member;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;

    private List<AccountDTO> accounts;
    @Data
    @NoArgsConstructor
    static class AccountDTO {
        @JsonProperty("accountID")
        private Integer id;

        @JsonProperty("username")
        private String user_name;
    }

}

