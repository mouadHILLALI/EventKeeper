package entity;

public class Participant extends User {
    private String role ;
    public Participant(int id, String username, String role ) {
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
