package com.vti.rw41.FinalExam.validation;

import com.vti.rw41.FinalExam.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameNotUniqueValidator implements ConstraintValidator<UserNameNotUnique, String> {
    @Autowired
    IAccountRepository repository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return repository.isUserNameNotExists(name);
    }
}
