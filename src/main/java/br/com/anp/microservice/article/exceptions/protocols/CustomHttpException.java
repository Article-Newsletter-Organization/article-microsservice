package br.com.anp.microservice.article.exceptions.protocols;

import br.com.anp.microservice.article.exceptions.UnexpectedException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"suppressed", "localizedMessage", "cause", "stackTrace"})
public class CustomHttpException extends RuntimeException {
    final private String message;
    final private HttpStatus status;
    final private int statusCode;
    final private CustomException details;

    public CustomHttpException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
        this.statusCode = status.value();
        this.details = new UnexpectedException(message);
    }

    public CustomHttpException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.details = new UnexpectedException(message);
    }

    public CustomHttpException(CustomException exception, HttpStatus status) {
        super(exception.getMessage());
        this.message = exception.getMessage();
        this.status = status;
        this.statusCode = status.value();
        this.details = exception;
    }
}
