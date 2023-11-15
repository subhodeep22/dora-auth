package in.doracorp.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String tenantId;
    private String userId;
    private String password;
    @NotNull(message = "Email is mandatory")
    private String email;
    @NotNull(message = "Name is mandatory")
    private String name;
    private String contact;
    private String userType;
}
