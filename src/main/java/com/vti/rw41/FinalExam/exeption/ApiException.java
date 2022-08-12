package com.vti.rw41.FinalExam.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiException extends RuntimeException {
    public final String messageCode;
}