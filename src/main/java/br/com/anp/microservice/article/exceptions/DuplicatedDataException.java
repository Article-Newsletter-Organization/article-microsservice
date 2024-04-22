package br.com.anp.microservice.article.exceptions;

import br.com.anp.microservice.article.exceptions.protocols.CustomException;
import org.springframework.lang.Nullable;

public class DuplicatedDataException extends CustomException {
    static public String defaultName = "DUPLICATED_DATA_EXCEPTION";
    static public String defaultMessage = "The values give is duplicated.";

    public DuplicatedDataException(String message){
        super(defaultName, message);
    }

    public DuplicatedDataException(){
        super(defaultName, defaultMessage);
    }

    public DuplicatedDataException(String message, @Nullable String stack) {
        super(defaultName, message, stack);
    }
}