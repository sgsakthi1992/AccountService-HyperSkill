package account.controller;

import account.model.RoleUpdateRequest;
import account.model.StatusResponse;
import account.model.UserResponse;
import account.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return adminService.getUsers();
    }

    @DeleteMapping(value = {"", "/{email}"})
    public StatusResponse deleteUser(@PathVariable(required = false) String email) {
        return adminService.deleteUser(email);
    }

    @PutMapping("/role")
    public UserResponse updateRole(@RequestBody(required = false) RoleUpdateRequest request) {
        return adminService.updateRole(request);
    }
}
