package in.doracorp.auth.util;

import in.doracorp.auth.dto.SignUpRequestDTO;
import in.doracorp.auth.dto.UserDTO;
import in.doracorp.auth.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public User toUserFromSignUpRequestDto(SignUpRequestDTO signUpRequestDTO, String userType){
        User user = new User();
        user.setName(signUpRequestDTO.getName());
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPassword(signUpRequestDTO.getPassword());
        user.setUserType(userType);

        return user;
    }

    public UserDTO toUserDtoFromUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setTenantId(user.getTenantId());
        userDTO.setUserId(user.getUserId());
        userDTO.setUserType(user.getUserType());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setContact(user.getContact());

        return userDTO;
    }
}
