package co.com.bvc.test2023.controller;

import co.com.bvc.test2023.model.Transaction;
import co.com.bvc.test2023.model.TransactionResponse;
import co.com.bvc.test2023.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<TransactionResponse> createUpdateTransaction(@RequestBody Transaction company) {
        logger.info("llega al método createUpdateTransaction...");
        TransactionResponse response = null;
        try {
            Transaction savedTransaction = transactionService.createUpdateTransaction(company);
            response = new TransactionResponse(null, savedTransaction);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            response = new TransactionResponse("Ha ocurrido un error al momento de guardar la trx: " + e.getMessage(),
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") Long id, @RequestBody Transaction transaction) {
        logger.info("llega al método updateTransaction...");
        Transaction existingTransaction = transactionService.getTransactionById(id);
        if (existingTransaction != null) {
            existingTransaction.setCompany(transaction.getCompany());
            existingTransaction.setUser(transaction.getUser());
            existingTransaction.setTransactionType(transaction.getTransactionType());
            existingTransaction.setNumberShares(transaction.getNumberShares());
            existingTransaction.setPriceShare(transaction.getPriceShare());
            existingTransaction.setDateTrx(transaction.getDateTrx());

            Transaction updatedTransaction = transactionService.createUpdateTransaction(existingTransaction);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") Long id) {
        logger.info("llega al método deleteCompany...");
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
