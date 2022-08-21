package com.vti.rw41.FinalExam.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameNotUniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameNotUnique {

    String message() default "{error.username.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
