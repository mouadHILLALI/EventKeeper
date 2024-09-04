package entity;

public class Reservation {
    private int participantId;
    private int eventId;
    public Reservation(int participantId, int eventId) {
        this.participantId = participantId;
        this.eventId = eventId;
    }
    public int getParticipantId() {
        return participantId;
    }
    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
