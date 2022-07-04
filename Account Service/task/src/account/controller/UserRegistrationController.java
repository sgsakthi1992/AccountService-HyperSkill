package account.controller;

import account.model.UserRegistrationRequest;
import account.model.UserRegistrationResponse;
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

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/signup")
    public UserRegistrationResponse signup(@RequestBody @Valid UserRegistrationRequest request){
        System.out.println(request);
        return userRegistrationService.signup(request);
    }
}
