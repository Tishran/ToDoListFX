package mainpack.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class TodayController {

    @FXML
    public void initialize() {

    }

    public Parent init() throws IOException {
        return FXMLLoader.load(getClass().getResource("Today.fxml"));
    }
}
