package account.model;

import lombok.Data;

@Data
public class RoleUpdateRequest {
    private String user;
    private String role;
    private Operation operation;
}
