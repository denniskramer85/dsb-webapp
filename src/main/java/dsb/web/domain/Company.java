package dsb.web.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    public Company(int companyId, String name, String KVKno, String BTWno, List<SMEAccount> accounts) {
        this.companyId = companyId;
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
        this.accounts = accounts;
    }

    public Company(String name, String KVKno, String BTWno) {
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
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


    public int getcompanyId() {
        return companyId;
    }

    public void setcompanyId(int companyId) {
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
}
