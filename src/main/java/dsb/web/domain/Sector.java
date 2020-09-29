package dsb.web.domain;

import javax.persistence.*;

@Entity
public class Sector implements Comparable<Sector> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sector_generator")
    @SequenceGenerator(name="sector_generator", initialValue = 1000, sequenceName = "sector_seq")
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
