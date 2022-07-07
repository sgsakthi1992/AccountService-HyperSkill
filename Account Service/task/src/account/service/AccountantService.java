package account.service;

import account.domain.Employee;
import account.exception.PeriodNotFoundException;
import account.model.PaymentRequest;
import account.model.PaymentResponse;
import account.repository.EmployeeRepository;
import account.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class AccountantService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public AccountantService(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PaymentResponse createPayments(@Valid List<PaymentRequest> request) {
        request.forEach(this::createEmployee);
        return createPaymentResponse("Added successfully!");
    }

    public PaymentResponse updatePayment(PaymentRequest paymentRequest) {
        var user = userRepository.findByEmailAllIgnoreCase(paymentRequest.getEmployee())
                .orElseThrow(() -> new UsernameNotFoundException(paymentRequest.getEmployee() + " not found"));
        var employee = employeeRepository.findEmployeeByUserAndPeriod(user, paymentRequest.getPeriod())
                .orElseThrow(() -> new PeriodNotFoundException(paymentRequest.getEmployee() + " for the period " + paymentRequest.getPeriod() + " not found"));
        employee.setSalary(paymentRequest.getSalary());
        employeeRepository.save(employee);
        return createPaymentResponse("Updated successfully!");
    }

    private void createEmployee(PaymentRequest paymentRequest) {
        var user = userRepository.findByEmailAllIgnoreCase(paymentRequest.getEmployee())
                .orElseThrow(() -> new UsernameNotFoundException(paymentRequest.getEmployee() + " not found"));
        employeeRepository.insertEmployee(user.getId(), paymentRequest.getSalary(), paymentRequest.getPeriod());
    }

    private PaymentResponse createPaymentResponse(String status) {
        return new PaymentResponse(status);
    }
}
