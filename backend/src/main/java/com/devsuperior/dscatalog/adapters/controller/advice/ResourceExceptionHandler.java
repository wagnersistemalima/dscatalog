package com.devsuperior.dscatalog.adapters.controller.advice;

import com.devsuperior.dscatalog.adapters.controller.advice.entity.ValidationErrorsOutputResponse;
import com.devsuperior.dscatalog.adapters.controller.entity.DefaultResponse;
import com.devsuperior.dscatalog.adapters.controller.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DefaultResponse<ValidationErrorsOutputResponse> handlerResourceNotFoundException(
            ResourceNotFoundException exception, HttpServletRequest httpServletRequest
    ) {
        ValidationErrorsOutputResponse validationErrors = new ValidationErrorsOutputResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                exception.getMessage(),
                httpServletRequest.getRequestURI()
        );

        return new DefaultResponse<>(validationErrors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultResponse<ValidationErrorsOutputResponse> handlerValidationError(
            MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors, httpServletRequest);
    }

    private DefaultResponse<ValidationErrorsOutputResponse> buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors, HttpServletRequest httpServletRequest) {

        ValidationErrorsOutputResponse validationErrors = new ValidationErrorsOutputResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "Validation",
                httpServletRequest.getRequestURI()
        );

        globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));

        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return new DefaultResponse<>(validationErrors);
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
