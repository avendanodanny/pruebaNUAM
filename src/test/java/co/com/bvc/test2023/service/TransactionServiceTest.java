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
        // Datos simulados
        Long transactionId = 1L;
        Transaction transaction = new Transaction();

        // Simular comportamiento del repositorio
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        // Llamar al método del servicio
        Transaction result = transactionService.getTransactionById(transactionId);

        // Validar respuesta
        assertNotNull(result);
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    void testGetTransactionByIdNotFound() {
        // Datos simulados
        Long transactionId = 1L;

        // Simular comportamiento del repositorio
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        // Llamar al método del servicio
        Transaction result = transactionService.getTransactionById(transactionId);

        // Validar respuesta
        assertNull(result);
        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    void testCreateUpdateTransaction() {
        // Datos simulados
        Transaction transaction = new Transaction();

        // Simular comportamiento del repositorio
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // Llamar al método del servicio
        Transaction result = transactionService.createUpdateTransaction(transaction);

        // Validar respuesta
        assertNotNull(result);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testDeleteTransaction() {
        // Datos simulados
        Long transactionId = 1L;

        // Simular comportamiento del repositorio
        doNothing().when(transactionRepository).deleteById(transactionId);

        // Llamar al método del servicio
        transactionService.deleteTransaction(transactionId);

        // Validar respuesta
        verify(transactionRepository, times(1)).deleteById(transactionId);
    }

    @Test
    void testGetTop10CompaniesByTransactions() {
        // Datos simulados
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L}, new Object[]{1L, 100L, 50L});

        // Simular comportamiento del repositorio
        when(transactionRepository.getTop10CompaniesByTransactions(any())).thenReturn(mockResults);

        // Llamar al método del servicio
        List<CompanyRankingDTO> result = transactionService.getTop10CompaniesByTransactions();

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTop10CompaniesByTransactions(any());
    }

    @Test
    void testGetTop10UsersByTransactions() {
        // Datos simulados
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L}, new Object[]{1L, 100L, 50L});

        // Simular comportamiento del repositorio
        when(transactionRepository.getTop10UsersByTransactions(any())).thenReturn(mockResults);

        // Llamar al método del servicio
        List<UserRankingDTO> result = transactionService.getTop10UsersByTransactions();

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTop10UsersByTransactions(any());
    }

    @Test
    void testGetTransactionSummaryByUser() {
        // Datos simulados
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L, 500L, 450L}, new Object[]{1L, 100L, 50L, 500L, 450L});

        // Simular comportamiento del repositorio
        when(transactionRepository.getTransactionSummaryByUser()).thenReturn(mockResults);

        // Llamar al método del servicio
        List<TransactionSummaryUserDTO> result = transactionService.getTransactionSummaryByUser();

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTransactionSummaryByUser();
    }

    @Test
    void testGetTransactionSummaryByCompany() {
        // Datos simulados
        List<Object[]> mockResults = Arrays.asList(new Object[]{1L, 100L, 50L, 500L, 450L}, new Object[]{1L, 100L, 50L, 500L, 450L});

        // Simular comportamiento del repositorio
        when(transactionRepository.getTransactionSummaryByCompany()).thenReturn(mockResults);

        // Llamar al método del servicio
        List<TransactionSummaryCompanyDTO> result = transactionService.getTransactionSummaryByCompany();

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(100L, result.get(0).getTotalCompradas());
        assertEquals(50L, result.get(0).getTotalVendidas());

        verify(transactionRepository, times(1)).getTransactionSummaryByCompany();
    }
}
