package br.com.cognitio.application.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class EmailAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -8179101584548168715L;

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
