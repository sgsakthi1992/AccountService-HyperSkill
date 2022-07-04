package account.service;

import account.converter.UserRegistrationConverter;
import account.model.UserRegistrationRequest;
import account.model.UserRegistrationResponse;
import account.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final UserRegistrationConverter converter;

    public UserRegistrationService(UserRepository userRepository, UserRegistrationConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserRegistrationResponse signup(UserRegistrationRequest request){
        var user = converter.convert(request);
        user = userRepository.save(user);
        return converter.convert(user);
    }
}
