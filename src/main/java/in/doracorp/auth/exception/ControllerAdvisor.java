package in.doracorp.auth.exception;

import in.doracorp.auth.dto.ErrorResponseDTO;
import in.doracorp.auth.dto.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDTO> handleLoginFailedException(AuthException ex, WebRequest request){
        return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO<String>(ex.getHttpStatusCode().value(),"Authentication Failed",ex.getMessage()),ex.getHttpStatusCode());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorResponseDTO> handleServerException(ServerException ex, WebRequest request){
        return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO<String>(ex.getHttpStatusCode().value(),"Failed to process the request",ex.getMessage()),ex.getHttpStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO<ValidationError>(HttpStatus.BAD_REQUEST.value(),"Validation Error", new ValidationError(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList())),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientDataException.class)
    public ResponseEntity<ErrorResponseDTO> handleInsufficientDataException(InsufficientDataException ex, WebRequest request){
        return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO<ValidationError>(HttpStatus.BAD_REQUEST.value(),"Validation Error",new ValidationError(ex.getValidationMessages())),HttpStatus.BAD_REQUEST);
    }
}
