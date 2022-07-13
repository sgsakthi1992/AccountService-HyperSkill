package account.model;

import lombok.Builder;
import lombok.Data;
@Builder(setterPrefix = "with")
@Data
public class StatusResponse {
    private String user;
    private String status;
}
