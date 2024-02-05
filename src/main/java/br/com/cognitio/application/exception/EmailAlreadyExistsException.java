package br.com.cognitio.application.exception;

import java.io.Serial;

public class EmailAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8179101584548168715L;

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
