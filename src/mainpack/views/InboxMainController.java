package mainpack.views;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import mainpack.model.UsersEvent;

import java.io.IOException;
import java.util.GregorianCalendar;

public class InboxMainController {

    @FXML
    public void initialize() {
    }

    public Parent init() throws IOException {
        return FXMLLoader.load(getClass().getResource("InboxMain.fxml"));
    }
}
