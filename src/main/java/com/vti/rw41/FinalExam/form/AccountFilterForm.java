package com.vti.rw41.FinalExam.form;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountFilterForm {
    private Integer minId;
    private Integer maxId;
}
