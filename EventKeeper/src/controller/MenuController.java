package controller;
import java.util.Scanner;
import service.UserServices;
import entity.User;
public class MenuController {
    public void menuDisplay(){
        int userChoice ;
          System.out.println(" __      __       .__                                  __           ___________                    __   ____  __.                                 \n" +
                "/  \\    /  \\ ____ |  |   ____  ____   _____   ____   _/  |_  ____   \\_   _____/__  __ ____   _____/  |_|    |/ _|____   ____ ______   ___________ \n" +
                "\\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\  \\   __\\/  _ \\   |    __)_\\  \\/ // __ \\ /    \\   __\\      <_/ __ \\_/ __ \\\\____ \\_/ __ \\_  __ \\\n" +
                " \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/   |  | (  <_> )  |        \\\\   /\\  ___/|   |  \\  | |    |  \\  ___/\\  ___/|  |_> >  ___/|  | \\/\n" +
                "  \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >  |__|  \\____/  /_______  / \\_/  \\___  >___|  /__| |____|__ \\___  >\\___  >   __/ \\___  >__|   \n" +
                "       \\/       \\/          \\/            \\/     \\/                         \\/           \\/     \\/             \\/   \\/     \\/|__|        \\/       ");
          System.out.println("1.Sign in\n2.Sign up\n0.Quit");
          try {
              Scanner scanner = new Scanner(System.in);
              userChoice = scanner.nextInt();
              switch (userChoice) {
                  case 1:
                      signInController();
                      menuDisplay();
                      break;
                  case 2:
                      signUpController();
                      menuDisplay();
                      break;
                  case 0:
                      System.out.println("welcome");
                      break;
                  default:
                      menuDisplay();
              }
          } catch (Exception e) {
              System.out.println("Invalid input. Please enter a valid choice.");
              menuDisplay();
          }

    }
    public void signUpController(){
        try {
            UserServices userServices = new UserServices();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username: ");
            String username = scanner.nextLine().toLowerCase();
            if (username.isEmpty()) {
                System.out.println("Please enter a valid username.");
                signUpController();
            }
            userServices.signUp(username);
            System.out.println("you have successfully signed up ");
        } catch (Exception e) {
            signUpController();
        }
    }
    public void signInController() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your username: ");
            String username = scanner.nextLine().toLowerCase();
            User user = UserServices.signIn(username);
            System.out.println("You have successfully signed in, " + user.getUsername() + " (User ID: " + user.getId() + ")");
        } catch (Exception e) {
            System.err.println("An error occurred during sign-in: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
