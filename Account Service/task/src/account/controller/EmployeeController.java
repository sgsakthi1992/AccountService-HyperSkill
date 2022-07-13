package account.controller;

import account.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("api/empl")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ACCOUNTANT')")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/payment")
    public ResponseEntity<?> getPayment(@Valid @Pattern(regexp = "(0\\d|1[0-2])-\\d{4}", message = "Wrong date!") @RequestParam(required = false) String period) {
        if (period == null) {
            return ResponseEntity.ok().body(employeeService.getPayments());
        }
        return ResponseEntity.ok().body(employeeService.getPayment(period));
    }
}
