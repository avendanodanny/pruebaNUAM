package co.com.bvc.test2023.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRankingDTO {
    private Long companyId;
    private Long totalCompradas;
    private Long totalVendidas;
}
