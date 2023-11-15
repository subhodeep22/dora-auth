package in.doracorp.auth.controller;

import in.doracorp.auth.dto.LoginResponseDTO;
import in.doracorp.auth.dto.LoginRequestDTO;
import in.doracorp.auth.exception.AuthException;
import in.doracorp.auth.exception.InsufficientDataException;
import in.doracorp.auth.model.User;
import in.doracorp.auth.service.UserService;
import in.doracorp.auth.util.Hash;
import in.doracorp.auth.util.Mapper;
import in.doracorp.auth.util.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private UserService userService;
    private Mapper mapper;

    @Autowired
    public LoginController(UserService userService, Mapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> logIn(@Validated @RequestBody LoginRequestDTO loginRequestDTO, BindingResult errors){
        if (errors.hasErrors()) {
            throw new InsufficientDataException("Login Failed",HttpStatus.BAD_REQUEST,errors);
        }
        User user = userService.findUser(loginRequestDTO);
        if(Hash.compareHash(user.getPassword(), loginRequestDTO.getPassword())){
            Map<String, Object> payload = new HashMap<>();
            payload.put("userEmail",user.getEmail());
            payload.put("userType",user.getUserType());
            String token = Sign.generateToken(payload);
            HttpHeaders headers = new HttpHeaders();
            headers.add("email",user.getEmail());
            headers.add("userType",user.getUserType());
            return new ResponseEntity<>(new LoginResponseDTO(false,token,"SUCCESS",mapper.toUserDtoFromUser(user)),headers,HttpStatus.OK);
        }else {
            throw new AuthException("Invalid Username/Password", HttpStatus.FORBIDDEN);
        }
    }
}
