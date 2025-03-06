package co.com.bvc.test2023.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRankingDTO {
    private Long userId;
    private Long totalCompradas;
    private Long totalVendidas;
}
