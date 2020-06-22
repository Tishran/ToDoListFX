package mainpack.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import mainpack.model.UsersEvent;

import java.io.IOException;
import java.util.GregorianCalendar;

public class RootLayoutController {
    @FXML
    private ListView<String> modes;
    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane leftPane;
    public static ObservableList<UsersEvent> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException{
        for (int i = 0; i < 10; i++) {
            list.add(new UsersEvent("Task " + (i + 1),
                    "note " + (i + 1), 1, new GregorianCalendar(), "On time"));
        }

        setContent(FXMLLoader.load(getClass().getResource("InboxMain.fxml")));

        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll("Inbox", "Today", "This week", "This month", "This year");
        modes.setItems(options);

        modes.getSelectionModel().select(0);
        modes.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                switch (t1) {
                    case "Inbox":
                        setContent(FXMLLoader.load(getClass().getResource("InboxMain.fxml")));
                        break;
                    case "Today":
                        setContent(FXMLLoader.load(getClass().getResource("Today.fxml")));
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        leftPane.setFocusTraversable(false);
        content.setFocusTraversable(true);
    }

    private void setContent(Parent parent) {
        if (content.getChildren().isEmpty()) {
            content.getChildren().add(parent);
        } else {
            content.getChildren().remove(0);
            content.getChildren().add(0, parent);
        }
    }
}
