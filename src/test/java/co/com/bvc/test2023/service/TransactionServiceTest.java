package co.com.bvc.test2023.service;

import co.com.bvc.test2023.dto.CompanyRankingDTO;
import co.com.bvc.test2023.model.Transaction;
import co.com.bvc.test2023.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllTransactions() {
        // Datos simulados
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        // Simular comportamiento del repositorio
        when(transactionRepository.findAll()).thenReturn(transactions);

        // Llamar al método del servicio
        List<Transaction> result = transactionService.getAllTransactions();

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransactionByIdFound() {
        Long transactionId = 1L;
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.getTransactionById(transactionId);

        assertNotNull(result);
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    void testGetTransactionByIdNotFound() {
        Long transactionId = 1L;
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Transaction result = transactionService.getTransactionById(transactionId);

        assertNull(result);
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    void testCreateUpdateTransaction() {
        Transaction transaction = new Transaction();
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.createUpdateTransaction(transaction);

        assertNotNull(result);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testDeleteTransaction() {
        Long transactionId = 1L;
        doNothing().when(transactionRepository).deleteById(transactionId);

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository, times(1)).deleteById(transactionId);
    }

    @Test
    void testGetTop10CompaniesByTransactions() {
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L}, new Object[]{1L, 100L, 50L});
        when(transactionRepository.getTop10CompaniesByTransactions(any())).thenReturn(mockResults);

        List<CompanyRankingDTO> result = transactionService.getTop10CompaniesByTransactions();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTop10CompaniesByTransactions(any());
    }
}
