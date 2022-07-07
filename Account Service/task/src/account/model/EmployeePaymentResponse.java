package account.model;

import lombok.Data;

@Data
public class EmployeePaymentResponse {
    private String name;
    private String lastname;
    private String period;
    private String salary;
}
