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
    private Button save;
    @FXML
    private Button cancel;

    private MainController mainController;
    private RootLayoutController root;

    public SaveAndAddController(MainController mainController, RootLayoutController root) {
        this.mainController = mainController;
        this.root = root;
    }

    @FXML
    public void initialize() {
        ObservableList<Integer> typesOfPriority = FXCollections.observableArrayList(1, 2, 3, 4);
        priority.setItems(typesOfPriority);

        repeat.setItems(FXCollections.observableArrayList("never", "daily", "every week", "every month", "every year", "custom"));
        repeat.getSelectionModel().select(0);
        reminder.setItems(FXCollections.observableArrayList("On time", "10 mins until", "None"));
        reminder.getSelectionModel().select(2);
    }

    @FXML
    public void saveClicked() {
        LocalDate localDate = date.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String[] t = time.getText().split(":");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(t[1]));

        UsersEvent event = new UsersEvent(name.getText(), notes.getText(), priority.getSelectionModel().getSelectedItem(),
                cal, repeat.getSelectionModel().getSelectedItem(), place.getText(), reminder.getSelectionModel().getSelectedItem());

        mainController.setAndReturnList(event);
        root.setList(event);

        cancelClicked();
    }

    @FXML
    public void cancelClicked() {
        Stage curStage = (Stage) cancel.getScene().getWindow();
        curStage.close();
    }
}
