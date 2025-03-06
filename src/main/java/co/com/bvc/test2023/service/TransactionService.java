package co.com.bvc.test2023.service;

import co.com.bvc.test2023.model.Transaction;
import co.com.bvc.test2023.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Method to get all the companies
     * @return list of companies
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Method to get one company for id
     * @return company
     */
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    /**
     * Method to create new company or update a existing
     * @return create or update company
     */
    public Transaction createUpdateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     * Method to delete one company for id
     */
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
