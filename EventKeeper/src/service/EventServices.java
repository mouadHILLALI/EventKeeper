package service;
import entity.Event;
import java.util.Random;
import java.util.Date;
import java.util.LinkedList;
public class EventServices {
    entity.Event event;
    Random rand = new Random();
    private static LinkedList<Event> events = new LinkedList<>();
    public void addEvent(String name , String description , String type , String location , Date date){
        int id = rand.nextInt(10000);
        event = new Event(id ,  name , description , type , date , location);
        events.add(event);
    }
    public void editEvent(int id, String name, String description, String type, String location, Date date) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setName(name);
                event.setDescription(description);
                event.setType(type);
                event.setLocation(location);
                event.setDate(date);
                break;
            }
        }
    }
    public void deleteEvent(int id) {
        events.removeIf(event -> event.getId() == id);
    }
    public LinkedList<Event> getEvents() {
        return events;
    }
    public Event getEvent(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
}
