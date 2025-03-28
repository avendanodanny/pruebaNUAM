package co.com.bvc.test2023.controller;

import co.com.bvc.test2023.dto.*;
import co.com.bvc.test2023.model.Company;
import co.com.bvc.test2023.model.Transaction;
import co.com.bvc.test2023.model.User;
import co.com.bvc.test2023.service.ITransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionRestControllerTest {

    @Mock
    private ITransactionService transactionService;

    @InjectMocks
    private TransactionRestController transactionRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllTransactions() {
        // Datos simulados
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        // Simular comportamiento del servicio
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        // Llamar al método del controlador
        ResponseEntity<List<Transaction>> response = transactionRestController.getAllTransactions();

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        // Verificar que el servicio fue llamado
        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    void testGetTransactionByUser() {
        // Datos simulados
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        // Simular comportamiento del servicio
        when(transactionService.getTransactionByUser(any())).thenReturn(transactions);

        // Llamar al método del controlador
        ResponseEntity<List<Transaction>> response = transactionRestController.getTransactionByUser(1L);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        // Verificar que el servicio fue llamado
        verify(transactionService, times(1)).getTransactionByUser(any());
    }

    @Test
    void testGetTransactionByCompany() {
        // Datos simulados
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        // Simular comportamiento del servicio
        when(transactionService.getTransactionByCompany(any())).thenReturn(transactions);

        // Llamar al método del controlador
        ResponseEntity<List<Transaction>> response = transactionRestController.getTransactionByCompany(1L);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        // Verificar que el servicio fue llamado
        verify(transactionService, times(1)).getTransactionByCompany(any());
    }

    @Test
    void testGetTransactionByIdFound() {
        // Datos simulados
        Long transactionId = 1L;
        Transaction transaction = new Transaction();

        // Simular comportamiento del servicio
        when(transactionService.getTransactionById(transactionId)).thenReturn(transaction);

        // Llamar al método del controlador
        ResponseEntity<Transaction> response = transactionRestController.getTransactionById(transactionId);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreateUpdateTransaction() {
        // Datos simulados
        Transaction transactionParam = new Transaction();
        transactionParam.setTransactionType("compra");
        Transaction savedTransaction = new Transaction();

        // Simular comportamiento del servicio
        when(transactionService.createUpdateTransaction(transactionParam)).thenReturn(savedTransaction);

        // Llamar al método del controlador
        ResponseEntity<TransactionResponse> response = transactionRestController.createUpdateTransaction(transactionParam);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testCreateTransactionByCompany() {
        // Datos simulados
        Transaction transactionParam = new Transaction();
        transactionParam.setTransactionType("compra");
        Transaction savedTransaction = new Transaction();

        // Simular comportamiento del servicio
        when(transactionService.createUpdateTransaction(transactionParam)).thenReturn(savedTransaction);

        // Llamar al método del controlador
        ResponseEntity<TransactionResponse> response = transactionRestController.createTransactionByCompany(1L, transactionParam);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testCreateUpdateTransactionInvalidTransactionType() {
        // Datos simulados
        Transaction transactionParam = new Transaction();
        transactionParam.setTransactionType("invalidType");

        // Llamar al método del controlador
        ResponseEntity<TransactionResponse> response = transactionRestController.createUpdateTransaction(transactionParam);

        // Validar respuesta
        String errorMessage = "El tipo de transacción no es válido, los posibles valores son (compra, venta)";
        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody().getError());
    }

    @Test
    void testCreateTransactionByCompanyInvalidTransactionType() {
        // Datos simulados
        Transaction transactionParam = new Transaction();
        transactionParam.setTransactionType("invalidType");

        // Llamar al método del controlador
        ResponseEntity<TransactionResponse> response = transactionRestController.createTransactionByCompany(1L, transactionParam);

        // Validar respuesta
        String errorMessage = "El tipo de transacción no es válido, los posibles valores son (compra, venta)";
        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody().getError());
    }

    @Test
    void testUpdateTransaction() {
        // Datos simulados
        Transaction transactionParam = new Transaction();
        transactionParam.setTransactionType("compra");
        Transaction savedTransaction = new Transaction();

        // Simular comportamiento del servicio
        when(transactionService.getTransactionById(any())).thenReturn(savedTransaction);
        when(transactionService.createUpdateTransaction(any())).thenReturn(savedTransaction);

        // Llamar al método del controlador
        ResponseEntity<TransactionResponse> response = transactionRestController.updateTransaction(1L, transactionParam);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testUpdateTransactionInvalidTransactionType() {
        // Datos simulados
        Transaction transactionParam = new Transaction();
        transactionParam.setTransactionType("invalidType");

        // Llamar al método del controlador
        ResponseEntity<TransactionResponse> response = transactionRestController.updateTransaction(1L, transactionParam);

        // Validar respuesta
        String errorMessage = "El tipo de transacción no es válido, los posibles valores son (compra, venta)";
        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody().getError());
    }

    @Test
    void testDeleteTransaction() {
        // Datos simulados
        Long transactionId = 1L;
        Transaction transaction = new Transaction();

        // Simular comportamiento del servicio
        Mockito.doNothing().when(transactionService).deleteTransaction(any());

        // Llamar al método del controlador
        ResponseEntity<HttpStatus> response = transactionRestController.deleteTransaction(transactionId);

        // Validar respuesta
        assertNotNull(response);
    }

    @Test
    void testGetTransactionByIdNotFound() {
        // Datos simulados
        Long transactionId = 1L;

        // Simular comportamiento del servicio
        when(transactionService.getTransactionById(transactionId)).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<Transaction> response = transactionRestController.getTransactionById(transactionId);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testGetTop10CompaniesByTransactions() {
        // Datos simulados
        List<CompanyRankingDTO> ranking = Arrays.asList(new CompanyRankingDTO(1L, 100L, 50L));

        // Simular comportamiento del servicio
        when(transactionService.getTop10CompaniesByTransactions()).thenReturn(ranking);

        // Llamar al método del controlador
        List<CompanyRankingDTO> response = transactionRestController.getTop10CompaniesByTransactions();

        // Validar respuesta
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(100, response.get(0).getTotalCompradas());
        assertEquals(50, response.get(0).getTotalVendidas());
    }

    @Test
    void testGetTop10UsersByTransactions() {
        // Datos simulados
        List<UserRankingDTO> ranking = Arrays.asList(new UserRankingDTO(1L, 100L, 50L));

        // Simular comportamiento del servicio
        when(transactionService.getTop10UsersByTransactions()).thenReturn(ranking);

        // Llamar al método del controlador
        List<UserRankingDTO> response = transactionRestController.getTop10UsersByTransactions();

        // Validar respuesta
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(100, response.get(0).getTotalCompradas());
        assertEquals(50, response.get(0).getTotalVendidas());
    }

    @Test
    void testGetTransactionSummaryCompany() {
        // Datos simulados
        List<TransactionSummaryCompanyDTO> summary = Arrays.asList(new TransactionSummaryCompanyDTO(1L, 100L, 50L, 15000L, 2600L));

        // Simular comportamiento del servicio
        when(transactionService.getTransactionSummaryByCompany()).thenReturn(summary);

        // Llamar al método del controlador
        List<TransactionSummaryCompanyDTO> response = transactionRestController.getTransactionSummaryCompany();

        // Validar respuesta
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(100, response.get(0).getTotalCompradas());
        assertEquals(50, response.get(0).getTotalVendidas());
    }

    @Test
    void testGetTransactionSummaryUser() {
        // Datos simulados
        List<TransactionSummaryUserDTO> summary = Arrays.asList(new TransactionSummaryUserDTO(1L, 100L, 50L, 15000L, 2600L));

        // Simular comportamiento del servicio
        when(transactionService.getTransactionSummaryByUser()).thenReturn(summary);

        // Llamar al método del controlador
        List<TransactionSummaryUserDTO> response = transactionRestController.getTransactionSummaryUser();

        // Validar respuesta
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(100, response.get(0).getTotalCompradas());
        assertEquals(50, response.get(0).getTotalVendidas());
    }

    @Test
    void testGetTransactionsBetweenDates() {
        // Datos simulados
        Company company = new Company(1L, "name company 1");
        User user = new User(1L, "name user 1");
        List<Transaction> transactions = Arrays.asList(new Transaction(1L,user, company, "compra", 100L, 50L, new Date()));

        // Simular comportamiento del servicio
        when(transactionService.getTransactionsBetweenDates(any(), any())).thenReturn(transactions);

        // Llamar al método del controlador
        List<Transaction> response = transactionRestController.getTransactionsBetweenDates(new Date(), new Date());

        // Validar respuesta
        assertNotNull(response);
        assertEquals(1, response.size());
    }
}
