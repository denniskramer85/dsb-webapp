package dsb.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private int roleID;
    private String roleName;

    public Role(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public Role() {
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
