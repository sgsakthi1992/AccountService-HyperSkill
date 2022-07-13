package account.repository;

import account.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAllIgnoreCase(String email);

    long deleteByEmailAllIgnoreCase(String email);

}
