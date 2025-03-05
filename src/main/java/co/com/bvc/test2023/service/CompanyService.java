package co.com.bvc.test2023.service;

import co.com.bvc.test2023.model.Company;
import co.com.bvc.test2023.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Method to get all the companies
     * @return list of companies
     */
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    /**
     * Method to get one company for id
     * @return company
     */
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    /**
     * Method to create new company or update a existing
     * @return create or update company
     */
    public Company createUpdateCompany(Company company) {
        return companyRepository.save(company);
    }

    /**
     * Method to delete one company for id
     */
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
