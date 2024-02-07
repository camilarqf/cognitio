package br.com.cognitio.infrastructure.exception.advice;

import br.com.cognitio.infrastructure.exception.error.StandardError;
import br.com.cognitio.infrastructure.exception.error.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> objectNotFoundException(RuntimeException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Erro na solicitação", ex.getMessage(), request.getRequestURI());
        logger.error("TIMESTAMP={}; REQUEST URI={}; STATUS CODE={}; ERROR={}", System.currentTimeMillis(),
                request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> objectNotFoundException(MethodArgumentNotValidException ex, HttpServletRequest request){
        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Erro de validação.", "Erro na validação dos campos", request.getRequestURI());

            for(FieldError x : ex.getBindingResult().getFieldErrors()){
                errors.addErrors(x.getField(), x.getDefaultMessage());
            }
        logger.error("TIMESTAMP={}; REQUEST URI={}; STATUS CODE={}; ERROR={}", System.currentTimeMillis(),
                request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), errors.getMessage());
        return ResponseEntity.status(errors.getStatus()).body(errors);
    }
}
