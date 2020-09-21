package dsb.web.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue
    private int userID;
    private String initials;
    private String inserts;
    private String surname;
    private String username;
    private String password;

    public User(int userID, String initials, String inserts, String surname, String username, String password) {
        this.userID = userID;
        this.initials = initials;
        this.inserts = inserts;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public User(String initials, String inserts, String surname, String username, String password) {
        this.userID = 0;
        this.initials = initials;
        this.inserts = inserts;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getInserts() {
        return inserts;
    }

    public void setInserts(String inserts) {
        this.inserts = inserts;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
