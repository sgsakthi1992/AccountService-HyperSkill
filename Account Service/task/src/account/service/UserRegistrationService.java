package account.service;

import account.converter.UserConverter;
import account.model.UserRequest;
import account.model.UserResponse;
import account.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final UserConverter converter;

    public UserRegistrationService(UserRepository userRepository, UserConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserResponse signup(UserRequest request) {
        var user = converter.convert(request);
        user.setRoles(List.of(getRole()));
        user = userRepository.save(user);
        return converter.convert(user);
    }

    private String getRole() {
        if (userRepository.count() == 0) {
            return "ROLE_ADMINISTRATOR";
        }
        return "ROLE_USER";
    }
}
