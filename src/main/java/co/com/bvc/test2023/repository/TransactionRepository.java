package co.com.bvc.test2023.repository;

import co.com.bvc.test2023.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
