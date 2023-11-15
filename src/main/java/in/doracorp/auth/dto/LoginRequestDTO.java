package in.doracorp.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequestDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String userType;
    private String tenantId;
}
