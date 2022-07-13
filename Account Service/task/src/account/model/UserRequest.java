package account.model;

import account.validator.BreachPassword;
import account.validator.UniqueEmail;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Lastname must not be blank")
    private String lastname;
    @Email(regexp = ".+@acme.com")
    @UniqueEmail(message = "User exist!")
    @NotBlank
    private String email;
    @NotBlank(message = "Password must not be blank")
    @Length(min = 12, message = "Password length must be 12 chars minimum!")
    @BreachPassword
    private String password;
}
