package co.com.bvc.test2023.controller;

import co.com.bvc.test2023.model.Company;
import co.com.bvc.test2023.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyRestController {

    private static Logger logger = LoggerFactory.getLogger(CompanyRestController.class);

    @Autowired
    private ICompanyService companyService;

    /**
     * Method to get all the companies
     * @return entity your body is list of companies
     */
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        logger.info("llega al método getAllCompanies...");
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    /**
     * Method to get one company for id
     * @param id company
     * @return entity your body contain company
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
        logger.info("llega al método getCompanyById...");
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to create new company or update a existing
     * @param company
     * @return entity your body contain company
     */
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        logger.info("llega al método createCompany...");
        Company savedCompany = companyService.createUpdateCompany(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    /**
     * Method to update company
     * @param id company
     * @param company
     * @return entity your body contain company
     */
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id, @RequestBody Company company) {
        logger.info("llega al método updateCompany...");
        Company existingCompany = companyService.getCompanyById(id);
        if (existingCompany != null) {
            existingCompany.setName(company.getName());
            Company updatedCompany = companyService.createUpdateCompany(existingCompany);
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to delete one company for id
     * @param id company
     * @return entity with http status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") Long id) {
        logger.info("llega al método deleteCompany...");
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
