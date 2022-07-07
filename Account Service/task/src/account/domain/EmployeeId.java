package account.domain;

import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeId implements Serializable {
    private Long user;
    private String period;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeId that = (EmployeeId) o;
        return user != null && Objects.equals(user, that.user)
                && period != null && Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, period);
    }
}
