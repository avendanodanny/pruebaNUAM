package co.com.bvc.test2023.dto;

import co.com.bvc.test2023.model.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransactionResponse {

    private String error;
    private Transaction transaction;

    public TransactionResponse(String error, Transaction transaction){
        this.error = error;
        this.transaction = transaction;
    }

}
