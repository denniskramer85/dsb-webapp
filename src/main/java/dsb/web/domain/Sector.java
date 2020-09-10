package dsb.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sector {

    @Id
    @GeneratedValue
    private int sectorId;
    private String sectorName;

    public Sector(int sectorId, String sectorName) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
    }

    public Sector() {
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
}
