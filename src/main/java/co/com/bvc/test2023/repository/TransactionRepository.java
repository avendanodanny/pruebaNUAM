package co.com.bvc.test2023.repository;

import co.com.bvc.test2023.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.user.id = ?1")
    List<Transaction> getTransactionByUser(Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.company.id = ?1")
    List<Transaction> getTransactionByCompany(Long companyId);

    @Query("SELECT t FROM Transaction t WHERE t.dateTrx BETWEEN ?1 AND ?2")
    List<Transaction> getTransactionsBetweenDates(Date startDate, Date endDate);

}
