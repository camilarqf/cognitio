package br.com.cognitio.application.exception;

import java.io.Serial;

public class LoginAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8179101584548168715L;

    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
