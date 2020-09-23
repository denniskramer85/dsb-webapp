package dsb.web.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private int companyId;
    private String name;
    private String KVKno;
    private String BTWno;
    @OneToMany
    private List<SMEAccount> accounts;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="accountManager", nullable=false)
    private Employee accountManager;
    @ManyToOne
    private Sector sector;

    public Company(int companyId, String name, String KVKno, String BTWno, List<SMEAccount> accounts, Employee accountManager, Sector sector) {
        this.companyId = companyId;
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
        this.accounts = accounts;
        this.accountManager = accountManager;
        this.sector = sector;
    }

    public Company(String name, String KVKno, String BTWno, Employee accountManager, Sector sector) {
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
        this.accountManager = accountManager;
        this.sector = sector;
    }

    public Company(String name, String KVKno, String BTWno, Sector sector) {
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
        this.sector = sector;
    }

    public Company() {
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", KVKno='" + KVKno + '\'' +
                ", BTWno='" + BTWno + '\'' +
                ", accountManager=" + accountManager +
                ", sector=" + sector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getName().equals(company.getName()) &&
                getKVKno().equals(company.getKVKno()) &&
                getBTWno().equals(company.getBTWno()) &&
                accounts.equals(company.accounts);
    }


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKVKno() {
        return KVKno;
    }

    public void setKVKno(String KVKno) {
        this.KVKno = KVKno;
    }

    public String getBTWno() {
        return BTWno;
    }

    public void setBTWno(String BTWno) {
        this.BTWno = BTWno;
    }

    public List<SMEAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<SMEAccount> accounts) {
        this.accounts = accounts;
    }

    public Employee getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Employee accountManager) {
        this.accountManager = accountManager;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
