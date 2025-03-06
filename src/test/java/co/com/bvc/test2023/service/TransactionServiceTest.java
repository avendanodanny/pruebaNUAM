package co.com.bvc.test2023.service;

import co.com.bvc.test2023.dto.CompanyRankingDTO;
import co.com.bvc.test2023.dto.TransactionSummaryCompanyDTO;
import co.com.bvc.test2023.dto.TransactionSummaryUserDTO;
import co.com.bvc.test2023.dto.UserRankingDTO;
import co.com.bvc.test2023.model.Transaction;
import co.com.bvc.test2023.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Date;
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
    void testGetTransactionByUser() {
        // Datos simulados
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        // Simular comportamiento del repositorio
        when(transactionRepository.getTransactionByUser(any())).thenReturn(transactions);

        // Llamar al método del servicio
        List<Transaction> result = transactionService.getTransactionByUser(1L);

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).getTransactionByUser(any());
    }

    @Test
    void testGetTransactionByCompany() {
        // Datos simulados
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        // Simular comportamiento del repositorio
        when(transactionRepository.getTransactionByCompany(any())).thenReturn(transactions);

        // Llamar al método del servicio
        List<Transaction> result = transactionService.getTransactionByCompany(1L);

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).getTransactionByCompany(any());
    }

    @Test
    void testGetTransactionsBetweenDates() {
        // Datos simulados
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        // Simular comportamiento del repositorio
        when(transactionRepository.getTransactionsBetweenDates(any(), any())).thenReturn(transactions);

        // Llamar al método del servicio
        List<Transaction> result = transactionService.getTransactionsBetweenDates(new Date(), new Date());

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).getTransactionsBetweenDates(any(), any());
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

    @Test
    void testGetTop10UsersByTransactions() {
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L}, new Object[]{1L, 100L, 50L});
        when(transactionRepository.getTop10UsersByTransactions(any())).thenReturn(mockResults);

        List<UserRankingDTO> result = transactionService.getTop10UsersByTransactions();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTop10UsersByTransactions(any());
    }

    @Test
    void testGetTransactionSummaryByUser() {
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L, 500L, 450L}, new Object[]{1L, 100L, 50L, 500L, 450L});
        when(transactionRepository.getTransactionSummaryByUser()).thenReturn(mockResults);

        List<TransactionSummaryUserDTO> result = transactionService.getTransactionSummaryByUser();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTransactionSummaryByUser();
    }

    @Test
    void testGetTransactionSummaryByCompany() {
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L, 500L, 450L}, new Object[]{1L, 100L, 50L, 500L, 450L});
        when(transactionRepository.getTransactionSummaryByCompany()).thenReturn(mockResults);

        List<TransactionSummaryCompanyDTO> result = transactionService.getTransactionSummaryByCompany();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTransactionSummaryByCompany();
    }
}
