package entity;

public class Admin extends User {
    private String role ;
    public Admin(String role ) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
