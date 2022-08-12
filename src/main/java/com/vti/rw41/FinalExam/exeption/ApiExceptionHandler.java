package com.vti.rw41.FinalExam.exeption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {
    @Autowired
    MessageSource messageSource;

    private ResponseEntity<?> responseErrorMessages(Map<String, String> messages, HttpStatus status) {
        return new ResponseEntity<>(messages, status);
    }

    private Map.Entry<String, String> createFieldErrorMessage(FieldError fieldError) {
        return Map.entry(fieldError.getField(), Objects.requireNonNull(fieldError.getDefaultMessage()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException exception) {
        Map<String, String> messages = exception.getBindingResult()
                .getFieldErrors().stream().map(this::createFieldErrorMessage)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return responseErrorMessages(messages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, HttpServletRequest request) {
        String message = messageSource.getMessage(
                exception.getMessage(),
                new Object[]{},
                exception.getMessage(),
                request.getLocale()
        );
        return responseErrorMessages(Map.of("error", message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleLoginException(Exception exception, HttpServletRequest request) {
        String message = messageSource.getMessage(
                exception.getMessage(),
                new Object[]{},
                exception.getMessage(),
                request.getLocale()
        );
        return responseErrorMessages(Map.of("error", message), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException exception, HttpServletRequest request) {
        //    exception.printStackTrace();
        String message = messageSource.getMessage(
                exception.getMessageCode(),
                new Object[]{},
                exception.getMessageCode(),
                request.getLocale()
        );
        return responseErrorMessages(
                Map.of("error", message), HttpStatus.BAD_GATEWAY);
    }

}
