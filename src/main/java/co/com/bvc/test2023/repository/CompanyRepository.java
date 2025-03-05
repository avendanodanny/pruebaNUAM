package co.com.bvc.test2023.repository;

import co.com.bvc.test2023.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
