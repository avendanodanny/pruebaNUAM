package co.com.bvc.test2023.service;

import co.com.bvc.test2023.model.Company;

import java.util.List;

public interface ICompanyService {

    public List<Company> getAllCompanies();

    public Company getCompanyById(Long id);

    public Company createUpdateCompany(Company company);

    public void deleteCompany(Long id);

}
