package account.service;

import account.exception.PasswordSameException;
import account.model.ChangePasswordRequest;
import account.model.ChangePasswordResponse;
import account.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ChangePasswordService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        var email = getUserDetails().getUsername();
        var user = userRepository.findByEmailAllIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        if (isPasswordSame(user.getPassword(), request.getNew_password())) {
            throw new PasswordSameException("The passwords must be different!");
        }
        user.setPassword(encoder.encode(request.getNew_password()));
        userRepository.save(user);
        return createChangePasswordResponse(user.getEmail());
    }

    private UserDetailsImpl getUserDetails() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) auth.getPrincipal();
    }

    private Boolean isPasswordSame(String oldPassword, String newPassword) {
        return encoder.matches(newPassword, oldPassword);
    }

    private ChangePasswordResponse createChangePasswordResponse(String email) {
        var response = new ChangePasswordResponse();
        response.setEmail(email);
        response.setStatus("The password has been updated successfully");
        return response;
    }
}
