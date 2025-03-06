package co.com.bvc.test2023.controller;

import co.com.bvc.test2023.dto.*;
import co.com.bvc.test2023.model.Company;
import co.com.bvc.test2023.model.Transaction;
import co.com.bvc.test2023.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    private static Logger logger = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    private ITransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        logger.info("llega al método getAllTransactions...");
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
        logger.info("llega al método getTransactionById...");
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private boolean validateTransactionType(String trxType){
        if (trxType.equals("compra") || trxType.equals("venta")){
            return true;
        }else{
            return false;
        }
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createUpdateTransaction(@RequestBody Transaction transaction) {
        logger.info("llega al método createUpdateTransaction...");
        TransactionResponse response = null;
        if (validateTransactionType(transaction.getTransactionType())){
            try {
                Transaction savedTransaction = transactionService.createUpdateTransaction(transaction);
                response = new TransactionResponse(null, savedTransaction);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }catch (Exception e){
                logger.error(e.getMessage());
                response = new TransactionResponse("Ha ocurrido un error al momento de guardar la trx: " + e.getMessage(),
                        null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            response = new TransactionResponse("El tipo de transacción no es válido, los posibles valores son (compra, venta)",
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable("id") Long id, @RequestBody Transaction transaction) {
        logger.info("llega al método updateTransaction...");
        TransactionResponse response = null;
        if (validateTransactionType(transaction.getTransactionType())){
            try {
                Transaction existingTransaction = transactionService.getTransactionById(id);
                if (existingTransaction != null) {
                    existingTransaction.setCompany(transaction.getCompany());
                    existingTransaction.setUser(transaction.getUser());
                    existingTransaction.setTransactionType(transaction.getTransactionType());
                    existingTransaction.setNumberShares(transaction.getNumberShares());
                    existingTransaction.setPriceShare(transaction.getPriceShare());
                    existingTransaction.setDateTrx(transaction.getDateTrx());

                    Transaction updatedTransaction = transactionService.createUpdateTransaction(existingTransaction);
                    response = new TransactionResponse(null, updatedTransaction);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }catch (Exception e){
                logger.error(e.getMessage());
                response = new TransactionResponse("Ha ocurrido un error al momento de guardar la trx: " + e.getMessage(),
                        null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            response = new TransactionResponse("El tipo de transacción no es válido, los posibles valores son (compra, venta)",
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") Long id) {
        logger.info("llega al método deleteTransaction...");
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/company/{idCompany}")
    public ResponseEntity<TransactionResponse> createTransactionByCompany(@PathVariable("idCompany") Long idCompany,
                                                                        @RequestBody Transaction transaction) {
        logger.info("llega al método createTransactionCompany...");
        TransactionResponse response = null;
        if (validateTransactionType(transaction.getTransactionType())){
            try {
                Company company = new Company(idCompany, "");
                transaction.setCompany(company);
                Transaction savedTransaction = transactionService.createUpdateTransaction(transaction);
                response = new TransactionResponse(null, savedTransaction);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }catch (Exception e){
                logger.error(e.getMessage());
                response = new TransactionResponse("Ha ocurrido un error al momento de guardar la trx: " + e.getMessage(),
                        null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            response = new TransactionResponse("El tipo de transacción no es válido, los posibles valores son (compra, venta)",
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Transaction>> getTransactionByUser(@PathVariable("idUser") Long idUser) {
        logger.info("llega al método getTransactionByUser...");
        List<Transaction> transactions = transactionService.getTransactionByUser(idUser);
        if (transactions != null) {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/company/{idCompany}")
    public ResponseEntity<List<Transaction>> getTransactionByCompany(@PathVariable("idCompany") Long idCompany) {
        logger.info("llega al método getTransactionByCompany...");
        List<Transaction> transactions = transactionService.getTransactionByCompany(idCompany);
        if (transactions != null) {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/range")
    public List<Transaction> getTransactionsBetweenDates(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        return transactionService.getTransactionsBetweenDates(startDate, endDate);
    }

    @GetMapping("/user/summary")
    public List<TransactionSummaryUserDTO> getTransactionSummaryUser() {
        return transactionService.getTransactionSummaryByUser();
    }

    @GetMapping("/company/summary")
    public List<TransactionSummaryCompanyDTO> getTransactionSummaryCompany() {
        return transactionService.getTransactionSummaryByCompany();
    }

    @GetMapping("/company/ranking")
    public List<CompanyRankingDTO> getTop10CompaniesByTransactions() {
        return transactionService.getTop10CompaniesByTransactions();
    }

    @GetMapping("/user/ranking")
    public List<UserRankingDTO> getTop10UsersByTransactions() {
        return transactionService.getTop10UsersByTransactions();
    }

}
