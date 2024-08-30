package service;
import java.util.Random;
import entity.User;
public class UserServices {
    Random rand = new Random();
    public void signIn(){


    }
    public void signUp(String username){
        int id = rand.nextInt(10000);
        User user = new User(id, username);

    }
}
