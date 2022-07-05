package account.model;

import account.validator.BreachPassword;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class ChangePasswordRequest {
    @NotBlank(message = "Password must not be blank")
    @Length(min = 12, message = "Password length must be 12 chars minimum!")
    @BreachPassword
    private String new_password;
}
