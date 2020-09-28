package dsb.web.controller.rest;

import dsb.web.domain.Company;
import dsb.web.repository.CompanyRepository;
import dsb.web.service.AddAccountHolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kvks")
public class BussinessDetailsController{

    private CompanyRepository companyRepository;

    public BussinessDetailsController(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }

    @GetMapping(value = "/{kvk}")
    public String findCompanyByKVK(@PathVariable("kvk") String kvkno){
        Company comp = companyRepository.findCompanyByKVKno(kvkno);
        if (comp != null) {
            System.out.println(comp);
            return (comp.getName() + "," + comp.getBTWno() + "," + comp.getSector().getSectorId());
        }
        return null;
    }
}