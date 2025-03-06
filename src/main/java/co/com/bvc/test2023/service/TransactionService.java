package co.com.bvc.test2023.service;

import co.com.bvc.test2023.dto.TransactionSummaryCompanyDTO;
import co.com.bvc.test2023.dto.TransactionSummaryUserDTO;
import co.com.bvc.test2023.model.Transaction;
import co.com.bvc.test2023.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Method to get all the transactions
     * @return list of transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Method to get one transaction for id
     * @return transaction
     */
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    /**
     * Method to create new transaction or update a existing
     * @return create or update transaction
     */
    public Transaction createUpdateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     * Method to delete one transaction for id
     */
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    /**
     * Method to get all the transactions by user
     * @return list of transactions
     */
    public List<Transaction> getTransactionByUser(Long idUser) {
        return transactionRepository.getTransactionByUser(idUser);
    }

    /**
     * Method to get all the transactions by company
     * @return list of transactions
     */
    public List<Transaction> getTransactionByCompany(Long idCompany) {
        return transactionRepository.getTransactionByCompany(idCompany);
    }

    /**
     * Method to get all the transaction between date range
     * @param startDate
     * @param endDate
     * @return list of transactions
     */
    public List<Transaction> getTransactionsBetweenDates(Date startDate, Date endDate) {
        return transactionRepository.getTransactionsBetweenDates(startDate, endDate);
    }

    /**
     * Method to get summary transactions by user
     * @return list of summary transactions
     */
    public List<TransactionSummaryUserDTO> getTransactionSummaryByUser() {
        List<Object[]> results = transactionRepository.getTransactionSummaryByUser();
        return results.stream()
                .map(obj -> new TransactionSummaryUserDTO(
                        (Long) obj[0],  // userId
                        (Long) obj[1],  // totalCompradas
                        (Long) obj[2],  // totalVendidas
                        (Long) obj[3],  // precioTotalCompras
                        (Long) obj[4]   // precioTotalVentas
                ))
                .collect(Collectors.toList());
    }

    /**
     * Method to get summary transactions by company
     * @return list of summary transactions
     */
    public List<TransactionSummaryCompanyDTO> getTransactionSummaryByCompany() {
        List<Object[]> results = transactionRepository.getTransactionSummaryByCompany();
        return results.stream()
                .map(obj -> new TransactionSummaryCompanyDTO(
                        (Long) obj[0],  // companyId
                        (Long) obj[1],  // totalCompradas
                        (Long) obj[2],  // totalVendidas
                        (Long) obj[3],  // precioTotalCompras
                        (Long) obj[4]   // precioTotalVentas
                ))
                .collect(Collectors.toList());
    }
}
