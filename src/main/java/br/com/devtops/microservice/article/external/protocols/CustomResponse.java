package br.com.devtops.microservice.article.external.protocols;

import br.com.devtops.microservice.article.exceptions.protocols.CustomHttpException;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class CustomResponse<T> extends ResponseEntity<CustomResponse.Body<T>> {

    public CustomResponse(T data, HttpStatusCode status) {
        super(new Body<T>(data, null), HttpStatusCode.valueOf(status.value()));
    }

    public CustomResponse(CustomHttpException error, HttpStatusCode status) {
        super(new Body<>(null, error), HttpStatusCode.valueOf(status.value()));
    }

    public CustomResponse(HttpStatusCode status) {
        super(new Body<>(null, null), HttpStatusCode.valueOf(status.value()));
    }

    @Getter
    @AllArgsConstructor
    public static class Body<T> {
        final private @Nullable T data;
        final private @Nullable CustomHttpException error;
        final private Date timestamp = new Date();
    }


}
