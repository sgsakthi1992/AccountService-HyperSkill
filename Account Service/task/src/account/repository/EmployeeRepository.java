package account.repository;

import account.domain.Employee;
import account.domain.EmployeeId;
import account.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId> {

    Optional<Employee> findEmployeeByUserAndPeriod(User user, String period);
    Optional<List<Employee>> findEmployeeByUser(User user);

    @Modifying
    @Query(value = "insert into employee (user_id, salary, period) VALUES (:userId,:salary,:period)", nativeQuery = true)
    void insertEmployee(@Param("userId") Long userId, @Param("salary") Long salary, @Param("period") String period);
}
