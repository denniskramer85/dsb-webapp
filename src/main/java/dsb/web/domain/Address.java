package dsb.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private int addressID;
    private String street;
    private int houseNumber;
    private String affixes;
    private String zipCode;
    private String city;

    public Address(int addressID, String street, int houseNumber, String affixes, String zipCode, String city) {
        this.addressID = 0;
        this.street = street;
        this.houseNumber = houseNumber;
        this.affixes = affixes;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Address(String street, int houseNumber, String affixes, String zipCode, String city) {
        this(0, street, houseNumber, affixes, zipCode, city);
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", affixes='" + affixes + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String printWholeAddress () {
        return String.format("%s %s %s\n%s %s", street, houseNumber, affixes, zipCode, city);
    }













    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getHouseNumber() == address.getHouseNumber() &&
                getStreet().equals(address.getStreet()) &&
                getAffixes().equals(address.getAffixes()) &&
                getZipCode().equals(address.getZipCode()) &&
                getCity().equals(address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressID(), getStreet(), getHouseNumber(), getAffixes(), getZipCode(), getCity());
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAffixes() {
        return affixes;
    }

    public void setAffixes(String affixes) {
        this.affixes = affixes;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
