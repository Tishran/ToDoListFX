package mainpack.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainpack.model.UsersEvent;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class SaveAndAddController {

    @FXML
    private TextField name;
    @FXML
    private TextField notes;
    @FXML
    private ComboBox<Integer> priority;
    @FXML
    private DatePicker date;
    @FXML
    private TextField time;
    @FXML
    private ComboBox<String> repeat;
    @FXML
    private TextField place;
    @FXML
    private ComboBox<String> reminder;
    @FXML
    private TextField hashtags;
    @FXML
    private Button save;
    @FXML
    private Button cancel;

    @FXML
    public void initialize() {
        ObservableList<Integer> typesOfPriority = FXCollections.observableArrayList(1, 2, 3, 4);
        priority.setItems(typesOfPriority);

        repeat.setItems(FXCollections.observableArrayList("never", "daily", "every week", "every month", "every year", "custom"));
        reminder.setItems(FXCollections.observableArrayList("On time", "10 mins until", "None"));
    }

    @FXML
    public void saveClicked() {
        LocalDate localDate = date.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        UsersEvent event = new UsersEvent(name.getText(), notes.getText(), priority.getSelectionModel().getSelectedItem(),
                cal, repeat.getSelectionModel().getSelectedItem(), place.getText(), reminder.getSelectionModel().getSelectedItem(),
                hashtags.getText().split("\\s+"));

        RootLayoutController.list.add(event);

        cancelClicked();
    }

    @FXML
    public void cancelClicked() {
        Stage curStage = (Stage) cancel.getScene().getWindow();
        curStage.close();
    }
}
