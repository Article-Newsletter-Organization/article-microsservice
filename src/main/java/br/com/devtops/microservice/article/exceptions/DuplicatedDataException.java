package br.com.devtops.microservice.article.exceptions;

public class DuplicatedDataException extends RuntimeException{

    public DuplicatedDataException(String message){
        super(message);
    }
}