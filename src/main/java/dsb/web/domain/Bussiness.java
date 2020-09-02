package dsb.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bussiness {

    @Id
    @GeneratedValue
    private int bussinessId;
    private String name;
    private String KVKno;
    private String BTWno;

    public Bussiness(int bussinessId, String name, String KVKno, String BTWno) {
        this.bussinessId = bussinessId;
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
    }

    public Bussiness(String name, String KVKno, String BTWno) {
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
    }

    public Bussiness() {
    }

    @Override
    public String toString() {
        return "Bussiness{" +
                "bussinessId=" + bussinessId +
                ", name='" + name + '\'' +
                ", KVKno='" + KVKno + '\'' +
                ", BTWno='" + BTWno + '\'' +
                '}';
    }

    public int getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(int bussinessId) {
        this.bussinessId = bussinessId;
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
