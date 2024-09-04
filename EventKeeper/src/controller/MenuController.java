package controller;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import entity.Reservation;
import service.ReservationServices;
import service.UserServices;
import entity.User;
import service.EventServices;
import entity.Event;
public class MenuController {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public void menuDisplay(){
        String asciiArt = " __      __       .__                                  __           ___________                    __   ____  __.                                 \n" +
                "/  \\    /  \\ ____ |  |   ____  ____   _____   ____   _/  |_  ____   \\_   _____/__  __ ____   _____/  |_|    |/ _|____   ____ ______   ___________ \n" +
                "\\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\  \\   __\\/  _ \\   |    __)_\\  \\/ // __ \\ /    \\   __\\      <_/ __ \\_/ __ \\\\____ \\_/ __ \\_  __ \\\n" +
                " \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/   |  | (  <_> )  |        \\\\   /\\  ___/|   |  \\  | |    |  \\  ___/\\  ___/|  |_> >  ___/|  | \\/\n" +
                "  \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >  |__|  \\____/  /_______  / \\_/  \\___  >___|  /__| |____|__ \\___  >\\___  >   __/ \\___  >__|   \n" +
                "       \\/       \\/          \\/            \\/     \\/                         \\/           \\/     \\/             \\/   \\/     \\/|__|        \\/       ";

        // Display the ASCII art
        JOptionPane.showMessageDialog(null, asciiArt, "Welcome", JOptionPane.INFORMATION_MESSAGE);

        String userChoice = JOptionPane.showInputDialog("1. Sign in\n2. Sign up\n0. Quit");
        if (userChoice != null) {
            int choice = Integer.parseInt(userChoice);
            try {
                Scanner scanner = new Scanner(System.in);
                switch (choice) {
                    case 1:
                        signInController();
                        break;
                    case 2:
                        signUpController();
                        menuDisplay();
                        break;
                    case 0:
                        JOptionPane.showInputDialog("welcome");
                        break;
                    default:
                        menuDisplay();
                }
            } catch (Exception e) {
                JOptionPane.showInputDialog("Invalid input. Please enter a valid choice.");
                menuDisplay();
            }
        }


    }
    public void signUpController() {
        UserServices userServices = new UserServices();
        String username = JOptionPane.showInputDialog("Enter your username: ");

        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid username.");
            return;
        }

        try {
            userServices.signUp(username.toLowerCase().trim());
            JOptionPane.showMessageDialog(null, "You have successfully signed up.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred during sign-up. Please try again.");
            e.printStackTrace();
        }
    }

    public void signInController() {
        try {
            // Prompt for username input
            String username = JOptionPane.showInputDialog("Enter your username:");

            // Handle the case where the user cancels the input dialog
            if (username == null || username.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;  // Exit the method if no valid input is provided
            }

            // Attempt to sign in the user
            User user = UserServices.signIn(username.toLowerCase().trim());

            // Check if the user is an admin
            if (username.equalsIgnoreCase("admin")) {
                adminController();  // Direct admin to the admin menu
            }
            // If user exists, direct to participant menu
            else if (user != null) {
                participantController(user);
            }
            // If user does not exist, show an error message
            else {
                JOptionPane.showMessageDialog(null, "Invalid username. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            // Handle any unexpected errors during the sign-in process
            JOptionPane.showMessageDialog(null, "An error occurred during sign-in: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  // Optionally log the error for debugging purposes
        }
    }

    public void adminController() {
        try {
            // Display menu options to the admin and capture their choice
            String userChoiceStr = JOptionPane.showInputDialog(
                    "1. Events Management\n" +
                            "2. Participants Management\n" +
                            "3. Stats\n" +
                            "0. Return to Main Menu\n\n" +
                            "Enter your choice:");

            // If the user cancels the input or provides invalid input, handle it gracefully
            if (userChoiceStr == null) {
                return;  // Exit the method if the user cancels the input
            }

            // Parse the user choice to an integer
            int userChoice;
            try {
                userChoice = Integer.parseInt(userChoiceStr.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                adminController();  // Restart the admin controller on invalid input
                return;  // Exit to avoid further processing
            }

            // Execute the appropriate action based on the user's choice
            switch (userChoice) {
                case 1:
                    eventController();
                    break;
                case 2:
                    participantManagementController();
                    break;
                case 3:
                    statsController();
                    break;
                case 0:
                    menuDisplay();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    adminController();  // Restart the admin controller on invalid option
                    break;
            }
        } catch (Exception e) {
            // Log and display any unexpected errors
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  // Optionally log the error for debugging purposes
            adminController();  // Restart the admin controller after handling the error
        }
    }

    public void eventController() {
        EventServices eventServices = new EventServices();
        Date date;
        int userChoice = 0;

        do {
            // Display the event management options and capture the user's choice
            String userChoiceStr = JOptionPane.showInputDialog(
                    "1. Add Event\n" +
                            "2. Edit Event\n" +
                            "3. Delete Event\n" +
                            "4. Display all Events\n" +
                            "5. Search for an event\n" +
                            "0. Return to Admin Menu\n\n" +
                            "Enter your choice:");

            // Handle null input (e.g., if the user cancels the input dialog)
            if (userChoiceStr == null) {
                return;  // Exit the method if the user cancels the input
            }

            try {
                userChoice = Integer.parseInt(userChoiceStr.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;  // Restart the loop if the input is invalid
            }

            switch (userChoice) {
                case 1:
                    String name = JOptionPane.showInputDialog("Enter event name:").toLowerCase().trim();
                    String description = JOptionPane.showInputDialog("Enter event description:").toLowerCase().trim();
                    String type = JOptionPane.showInputDialog("Enter event type:").toLowerCase().trim();
                    String location = JOptionPane.showInputDialog("Enter event location:").toLowerCase().trim();
                    String dateStr = JOptionPane.showInputDialog("Enter event date (dd/MM/yyyy):");

                    try {
                        date = sdf.parse(dateStr);
                        eventServices.addEvent(name, description, type, location, date);
                        JOptionPane.showMessageDialog(null, "Event successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Invalid date format. Please enter the date in the format dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 2:
                    String idStr = JOptionPane.showInputDialog("Enter event ID:");
                    if (idStr == null || idStr.trim().isEmpty()) {
                        break;  // If no ID is entered, exit the case
                    }

                    int id = Integer.parseInt(idStr.trim());
                    Event event = eventServices.getEvent(id);
                    if (event != null) {
                        name = JOptionPane.showInputDialog("Enter event new name (leave blank to keep '" + event.getName() + "'):");
                        description = JOptionPane.showInputDialog("Enter event new description (leave blank to keep '" + event.getDescription() + "'):");
                        type = JOptionPane.showInputDialog("Enter event new type (leave blank to keep '" + event.getType() + "'):");
                        location = JOptionPane.showInputDialog("Enter event new location (leave blank to keep '" + event.getLocation() + "'):");
                        dateStr = JOptionPane.showInputDialog("Enter event new date (leave blank to keep '" + sdf.format(event.getDate()) + "'):");

                        name = name.isEmpty() ? event.getName() : name.toLowerCase().trim();
                        description = description.isEmpty() ? event.getDescription() : description.toLowerCase().trim();
                        type = type.isEmpty() ? event.getType() : type.toLowerCase().trim();
                        location = location.isEmpty() ? event.getLocation() : location.toLowerCase().trim();

                        if (dateStr.isEmpty()) {
                            date = event.getDate();
                        } else {
                            try {
                                date = sdf.parse(dateStr);
                            } catch (ParseException e) {
                                JOptionPane.showMessageDialog(null, "Invalid date format. Please enter the date in the format dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        }
                        eventServices.editEvent(id, name, description, type, location, date);
                        JOptionPane.showMessageDialog(null, "Event successfully updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Event does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 3:
                    idStr = JOptionPane.showInputDialog("Enter event ID:");
                    if (idStr == null || idStr.trim().isEmpty()) {
                        break;  // If no ID is entered, exit the case
                    }

                    int idToDelete = Integer.parseInt(idStr.trim());
                    Event eventToDelete = eventServices.getEvent(idToDelete);
                    if (eventToDelete != null) {
                        eventServices.deleteEvent(idToDelete);
                        JOptionPane.showMessageDialog(null, "Event successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Event does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 4:
                    StringBuilder eventsList = new StringBuilder("Event ID | Event Name | Event Description | Event Type | Event Location | Event Date\n");
                    LinkedList<Event> events = eventServices.getEvents();
                    for (Event e : events) {
                        eventsList.append(String.format("%-10d | %-20s | %-30s | %-15s | %-20s | %-15s\n",
                                e.getId(), e.getName(), e.getDescription(), e.getType(), e.getLocation(), sdf.format(e.getDate())));
                    }
                    JOptionPane.showMessageDialog(null, eventsList.toString(), "All Events", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 5:
                    idStr = JOptionPane.showInputDialog("Enter event ID:");
                    if (idStr == null || idStr.trim().isEmpty()) {
                        break;  // If no ID is entered, exit the case
                    }

                    int idToSearch = Integer.parseInt(idStr.trim());
                    Event eventToSearch = eventServices.getEvent(idToSearch);
                    if (eventToSearch != null) {
                        String eventDetails = String.format(
                                "Event ID: %d\nEvent Name: %s\nEvent Description: %s\nEvent Type: %s\nEvent Location: %s\nEvent Date: %s",
                                eventToSearch.getId(),
                                eventToSearch.getName(),
                                eventToSearch.getDescription(),
                                eventToSearch.getType(),
                                eventToSearch.getLocation(),
                                sdf.format(eventToSearch.getDate())
                        );
                        JOptionPane.showMessageDialog(null, eventDetails, "Event Details", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Event does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Returning to Admin Menu...", "Info", JOptionPane.INFORMATION_MESSAGE);
                    adminController();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (userChoice != 0);
    }

    public void participantManagementController() {
        int userChoice = -1;

        do {
            String userChoiceStr = JOptionPane.showInputDialog(
                    "1. Add Participant\n" +
                            "2. Remove a Participant\n" +
                            "3. Display all Participants\n" +
                            "0. Return to Main Menu\n\n" +
                            "Enter your choice:");

            // Handle null input (e.g., if the user cancels the input dialog)
            if (userChoiceStr == null) {
                return;  // Exit the method if the user cancels the input
            }

            try {
                userChoice = Integer.parseInt(userChoiceStr.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;  // Restart the loop if the input is invalid
            }

            UserServices userServices = new UserServices();

            switch (userChoice) {
                case 1:
                    String name = JOptionPane.showInputDialog("Enter Participant Username:").toLowerCase().trim();
                    if (name != null && !name.isEmpty()) {
                        userServices.signUp(name);
                        JOptionPane.showMessageDialog(null, "You have successfully added the participant.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid username.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 2:
                    String idStr = JOptionPane.showInputDialog("Enter Participant ID:");
                    if (idStr != null && !idStr.trim().isEmpty()) {
                        try {
                            int id = Integer.parseInt(idStr.trim());
                            User user = userServices.getUser(id);
                            if (user != null) {
                                userServices.deleteUser(id);
                                JOptionPane.showMessageDialog(null, "User deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Participant ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 3:
                    StringBuilder participantsList = new StringBuilder("Participant ID | Participant Name\n");
                    LinkedList<User> users = userServices.getUsers();
                    for (User u : users) {
                        participantsList.append(String.format("%-10d | %-20s\n", u.getId(), u.getUsername()));
                    }
                    JOptionPane.showMessageDialog(null, participantsList.toString(), "All Participants", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Returning to Main Menu...", "Info", JOptionPane.INFORMATION_MESSAGE);
                    adminController();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (userChoice != 0);
    }

    public void statsController(){
         JOptionPane.showInputDialog("Enter your stats: ");
    }

    public void participantController(User user) {
        int userChoice = 0;
        int userID = user.getId();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        do {
            String userChoiceStr = JOptionPane.showInputDialog(
                    "1. Display Events\n" +
                            "2. Reserve an Event\n" +
                            "3. Display Event Reservations\n" +
                            "0. Return to Main Menu\n\n" +
                            "Enter your choice:");

            // Handle null input (e.g., if the user cancels the input dialog)
            if (userChoiceStr == null) {
                return;  // Exit the method if the user cancels the input
            }

            try {
                userChoice = Integer.parseInt(userChoiceStr.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;  // Restart the loop if the input is invalid
            }

            EventServices eventServices = new EventServices();

            switch (userChoice) {
                case 1:
                    LinkedList<Event> events = eventServices.getEvents();

                    if (events == null) {
                        JOptionPane.showMessageDialog(null, "No events found. The event list is null.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else if (events.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No events found. The event list is empty.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        StringBuilder eventsList = new StringBuilder("Event ID | Event Name | Event Description | Event Type | Event Location | Event Date\n");
                        for (Event e : events) {
                            eventsList.append(String.format("%-10d | %-20s | %-30s | %-15s | %-20s | %-15s\n",
                                    e.getId(),
                                    e.getName(),
                                    e.getDescription(),
                                    e.getType(),
                                    e.getLocation(),
                                    sdf.format(e.getDate())));
                        }
                        JOptionPane.showMessageDialog(null, eventsList.toString(), "Available Events", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case 2:
                    String eventIDStr = JOptionPane.showInputDialog("Enter Event ID:");

                    if (eventIDStr != null && !eventIDStr.trim().isEmpty()) {
                        try {
                            int eventID = Integer.parseInt(eventIDStr.trim());
                            Event event = eventServices.getEvent(eventID);
                            if (event != null) {
                                ReservationServices reservationServices = new ReservationServices();
                                reservationServices.reserveEvent(userID, eventID);
                                JOptionPane.showMessageDialog(null, "The event was reserved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Event does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid Event ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Event ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 3:
                    LinkedList<Event> reservedEvents = getEvents(userID);

                    if (reservedEvents.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "You have no reserved events.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        StringBuilder reservedEventsList = new StringBuilder("Event ID | Event Name | Event Description | Event Type | Event Location | Event Date\n");
                        for (Event e : reservedEvents) {
                            reservedEventsList.append(String.format("%-10d | %-20s | %-30s | %-15s | %-20s | %-15s\n",
                                    e.getId(),
                                    e.getName(),
                                    e.getDescription(),
                                    e.getType(),
                                    e.getLocation(),
                                    sdf.format(e.getDate())));
                        }
                        JOptionPane.showMessageDialog(null, reservedEventsList.toString(), "Your Reserved Events", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Returning to Main Menu...", "Information", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } while (userChoice != 0);
    }

    private static LinkedList<Event> getEvents(int userID) {
        ReservationServices reservationServices = new ReservationServices();
        LinkedList<Reservation> reservations = reservationServices.getReservations();
        LinkedList<Event> reservedEvents = new LinkedList<>();

        EventServices eventServices2 = new EventServices();

        for (Reservation r : reservations) {
            if (r.getParticipantId() == userID) {
                Event event = eventServices2.getEvent(r.getEventId());
                if (event != null) {
                    reservedEvents.add(event);
                }
            }
        }
        return reservedEvents;
    }


}
