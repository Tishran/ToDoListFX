package mainpack.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import mainpack.model.UsersEvent;

import java.io.IOException;
import java.util.Calendar;

public class RootLayoutController {
    @FXML
    private ListView<String> modes;
    @FXML
    private BorderPane content;
    @FXML
    private AnchorPane leftPane;
    public static ObservableList<UsersEvent> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException {
        FXCollections.sort(list, UsersEvent::compareTo);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        loader.setController(new MainController(list, -1));
        setContent(loader.load());

        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll("Inbox", "Today", "This week", "This month", "This year");
        modes.setItems(options);

        modes.getSelectionModel().select(0);
        modes.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                FXMLLoader otherContent = new FXMLLoader(getClass().getResource("MainView.fxml"));

                Calendar c = Calendar.getInstance();

                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                switch (t1) {
                    case "Inbox":
                        otherContent.setController(new MainController(list, -1));
                        break;
                    case "Today":
                        otherContent.setController(new MainController(createNewList(Calendar.DAY_OF_MONTH, c), Calendar.DAY_OF_MONTH));
                        break;
                    case "This week":
                        otherContent.setController(new MainController(createNewList(Calendar.WEEK_OF_YEAR, c), Calendar.WEEK_OF_YEAR));
                        break;
                    case "This month":
                        otherContent.setController(new MainController(createNewList(Calendar.MONTH, c), Calendar.MONTH));
                        break;
                    case "This year":
                        otherContent.setController(new MainController(createNewList(Calendar.YEAR, c), Calendar.YEAR));
                        break;
                }

                setContent(otherContent.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        leftPane.setFocusTraversable(false);
        content.setFocusTraversable(true);
    }

    private void setContent(Parent parent) {
        if (!content.getChildren().isEmpty()) {
            content.getChildren().remove(0);
        }
        content.setCenter(parent);
    }

    private ObservableList<UsersEvent> createNewList(int j, Calendar calendar) {
        ObservableList<UsersEvent> contentList = FXCollections.observableArrayList();

        for (UsersEvent i : RootLayoutController.list) {
            Calendar curDate = i.getDateOfEvent();

            if (curDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                if (curDate.get(j) == calendar.get(j)) {
                    contentList.add(i);
                }
            }
        }

        return contentList;
    }

    public static void sorting() {
        FXCollections.sort(list, UsersEvent::compareTo);
    }
}
