package in.doracorp.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String tenantId;
    private String userId;
    private String email;
    private String name;
    private String contact;
    private String userType;
}
