package mainpack.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SaveAndAddController {

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
    private Button save;
    @FXML
    private Button cancel;

    @FXML
    public void initialize() {

    }
}
