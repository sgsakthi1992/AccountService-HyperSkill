package account.model;

import lombok.Data;

@Data
public class UserRegistrationResponse {
    private Long id;
    private String name;
    private String lastname;
    private String email;
}
