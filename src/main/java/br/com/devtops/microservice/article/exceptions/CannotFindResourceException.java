package br.com.devtops.microservice.article.exceptions;

public class CannotFindResourceException  extends RuntimeException{
    public CannotFindResourceException(String message){
        super(message);
    }
}