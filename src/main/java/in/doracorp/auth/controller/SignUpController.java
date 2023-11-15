package in.doracorp.auth.controller;

import in.doracorp.auth.dto.SignUpResponseDTO;
import in.doracorp.auth.dto.SignUpRequestDTO;
import in.doracorp.auth.model.User;
import in.doracorp.auth.service.UserService;
import in.doracorp.auth.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signUp")
public class SignUpController {
    private UserService userService;
    private Mapper mapper;

    @Autowired
    public SignUpController(UserService userService, Mapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<SignUpResponseDTO> signUpVisitor(@RequestBody SignUpRequestDTO signUpRequestDTO){
        User user = mapper.toUserFromSignUpRequestDto(signUpRequestDTO, "visitor");
        userService.registerUser(user);
        return new ResponseEntity<SignUpResponseDTO>(new SignUpResponseDTO(false, "Sign Up Success"), HttpStatus.CREATED);
    }
}
