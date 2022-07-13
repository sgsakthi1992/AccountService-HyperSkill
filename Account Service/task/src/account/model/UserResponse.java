package account.model;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private List<String> roles;
}
