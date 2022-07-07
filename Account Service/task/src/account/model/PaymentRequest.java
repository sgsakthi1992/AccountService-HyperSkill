package account.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class PaymentRequest {
    private String employee;
    @Pattern(regexp = "(0\\d|1[0-2])-\\d{4}", message = "Wrong date!")
    private String period;
    @Min(value = 0, message = "Salary must be non negative!")
    private Long salary;
}
