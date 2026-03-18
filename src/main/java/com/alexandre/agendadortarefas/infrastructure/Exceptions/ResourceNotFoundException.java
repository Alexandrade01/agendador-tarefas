package com.alexandre.agendadortarefas.infrastructure.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundException(String message,Throwable throwable){
        super(message,throwable);
    }
}
