package account.model;

import lombok.Data;

@Data
public class ChangePasswordResponse {
    private String email;
    private String status;
}
