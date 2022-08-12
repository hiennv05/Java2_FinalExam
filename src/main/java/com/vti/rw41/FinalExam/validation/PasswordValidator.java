package com.vti.rw41.FinalExam.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) {
            return false;
        }
        if (password.length() < 5 || password.length() > 12) {
            return false;
        }
        if (!Pattern.matches(".*[A-Z].*", password)) {
            return false;
        }
        return true;
    }
}
