package dsb.web.controller.beans;

import dsb.web.domain.Account;
import dsb.web.domain.Address;

import java.util.List;

public class CustomerBean {

    private String surname;
    private String inserts;
    private String initials;
    private String street;
    private Integer houseNumber;
    private String affixes;
    private String zipCode;
    private String city;
    private Integer socialSecurityNo;
    private String username;
    private String password;

    public CustomerBean(String surname, String inserts, String initials, String street, Integer houseNumber, String affixes, String zipCode, String city, Integer socialSecurityNo, String username, String password) {
        this.surname = surname;
        this.inserts = inserts;
        this.initials = initials;
        this.street = street;
        this.houseNumber = houseNumber;
        this.affixes = affixes;
        this.zipCode = zipCode;
        this.city = city;
        this.socialSecurityNo = socialSecurityNo;
        this.username = username;
        this.password = password;
    }

    public CustomerBean() {
    }

    @Override
    public String toString() {
        return "CustomerBean{" +
                "surname='" + surname + '\'' +
                ", inserts='" + inserts + '\'' +
                ", initials='" + initials + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", affixes='" + affixes + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", socialSecurityNo=" + socialSecurityNo +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInserts() {
        return inserts;
    }

    public void setInserts(String inserts) {
        this.inserts = inserts;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
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

    public Integer getSocialSecurityNo() {
        return socialSecurityNo;
    }

    public void setSocialSecurityNo(Integer socialSecurityNo) {
        this.socialSecurityNo = socialSecurityNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
