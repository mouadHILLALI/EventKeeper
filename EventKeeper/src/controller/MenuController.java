package controller;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import service.UserServices;
import entity.User;
import service.EventServices;
import entity.Event;
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
            String username = scanner.nextLine().toLowerCase().trim();
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
            String username = scanner.nextLine().toLowerCase().trim();
            User user = UserServices.signIn(username);
           if (username.equals("admin")){
               adminController();
           }
        } catch (Exception e) {
            System.err.println("An error occurred during sign-in: " + e.getMessage());
        } finally {
            scanner.close();
        }

    }
    public void adminController(){
        System.out.println("1.Events Management\n2.Participants Management\n3.Stats\n0.return to main menu");
        try {
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();
            switch (userChoice){
                case 1 :
                    eventController();
                    break;
                case 2 :
                    participantManagementController();
                    break;
                case 3 :
                    statsController();
                    break;
                case 0 :
                    menuDisplay();
                    break;
                default:
                    adminController();
                    break;
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            adminController();
        }
    }
    public void eventController(){
        EventServices eventServices = new EventServices();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        int userChoice;

        do {
            System.out.println("1.Add Event\n2.Edit Event\n3.Delete Event\n4.Display all Events\n5.Search for an event\n0.Return to Admin Menu");
            userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    System.out.println("Enter event name: ");
                    String name = scanner.nextLine().toLowerCase().trim();
                    System.out.println("Enter event description: ");
                    String description = scanner.nextLine().toLowerCase().trim();
                    System.out.println("Enter event type: ");
                    String type = scanner.nextLine().toLowerCase().trim();
                    System.out.println("Enter event location: ");
                    String location = scanner.nextLine().toLowerCase().trim();
                    System.out.println("Enter event date dd/MM/yyyy: ");
                    String dateStr = scanner.nextLine();
                    try {
                        date = sdf.parse(dateStr);
                        eventServices.addEvent(name, description, type, location, date);
                        System.out.println("You have successfully added the event.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in the format dd/MM/yyyy.");
                    }
                    break;

                case 2:
                    System.out.println("Enter event ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Event event = eventServices.getEvent(id);
                    if (event != null) {
                        System.out.println("Enter event new name (leave blank to keep '" + event.getName() + "'): ");
                        name = scanner.nextLine().toLowerCase().trim();
                        name = name.isEmpty() ? event.getName() : name;

                        System.out.println("Enter event new description (leave blank to keep '" + event.getDescription() + "'): ");
                        description = scanner.nextLine().toLowerCase().trim();
                        description = description.isEmpty() ? event.getDescription() : description;

                        System.out.println("Enter event new type (leave blank to keep '" + event.getType() + "'): ");
                        type = scanner.nextLine().toLowerCase().trim();
                        type = type.isEmpty() ? event.getType() : type;

                        System.out.println("Enter event new location (leave blank to keep '" + event.getLocation() + "'): ");
                        location = scanner.nextLine().toLowerCase().trim();
                        location = location.isEmpty() ? event.getLocation() : location;

                        System.out.println("Enter event new date (leave blank to keep '" + sdf.format(event.getDate()) + "'): ");
                        dateStr = scanner.nextLine();
                        if (dateStr.isEmpty()) {
                            date = event.getDate();
                        } else {
                            try {
                                date = sdf.parse(dateStr);
                            } catch (ParseException e) {
                                System.out.println("Invalid date format. Please enter the date in the format dd/MM/yyyy.");
                                break;
                            }
                        }
                        eventServices.editEvent(id, name, description, type, location, date);
                        System.out.println("Event successfully updated.");
                    } else {
                        System.out.println("Event does not exist.");
                    }
                    break;

                case 3:
                    System.out.println("Enter event ID: ");
                    int idToDelete = scanner.nextInt();
                    scanner.nextLine();
                    Event eventToDelete = eventServices.getEvent(idToDelete);
                    if (eventToDelete != null) {
                        eventServices.deleteEvent(idToDelete);
                        System.out.println("Event successfully deleted.");
                    } else {
                        System.out.println("Event does not exist.");
                    }
                    break;

                case 4:
                    System.out.printf("%-10s %-20s %-30s %-15s %-20s %-15s\n",
                            "Event ID", "Event Name", "Event Description", "Event Type", "Event Location", "Event Date");

                    LinkedList<Event> events = eventServices.getEvents();
                    for (Event e : events) {
                        System.out.printf("%-10s %-20s %-30s %-15s %-20s %-15s\n",
                                e.getId(),
                                e.getName(),
                                e.getDescription(),
                                e.getType(),
                                e.getLocation(),
                                sdf.format(e.getDate())
                        );
                    }
                    break;

                case 5:
                    System.out.println("Enter event ID: ");
                    int idToSearch = scanner.nextInt();
                    scanner.nextLine();
                    Event eventToSearch = eventServices.getEvent(idToSearch);
                    if (eventToSearch != null) {
                        System.out.printf("%-10s %-20s %-30s %-15s %-20s %-15s\n",
                                "Event ID", "Event Name", "Event Description", "Event Type", "Event Location", "Event Date");
                        System.out.printf("%-10s %-20s %-30s %-15s %-20s %-15s\n",
                                eventToSearch.getId(),
                                eventToSearch.getName(),
                                eventToSearch.getDescription(),
                                eventToSearch.getType(),
                                eventToSearch.getLocation(),
                                sdf.format(eventToSearch.getDate())
                        );
                    } else {
                        System.out.println("Event does not exist.");
                    }
                    break;

                case 0:
                    System.out.println("Returning to Admin Menu...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (userChoice != 0);


    }

    public void participantManagementController(){
        int userChoice = -1;
        Scanner scanner = new Scanner(System.in);
      do {
          System.out.println("1.Add Participant\n2.Remove a Participant\n3.Display all Participants\n0.Return to Main Menu");
            try {
                userChoice = scanner.nextInt();
                scanner.nextLine();
                switch (userChoice){
                    case 1 :
                        System.out.println("Enter Participant Username: ");
                        String name = scanner.nextLine().toLowerCase().trim();
                        UserServices userServices = new UserServices();
                        if (!name.isEmpty()) {
                        userServices.signUp(name);
                        System.out.println("You have successfully added the participant");
                        }else{
                            System.out.println("Please enter a valid username.");
                            participantManagementController();
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Enter Participant ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            UserServices userServices1 = new UserServices();
                            User user = userServices1.getUser(id);
                            if (user != null) {
                                userServices1.deleteUser(id);
                                System.out.println("User deleted successfully.");
                            } else {
                                System.out.println("User does not exist.");
                            }
                        } catch (Exception e) {
                            System.err.println("An unexpected error occurred: " + e.getMessage());
                        }
                        break;

                    case 3 :
                        System.out.printf("%-10s %-20s\n",
                                "Participant ID", "Participant Name");
                        UserServices userServices2 = new UserServices();
                        LinkedList<User> user = userServices2.getUsers();
                        for (User u : user) {
                            System.out.printf("%-10s %-20s\n",
                                   u.getId(),
                                    u.getUsername()
                            );
                        }
                        break;
                    case 0 :
                        System.out.println("Returning to Main Menu...");
                        adminController();
                        break;
                }
            }catch (Exception e){
                System.err.println("An error occurred: " + e.getMessage());
            }
      }while (userChoice!=0);
    }
    public void statsController(){
        System.out.println("Enter your stats: ");
    }
}
