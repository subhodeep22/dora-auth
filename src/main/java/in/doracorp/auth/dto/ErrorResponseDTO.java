package in.doracorp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO<T> {
    private int errorCode;
    private String errorMessage;
    private T errorDetails;
}
