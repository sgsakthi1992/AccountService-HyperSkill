package account.controller;

import account.model.ChangePasswordRequest;
import account.model.ChangePasswordResponse;
import account.model.UserRequest;
import account.model.UserResponse;
import account.service.ChangePasswordService;
import account.service.UserRegistrationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final ChangePasswordService changePasswordService;

    public UserRegistrationController(UserRegistrationService userRegistrationService, ChangePasswordService changePasswordService) {
        this.userRegistrationService = userRegistrationService;
        this.changePasswordService = changePasswordService;
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody @Valid UserRequest request) {
        return userRegistrationService.signup(request);
    }

    @PostMapping("/changepass")
    public ChangePasswordResponse changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        return changePasswordService.changePassword(request);
    }
}
