package in.doracorp.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class AuthException extends RuntimeException{
    private final HttpStatus httpStatusCode;

    public AuthException(String message,Throwable cause, HttpStatus httpStatusCode){
        super(message,cause);
        this.httpStatusCode = httpStatusCode;
    }

    public AuthException(String message,HttpStatus httpStatusCode){
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
