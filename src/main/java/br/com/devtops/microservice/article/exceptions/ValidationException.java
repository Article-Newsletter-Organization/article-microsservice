package br.com.devtops.microservice.article.exceptions;

import br.com.devtops.microservice.article.exceptions.protocols.CustomException;
import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class ValidationException extends CustomException {
    static public String defaultName = "VALIDATION_EXCEPTION";
    static public String defaultMessage = "The data received are not valid.";

    private @Nullable List<Field> fields;

    public ValidationException(String message) {
        super(defaultName, message);
    }

    public ValidationException(String message, @Nullable List<Field> fields) {
        super(defaultName, message);
        this.fields = fields;
    }

    public ValidationException(@Nullable List<Field> fields) {
        super(defaultName, defaultMessage);
        this.fields = fields;
    }

    @Getter
    @AllArgsConstructor
    static public class Field {
        private String target;
        private String message;
    }

    public static List<Field> convertParameterValidationResult(List<ParameterValidationResult> violations) {
        List<Field> fields = new ArrayList<>();
        for (ParameterValidationResult violation : violations) {
            fields.add(new Field(violation.getMethodParameter().getParameterName(), violation.getResolvableErrors().toString()));
        }
        return fields;
    }

    public static List<Field> convertFieldErrors(List<FieldError> errors) {
        List<Field> fields = new ArrayList<>();
        for (FieldError error : errors) {
            fields.add(new Field(error.getField(), error.getDefaultMessage()));
        }
        return fields;
    }
}
