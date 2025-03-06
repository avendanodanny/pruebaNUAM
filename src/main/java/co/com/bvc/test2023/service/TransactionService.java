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
     * Method to get all the transaction by user
     * @return list of transaction
     */
    public List<Transaction> getTransactionByUser(Long idUser) {
        return transactionRepository.getTransactionByUser(idUser);
    }
}
