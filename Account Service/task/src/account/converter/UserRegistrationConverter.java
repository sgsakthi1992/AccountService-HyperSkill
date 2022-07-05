package account.converter;

import account.domain.User;
import account.model.UserRegistrationRequest;
import account.model.UserRegistrationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationConverter {

    private final PasswordEncoder passwordEncoder;

    public UserRegistrationConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convert(UserRegistrationRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail().toLowerCase());
        return user;
    }

    public UserRegistrationResponse convert(User user) {
        var response = new UserRegistrationResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        return response;
    }
}
