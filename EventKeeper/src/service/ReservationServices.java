package service;
import entity.Reservation;
import java.util.LinkedList;
public class ReservationServices {
    static LinkedList<Reservation> reservations = new LinkedList<>();
     public void reserveEvent(int participantID , int eventID){
         reservations.add(new Reservation(participantID, eventID));
     }
     public LinkedList<Reservation> getReservations(){
         return reservations;
     }

}
