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

public class InformationPaneController {

    private UsersEvent event;
    private int id = -1;
    private MainController mainController;

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
    private ComboBox<String> amOrPm;
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

    public InformationPaneController(UsersEvent event, int id, MainController mainController) {
        this.event = event;
        this.id = id;
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        ObservableList<Integer> typesOfPriority = FXCollections.observableArrayList(1, 2, 3, 4);
        priority.setItems(typesOfPriority);

        repeat.setItems(FXCollections.observableArrayList("never", "daily", "every week", "every month", "every year", "custom"));
        reminder.setItems(FXCollections.observableArrayList("On time", "10 mins until", "None"));
        amOrPm.setItems(FXCollections.observableArrayList("AM", "PM"));

        name.setText(event.getNameOfEvent());
        notes.setText(event.getNotesForEvent());
        priority.getSelectionModel().select(event.getPriority() - 1);

        Calendar calendar = event.getDateOfEvent();
        date.setValue(LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)));
        amOrPm.getSelectionModel().select(calendar.get(Calendar.AM_PM));
        time.setText(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));

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
        cal.set(Calendar.HOUR, Integer.parseInt(t[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(t[1]));
        cal.set(Calendar.AM_PM, amOrPm.getSelectionModel().getSelectedItem().equals("AM") ? Calendar.AM : Calendar.PM);
        event.setDateOfEvent(cal);

        event.setRepeat(repeat.getSelectionModel().getSelectedItem());

        event.setPlaceOfEvent(place.getText());
        event.setReminder(place.getText());

        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();

        RootLayoutController.list.set(id, event);
        RootLayoutController.sorting();
    }

    private void clickEdit() {
        name.setEditable(true);
        notes.setEditable(true);
        priority.setDisable(false);
        date.setDisable(false);
        time.setEditable(true);
        amOrPm.setDisable(false);
        repeat.setDisable(false);
        place.setEditable(true);
        reminder.setDisable(false);

        save.setVisible(true);
        save.setDisable(false);
    }

    private void clickDone() {
        RootLayoutController.list.remove(event);
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

}
