package co.com.bvc.test2023.service;

import co.com.bvc.test2023.dto.CompanyRankingDTO;
import co.com.bvc.test2023.dto.TransactionSummaryCompanyDTO;
import co.com.bvc.test2023.dto.TransactionSummaryUserDTO;
import co.com.bvc.test2023.dto.UserRankingDTO;
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

    public List<TransactionSummaryUserDTO> getTransactionSummaryByUser();

    public List<TransactionSummaryCompanyDTO> getTransactionSummaryByCompany();

    public List<CompanyRankingDTO> getTop10CompaniesByTransactions();

    public List<UserRankingDTO> getTop10UsersByTransactions();

}
