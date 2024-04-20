package br.com.devtops.microservice.article.exceptionhandlers;

import br.com.devtops.microservice.article.exceptions.protocols.CustomHttpException;
import br.com.devtops.microservice.article.external.protocols.CustomResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final CustomResponse<CustomHttpException> handleAllExceptions(Exception exception, WebRequest request){
        CustomHttpException customException = new CustomHttpException(exception.getMessage());

        return new CustomResponse<>(customException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomHttpException.class)
    public final CustomResponse<CustomHttpException> handleAllHttpExceptions(CustomHttpException exception) {
        return new CustomResponse<>(exception, exception.getStatus());
    }
}
