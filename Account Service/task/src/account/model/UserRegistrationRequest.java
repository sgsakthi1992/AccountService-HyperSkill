package account.model;

import account.validator.UniqueEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Lastname must not be blank")
    private String lastname;
    @Email(regexp = ".+@acme.com")
    @UniqueEmail(message = "User exist!")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
}
