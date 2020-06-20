package mainpack.views;

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
    private TextField hashtags;
    @FXML
    private Button edit;
    @FXML
    private Button done;
    @FXML
    private Button save;

    public InformationPaneController(UsersEvent event, int id) {
        this.event = event;
        this.id = id;
    }

    /*public void setEvent(UsersEvent event) {
        this.event = event;
    }*/

    /*public void setString(String string) {
        this.string = string;
    }*/

    @FXML
    public void initialize() {
        name.setText(event.getNameOfEvent());
        notes.setText(event.getNotesForEvent());
        priority.setPromptText(String.valueOf(event.getPriority()));

        Calendar calendar = event.getDateOfEvent();
        date.setValue(LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)));
        time.setText("sdfas");
        repeat.setPromptText("yes");
        place.setText(event.getPlaceOfEvent());
        reminder.setPromptText(event.getReminder());
        try {
            hashtags.setText(event.getHashtags().toString());
        } catch (NullPointerException e) {
            hashtags.setText("No hashtags");
        }

        edit.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickEdit());
        done.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickDone());
        save.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> clickSave());
    }

    private void clickSave() {
        event.setNameOfEvent(name.getText());
        event.setNotesForEvent(notes.getText());
        event.setPriority(Integer.parseInt(priority.getSelectionModel().getSelectedItem()));

        LocalDate localDate = date.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        event.setDateOfEvent(cal);

        String rep = repeat.getSelectionModel().getSelectedItem();
        event.setRepeat(rep.equals("yes"));

        event.setPlaceOfEvent(place.getText());
        event.setReminder(place.getText());
        event.setHashtags(hashtags.getText().split("\\s+"));

        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();

        RootLayoutController.list.set(id, event);
    }

    private void clickEdit() {
        name.setEditable(true);
        name.setDisable(false);
        notes.setEditable(true);
        notes.setDisable(false);
        priority.setDisable(false);
        date.setEditable(true);
        date.setDisable(false);
        time.setEditable(true);
        time.setDisable(false);
        repeat.setDisable(false);
        place.setEditable(true);
        place.setDisable(false);
        reminder.setDisable(false);
        hashtags.setEditable(true);
        hashtags.setDisable(false);

        save.setVisible(true);
        save.setDisable(false);
    }

    private void clickDone() {
        RootLayoutController.list.remove(event);
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }

}
