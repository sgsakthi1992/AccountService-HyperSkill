package account.service;

import account.converter.UserConverter;
import account.domain.Employee;
import account.domain.User;
import account.model.EmployeePaymentResponse;
import account.repository.EmployeeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeePaymentResponse getPayment(String period) {
        var user = getUserDetails().getUser();
        var employee = employeeRepository.findEmployeeByUserAndPeriod(user, period)
                .orElse(null);
        return createEmployeePaymentResponse(user, employee);
    }

    public List<EmployeePaymentResponse> getPayments() {
        var user = getUserDetails().getUser();
        var employees = employeeRepository.findEmployeeByUser(user)
                .orElse(List.of());
        return createEmployeePaymentResponseList(user, employees);
    }

    private UserDetailsImpl getUserDetails() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) auth.getPrincipal();
    }

    private List<EmployeePaymentResponse> createEmployeePaymentResponseList(User user, List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getPeriod).reversed())
                .map(employee -> createEmployeePaymentResponse(user, employee))
                .collect(Collectors.toList());
    }

    private EmployeePaymentResponse createEmployeePaymentResponse(User user, Employee employee) {
        var response = new EmployeePaymentResponse();
        if (employee != null) {
            response.setName(user.getName());
            response.setLastname(user.getLastname());
            response.setPeriod(updateMonth(employee.getPeriod()));
            response.setSalary(convertSalary(employee.getSalary()));
        }
        return response;
    }

    private String updateMonth(String period) {
        var monthAndYear = period.split("-");
        var month = Month.of(Integer.parseInt(monthAndYear[0])).getDisplayName(TextStyle.FULL_STANDALONE, Locale.US);
        return month + "-" + monthAndYear[1];
    }

    private String convertSalary(Long salary) {
        return (salary / 100) + " dollar(s) " + (salary % 100) + " cent(s)";
    }
}
