package service;
import java.util.Random;
import java.util.LinkedList;
import entity.User;
public class UserServices {
    Random rand = new Random();
    static LinkedList<User> users = new LinkedList<>();
    entity.User User ;
    public static User signIn(String username) {
        try {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("cannot signIn", e);
        }
    }
    public void signUp(String username){
        try {
            int id = rand.nextInt(10000);
            User user = new User(id, username);
            users.add(user);
        } catch (Exception e) {
            System.out.println("Invalid input.");
            signUp(username);
        }
    }
    public User getUser(int id) {
        User user = users.get(id);
        if (user !=null) {
            return user;
        }
        return null;
    }
    public void deleteUser(int id) {
        User user = getUser(id);
        if (user != null) {
            users.remove(user);
            System.out.println("User removed successfully.");
        } else {
            System.out.println("No user was found.");
        }
    }

    public LinkedList<User> getUsers() {
        return users;
    }
}
