package controller;
import java.util.Scanner;
public class Menu {
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
                      System.out.println("welcome");
                      break;
                  case 2:
                      System.out.println("welcome");
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

}
