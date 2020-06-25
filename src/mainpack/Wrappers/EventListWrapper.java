package mainpack.Wrappers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainpack.model.UsersEvent;

import java.io.Serializable;
import java.util.ArrayList;

public class EventListWrapper implements Serializable {

    private ArrayList<UsersEvent> events;

    public EventListWrapper(ObservableList<UsersEvent> events) {
        this.events = new ArrayList<UsersEvent>(events);
    }

    public ObservableList<UsersEvent> getEvents() {
        return FXCollections.observableArrayList(events);
    }
}
