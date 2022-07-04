package account.model;

import account.validator.UniqueEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @Email(regexp = ".+@acme.com")
    //@UniqueEmail
    private String email;
    @NotBlank
    private String password;
}
