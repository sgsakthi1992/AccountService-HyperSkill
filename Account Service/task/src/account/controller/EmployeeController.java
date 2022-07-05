package account.controller;

import account.model.UserRegistrationResponse;
import account.service.EmployeeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empl")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/payment")
    public UserRegistrationResponse getPayment(@AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails!=null) {
            return employeeService.getPayment(userDetails.getUsername());
        }
        throw new UsernameNotFoundException("Invalid user");
    }
}
