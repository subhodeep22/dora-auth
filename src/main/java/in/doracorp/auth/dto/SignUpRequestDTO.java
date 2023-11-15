package in.doracorp.auth.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDTO {
    private String name;
    private String email;
    private String password;
}
