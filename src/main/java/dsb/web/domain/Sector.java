package dsb.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sector implements Comparable<Sector> {

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

    @Override
    public int compareTo(Sector sector) {
        return (int)(this.sectorId - sector.getSectorId());
    }


    @Override
    public String toString() {
        return sectorName;
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
