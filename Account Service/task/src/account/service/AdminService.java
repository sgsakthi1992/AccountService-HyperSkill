package account.service;

import account.converter.UserConverter;
import account.domain.User;
import account.exception.DataNotFoundException;
import account.exception.NotValidOperationException;
import account.model.Operation;
import account.model.RoleUpdateRequest;
import account.model.StatusResponse;
import account.model.UserResponse;
import account.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private static final List<String> ROLES = List.of("USER", "ACCOUNTANT", "ADMINISTRATOR");
    private final UserRepository userRepository;
    private final UserConverter converter;

    public AdminService(UserRepository userRepository, UserConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public StatusResponse deleteUser(String email) {
        if (getUserDetails().getUser().getEmail().equalsIgnoreCase(email)) {
            throw new NotValidOperationException("Can't remove ADMINISTRATOR role!");
        }
        var isDeleted = userRepository.deleteByEmailAllIgnoreCase(email);
        if (isDeleted == 0) {
            throw new DataNotFoundException("User not found!");
        }
        return StatusResponse.builder()
                .withUser(email)
                .withStatus("Deleted successfully!")
                .build();
    }

    private UserDetailsImpl getUserDetails() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) auth.getPrincipal();
    }

    public UserResponse updateRole(RoleUpdateRequest request) {
        if(request == null) {
            throw  new DataNotFoundException("User not found!");
        }
        var user = userRepository.findByEmailAllIgnoreCase(request.getUser())
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
        isValidUpdate(request, user);
        user = performUpdate(request, user);
        return converter.convert(user);
    }

    private User performUpdate(RoleUpdateRequest request, User user) {
        var userRoles = user.getRoles();
        if (request.getOperation().equals(Operation.GRANT)) {
            userRoles.add("ROLE_" + request.getRole().toUpperCase());
        } else if (request.getOperation().equals(Operation.REMOVE)) {
            userRoles.remove("ROLE_" + request.getRole().toUpperCase());
        }
        user.setRoles(userRoles);
        return userRepository.save(user);
    }

    private void isValidUpdate(RoleUpdateRequest request, User user) {
        if (!ROLES.contains(request.getRole().toUpperCase())) {
            throw new DataNotFoundException("Role not found!");
        }
        if (request.getOperation().equals(Operation.GRANT)) {
            if (user.getRoles().contains("ROLE_ADMINISTRATOR") || request.getRole().equalsIgnoreCase("ADMINISTRATOR")) {
                throw new NotValidOperationException("The user cannot combine administrative and business roles!");
            }
        } else if (request.getOperation().equals(Operation.REMOVE)) {
            if (request.getRole().equalsIgnoreCase("ADMINISTRATOR")) {
                throw new NotValidOperationException("Can't remove ADMINISTRATOR role!");
            } else if (!user.getRoles().contains("ROLE_" + request.getRole().toUpperCase())) {
                throw new NotValidOperationException("The user does not have a role!");
            } else if (user.getRoles().size() == 1) {
                throw new NotValidOperationException("The user must have at least one role!");
            }
        }
    }
}
