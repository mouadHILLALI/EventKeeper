package entity;

public class Admin extends User {
    private String role ;
    public Admin(int id, String username,String role ) {
        super(id, username);
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
