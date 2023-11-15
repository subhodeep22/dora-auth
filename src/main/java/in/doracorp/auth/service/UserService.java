package in.doracorp.auth.service;

import in.doracorp.auth.dto.LoginRequestDTO;
import in.doracorp.auth.exception.AuthException;
import in.doracorp.auth.exception.ServerException;
import in.doracorp.auth.model.User;
import in.doracorp.auth.repository.UserRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void registerUser(User user) throws ServerException, AuthException {
        try {
            if (!userRepository.existsUserByEmailAndIsVisitor(user.getEmail())) {
                userRepository.save(user);
            } else {
                throw new AuthException("User Already Exists", HttpStatus.BAD_REQUEST);
            }
        }catch (HibernateException e){
            throw new ServerException("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public User findUser(LoginRequestDTO user) throws ServerException, AuthException{
        try {
            if(user.getUserType().equals("tenant-user")){
                if(userRepository.existsUserByEmailAndTenantId(user.getEmail(),user.getTenantId())) {
                    return userRepository.findByEmailAndTenantId(user.getEmail(), user.getTenantId());
                }else{
                    throw new AuthException("Invalid Email/Password", HttpStatus.FORBIDDEN);
                }
            }else {
                if (userRepository.existsUserByEmailAndIsVisitor(user.getEmail())) {
                    return userRepository.findByEmailAndIsVisitor(user.getEmail());
                } else {
                    throw new AuthException("Invalid Email/Password", HttpStatus.FORBIDDEN);
                }
            }
        }catch (HibernateException e){
            throw new ServerException("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
