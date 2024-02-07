package br.com.cognitio.application.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class LoginAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8179101584548168715L;

    public LoginAlreadyExistsException(String message) {
        super(message);
    }

    public LoginAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
