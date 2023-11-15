package in.doracorp.auth.exception;

import in.doracorp.auth.dto.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class InsufficientDataException extends RuntimeException{
    private final HttpStatus httpStatusCode;
    private final BindingResult validationErrors;

    public List<String> getValidationMessages(){
        return this.validationErrors
                .getAllErrors()
                .stream()
                .map(InsufficientDataException::getError)
                .toList();
    }

    public static String getError(ObjectError error){
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
//            String className = fieldError.getObjectName();
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s %s, but it was %s", property, message, invalidValue);
        }
        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
    }
    public InsufficientDataException(String message,Throwable cause, HttpStatus httpStatusCode,BindingResult validationErrors){
        super(message,cause);
        this.httpStatusCode = httpStatusCode;
        this.validationErrors = validationErrors;
    }

    public InsufficientDataException(String message,HttpStatus httpStatusCode,BindingResult validationErrors){
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.validationErrors = validationErrors;
    }
}
