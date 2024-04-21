package br.com.devtops.microservice.article.exceptionhandlers;

import br.com.devtops.microservice.article.exceptions.UnexpectedException;
import br.com.devtops.microservice.article.exceptions.ValidationException;
import br.com.devtops.microservice.article.exceptions.protocols.CustomHttpException;
import br.com.devtops.microservice.article.external.protocols.CustomResponse;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final CustomResponse<CustomHttpException> handleCustomExceptions(Exception exception){
        CustomHttpException customException = new CustomHttpException(
            new UnexpectedException(
                    UnexpectedException.defaultMessage,
                    exception.getMessage()
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new CustomResponse<>(customException);
    }

    @ExceptionHandler(CustomHttpException.class)
    public final CustomResponse<CustomHttpException> handleCustomHttpExceptions(CustomHttpException exception) {
        return new CustomResponse<>(exception, exception.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        CustomHttpException customException = new CustomHttpException(
                new ValidationException(
                        ValidationException.convertFieldErrors(exception.getFieldErrors())
                ),
                HttpStatus.BAD_REQUEST
        );

        CustomResponse<Object> customResponse = new CustomResponse<>(customException);

        return customResponse.getResponseEntity();
    }

    @Override
    protected ResponseEntity<Object> handleMethodValidationException(MethodValidationException exception, @Nullable HttpHeaders headers, @Nullable HttpStatus status, @Nullable WebRequest request) {
        CustomHttpException customException = new CustomHttpException(
                new ValidationException(
                        ValidationException.convertParameterValidationResult(exception.getAllValidationResults())
                ),
                HttpStatus.BAD_REQUEST
        );

        CustomResponse<Object> customResponse = new CustomResponse<>(customException);

        return customResponse.getResponseEntity();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        CustomHttpException customException = new CustomHttpException(
                new ValidationException(
                        this.extractUnrecognizedField(exception.getMessage())
                ),
                HttpStatus.BAD_REQUEST
        );

        CustomResponse<Object> customResponse = new CustomResponse<>(customException);

        return customResponse.getResponseEntity();
    }

    private String extractUnrecognizedField(String errorMessage) {
        Pattern pattern = Pattern.compile("Unrecognized field \"(\\w+)\"");
        Matcher matcher = pattern.matcher(errorMessage);

        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
    }
}
