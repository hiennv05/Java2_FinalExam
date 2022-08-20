package com.vti.rw41.FinalExam.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.rw41.FinalExam.enumurations.RoleDepartment;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDto {
  //  private Integer id;

    @NonNull
    private String name;

    private RoleDepartment type;

    private Integer totalMember;

//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate createDate;
//
//    private List<AccountDTO> accounts;
//    @Data
//    @NoArgsConstructor
//    static class AccountDTO {
//        @JsonProperty("accountID")
//        private Integer id;
//
//        @JsonProperty("username")
//        private String username;
//    }

}

