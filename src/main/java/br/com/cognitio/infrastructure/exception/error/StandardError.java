package br.com.cognitio.infrastructure.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = -3797677355740645087L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
