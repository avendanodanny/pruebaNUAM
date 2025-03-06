package co.com.bvc.test2023.service;

import co.com.bvc.test2023.dto.TransactionSummaryDTO;
import co.com.bvc.test2023.model.Transaction;

import java.util.Date;
import java.util.List;

public interface ITransactionService {

    public List<Transaction> getAllTransactions();

    public Transaction getTransactionById(Long id);

    public Transaction createUpdateTransaction(Transaction company);

    public void deleteTransaction(Long id);

    public List<Transaction> getTransactionByUser(Long idUser);

    public List<Transaction> getTransactionByCompany(Long companyId);

    public List<Transaction> getTransactionsBetweenDates(Date startDate, Date endDate);

    public List<TransactionSummaryDTO> getTransactionSummaryByUser();

}
