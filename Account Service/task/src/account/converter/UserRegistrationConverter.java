package account.converter;

import account.domain.User;
import account.model.UserRegistrationRequest;
import account.model.UserRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationConverter {

    public User convert(UserRegistrationRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        return user;
    }

    public UserRegistrationResponse convert(User user) {
        var response = new UserRegistrationResponse();
        response.setName(user.getName());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        return response;
    }
}
