package in.doracorp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class LoginResponseDTO {
    private boolean error;
    private String token;
    private String message;
    private UserDTO user;
}
