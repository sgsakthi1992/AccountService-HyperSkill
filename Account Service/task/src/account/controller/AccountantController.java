package account.controller;

import account.model.PaymentRequest;
import account.model.StatusResponse;
import account.service.AccountantService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/acct/payments")
@Validated
@PreAuthorize("hasRole('ROLE_ACCOUNTANT')")
public class AccountantController {

    private final AccountantService accountantService;

    public AccountantController(AccountantService accountantService) {
        this.accountantService = accountantService;
    }

    @PostMapping
    public StatusResponse createPayments(@Valid @RequestBody(required = false) List<PaymentRequest> request) {
        return accountantService.createPayments(request);
    }

    @PutMapping
    public StatusResponse updatePayment(@Valid @RequestBody PaymentRequest request) {
        return accountantService.updatePayment(request);
    }
}
