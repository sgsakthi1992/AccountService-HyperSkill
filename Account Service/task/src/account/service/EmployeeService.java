package account.service;

import account.converter.UserRegistrationConverter;
import account.model.UserRegistrationResponse;
import account.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final UserRepository userRepository;
    private final UserRegistrationConverter converter;

    public EmployeeService(UserRepository userRepository, UserRegistrationConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserRegistrationResponse getPayment(String email) {
        var user = userRepository.findByEmailAllIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        return converter.convert(user);
    }
}
