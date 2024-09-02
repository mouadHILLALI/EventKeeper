package entity;

import java.util.Date;

public class Event {
    private int id;
    private String name;
    private String description;
    private String type;
    private Date date;
    private String location;

    public Event(int id, String name, String description, String type, Date date, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.date = date;
        this.location = location;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    public Date getDate() {
        return date;
    }
    public String getLocation() {
        return location;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
