package account.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@IdClass(EmployeeId.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;
    @Id
    private String period;
    private Long salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return user != null && Objects.equals(user, employee.user)
                && period != null && Objects.equals(period, employee.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, period);
    }
}
