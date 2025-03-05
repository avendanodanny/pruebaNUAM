package co.com.bvc.test2023.repository;

import co.com.bvc.test2023.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
