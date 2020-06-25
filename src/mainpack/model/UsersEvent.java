package mainpack.model;

import java.io.Serializable;
import java.util.Calendar;


//For each user's event
public class UsersEvent implements Comparable<UsersEvent>, Serializable {

    private String nameOfEvent;
    private String notesForEvent;
    private int priority;
    private Calendar dateOfEvent;
    private String repeat;
    private String placeOfEvent;
    private String reminder;

    public UsersEvent(String nameOfEvent, String notesForEvent, int priority,
                      Calendar dateOfEvent, String repeat, String placeOfEvent,
                      String reminder) {
        this.nameOfEvent = nameOfEvent;
        this.notesForEvent = notesForEvent;
        this.priority = priority;
        this.dateOfEvent = dateOfEvent;
        this.repeat = repeat;
        this.placeOfEvent = placeOfEvent;
        this.reminder = reminder;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }

    public String getNotesForEvent() {
        return notesForEvent;
    }

    public void setNotesForEvent(String notesForEvent) {
        this.notesForEvent = notesForEvent;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Calendar getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(Calendar dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getPlaceOfEvent() {
        return placeOfEvent;
    }

    public void setPlaceOfEvent(String placeOfEvent) {
        this.placeOfEvent = placeOfEvent;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    @Override
    public String toString() {
        return nameOfEvent;
    }

    @Override
    public int compareTo(UsersEvent event) {
        return this.getDateOfEvent().after(event.getDateOfEvent()) ? 1 : 0;
    }
}
