package br.com.anp.microservice.article.exceptions;

import br.com.anp.microservice.article.exceptions.protocols.CustomException;
import org.springframework.lang.Nullable;

public class UnexpectedException extends CustomException {
    static public String defaultName = "UNEXPECTED_EXCEPTION";
    static public String defaultMessage = "An unexpected error occurred!";

    public UnexpectedException(String message) {
        super(defaultName, message);
    }

    public UnexpectedException() {
        super(defaultName, defaultMessage);
    }

    public UnexpectedException(String message, @Nullable String stack) {
        super(defaultName, message, stack);
    }
}
