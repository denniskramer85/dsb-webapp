package dsb.web.controller.beans;

import dsb.web.service.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@FieldsValueMatch(field = "password", fieldMatch = "password2")
@Component
public class SignUpBean {


    @Size(min=2, message = "Minimaal 2 letters")
    private String surname;

    private String inserts;

    @NotEmptyFieldConstraint
    private String initials;

    @NotEmptyFieldConstraint
    private String street;

    @NotEmptyFieldConstraint
    @IntegerConstraint
    private String houseNumberString;
    private Integer houseNumber;

    private String affixes;

    @NotEmptyFieldConstraint
    @ZipCodeConstraint
    private String zipCode;

    @NotEmptyFieldConstraint
    private String city;

    @NotEmptyFieldConstraint
    @IntegerConstraint
    @SocSecNoConstraint
    private String socialSecurityNoString;
    private Integer socialSecurityNo;

    @Size(min=6, message = "Minimaal 6 tekens")
    @UsernameOccupiedConstraint
    private String username;

    @NotEmptyFieldConstraint
    private String password;

    @NotEmptyFieldConstraint
    private String password2;

    @Autowired
    public SignUpBean() {
    }


    public Integer checkIfInteger(String ageString) {


        try {
            return Integer.parseInt(ageString);
        } catch(NumberFormatException e){
            return null;
        }
    }


    public void nameStyler() {
        //styling of initials
        String[] asArray = initials.trim().replaceAll("[^a-zA-Z]", "")
                .toUpperCase().split("");
        initials = String.join(".", asArray) + ".";

        //styling of inserts
        inserts = inserts.toLowerCase();

        //styling of surname
        String mid = surname.toLowerCase();
        surname = mid.substring(0,1).toUpperCase() + mid.substring(1);
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

    public String getHouseNumberString() {
        setHouseNumber(checkIfInteger(houseNumberString));
        setSocialSecurityNo(checkIfInteger(socialSecurityNoString));
        return houseNumberString;
    }

    public void setHouseNumberString(String houseNumberString) {
        this.houseNumberString = houseNumberString;
    }

    public String getSocialSecurityNoString() {
        setSocialSecurityNo(checkIfInteger(socialSecurityNoString));
        return socialSecurityNoString;
    }

    public void setSocialSecurityNoString(String socialSecurityNoString) {
        this.socialSecurityNoString = socialSecurityNoString;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "SignUpBean{" +
                "surname='" + surname + '\'' +
                ", inserts='" + inserts + '\'' +
                ", initials='" + initials + '\'' +
                '}';
    }
}
