package service;
import java.util.Random;
import java.util.LinkedList;
import entity.User;
public class UserServices {
    Random rand = new Random();
    static LinkedList<User> users = new LinkedList<>();
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
    }
