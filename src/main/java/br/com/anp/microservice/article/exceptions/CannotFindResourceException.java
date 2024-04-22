package br.com.anp.microservice.article.exceptions;

import br.com.anp.microservice.article.exceptions.protocols.CustomException;

public class CannotFindResourceException  extends CustomException {
    static public String defaultName = "CANNOT_FIND_RESOURCE_EXCEPTION";
    static public String defaultMessage = "Resource not found.";

    public CannotFindResourceException(String message){
        super(defaultName, message);
    }

    public CannotFindResourceException(){
        super(defaultName, defaultMessage);
    }
}