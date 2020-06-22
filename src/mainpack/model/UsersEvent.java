package mainpack.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


//For each user's event
public class UsersEvent {

    private String nameOfEvent;
    private String notesForEvent;
    private int priority;
    private Calendar dateOfEvent;
    private String repeat;
    private String placeOfEvent;
    private String reminder;
    private ArrayList<String> hashtags;
    private Instant start;
    private Instant end;

    public UsersEvent() {}

    public UsersEvent(String nameOfEvent, String notesForEvent, int priority, Calendar dateOfEvent, String repeat,
                      String placeOfEvent, String reminder, ArrayList<String> hashtags, Instant start, Instant end) {
        this.nameOfEvent = nameOfEvent;
        this.notesForEvent = notesForEvent;
        this.priority = priority;
        this.dateOfEvent = dateOfEvent;
        this.repeat = repeat;
        this.placeOfEvent = placeOfEvent;
        this.reminder = reminder;
        this.hashtags = hashtags;
        this.start = start;
        this.end = end;
    }

    public UsersEvent(String nameOfEvent, String notesForEvent, int priority, Calendar dateOfEvent,
                      String repeat, String placeOfEvent, String reminder, String[] hashtags) {
        this.nameOfEvent = nameOfEvent;
        this.notesForEvent = notesForEvent;
        this.priority = priority;
        this.dateOfEvent = dateOfEvent;
        this.repeat = repeat;
        this.placeOfEvent = placeOfEvent;
        this.reminder = reminder;
        this.hashtags = new ArrayList<>(Arrays.asList(hashtags));
    }

    public UsersEvent(String nameOfEvent, int priority, Calendar dateOfEvent, String repeat, String reminder) {
        this.nameOfEvent = nameOfEvent;
        this.priority = priority;
        this.dateOfEvent = dateOfEvent;
        this.repeat = repeat;
        this.reminder = reminder;
    }

    public UsersEvent(String nameOfEvent, String notesForEvent, int priority, Calendar dateOfEvent, String repeat, String reminder) {
        this.nameOfEvent = nameOfEvent;
        this.notesForEvent = notesForEvent;
        this.priority = priority;
        this.dateOfEvent = dateOfEvent;
        this.repeat = repeat;
        this.reminder = reminder;
    }

    public UsersEvent(String nameOfEvent, String notesForEvent, int priority, Calendar dateOfEvent, String reminder) {
        this.nameOfEvent = nameOfEvent;
        this.notesForEvent = notesForEvent;
        this.priority = priority;
        this.dateOfEvent = dateOfEvent;
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

    public String isRepeat() {
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

    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = new ArrayList<>(Arrays.asList(hashtags));
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return nameOfEvent;
    }
}
