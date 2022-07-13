package account.converter;

import account.domain.User;
import account.model.UserRequest;
import account.model.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    public UserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convert(UserRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail().toLowerCase());
        return user;
    }

    public UserResponse convert(User user) {
        var response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles().stream().sorted().collect(Collectors.toList()));
        return response;
    }
}
