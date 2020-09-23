package dsb.web.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends User {

    @ManyToOne
    private Role role;

    public Employee(int userID, String initials, String inserts, String surname, String username, String password, Role role) {
        super(userID, initials, inserts, surname, username, password);
        this.role = role;
    }

    public Employee() {
        super();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
