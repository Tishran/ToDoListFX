package mainpack.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainpack.model.UsersEvent;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static mainpack.views.MainController.*;

public class InformationPaneController {

    private UsersEvent event;
    private int id = -1;
    private MainController mainController;
    private RootLayoutController root;

    @FXML
    private TextField name;
    @FXML
    private TextField notes;
    @FXML
    private ComboBox<String> priority;
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
    private Button edit;
    @FXML
    private Button done;
    @FXML
    private Button save;

    public InformationPaneController(UsersEvent event, int id, MainController mainController, RootLayoutController root) {
        this.event = event;
        this.id = id;
        this.mainController = mainController;
        this.root = root;
    }

    @FXML
    public void initialize() {
        ObservableList<String> typesOfPriority = FXCollections.observableArrayList("None", "Low", "Medium", "High");
        priority.setItems(typesOfPriority);

        repeat.setItems(FXCollections.observableArrayList(NEVER, DAILY, EVERY_WEEK, EVERY_MONTH,
                EVERY_YEAR, CUSTOM));
        reminder.setItems(FXCollections.observableArrayList("None", "On time", "10 mins until", "20 mins until",
                "1 hour until", "1 day until(9:00)", "2 days until(9:00)"));

        name.setText(event.getNameOfEvent());
        notes.setText(event.getNotesForEvent());
        priority.getSelectionModel().select(event.getPriority());

        Calendar calendar = event.getDateOfEvent();
        date.setValue(LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)));
        time.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

        repeat.getSelectionModel().select((event.getRepeat()));
        place.setText(event.getPlaceOfEvent());
        reminder.getSelectionModel().select(event.getReminder());

        edit.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickEdit());
        done.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickDone());
        save.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickSave());
    }

    private void clickSave() {
        event.setNameOfEvent(name.getText());
        event.setNotesForEvent(notes.getText());
        event.setPriority(priority.getSelectionModel().getSelectedItem());

        LocalDate localDate = date.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String[] t = time.getText().split(":");
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(t[1]));
        event.setDateOfEvent(cal);

        event.setRepeat(repeat.getSelectionModel().getSelectedItem());

        event.setPlaceOfEvent(place.getText());
        event.setReminder(place.getText());

        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();

        root.setList(id, event);
        mainController.setAndReturnList(id, event);
    }

    private void clickEdit() {
        name.setEditable(true);
        notes.setEditable(true);
        priority.setDisable(false);
        date.setDisable(false);
        time.setEditable(true);
        repeat.setDisable(false);
        place.setEditable(true);
        reminder.setDisable(false);

        save.setVisible(true);
        save.setDisable(false);
    }

    private void clickDone() {
        root.removeItem(event);
        mainController.removeItem(event);
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

}
