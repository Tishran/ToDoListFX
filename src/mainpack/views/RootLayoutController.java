package mainpack.views;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import mainpack.Main;
import mainpack.model.UsersEvent;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class RootLayoutController {
    @FXML
    private ListView<String> modes;
    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane leftPane;
    public static ObservableList<UsersEvent> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException {
        for (int i = 0; i < 10; i++) {
            list.add(new UsersEvent("Task " + (i + 1),
                    "note " + (i + 1), 1, Calendar.getInstance(), "On time"));
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        loader.setController(new MainController(list));
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

                Date start;
                Date end;
                switch (t1) {
                    case "Inbox":
                        otherContent.setController(new MainController(list));
                        break;
                    case "Today":
                        start = c.getTime();
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        end = c.getTime();

                        otherContent.setController(new MainController(createNewList(start, end)));
                        break;
                    case "This week":
                        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        start = c.getTime();
                        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        end = c.getTime();

                        otherContent.setController(new MainController(createNewList(start, end)));
                        break;
                    case "This month":
                        c.set(Calendar.DAY_OF_MONTH, 1);
                        start = c.getTime();
                        c.add(Calendar.MONTH, 1);
                        end = c.getTime();

                        otherContent.setController(new MainController(createNewList(start, end)));
                    case "This year":
                        c.set(Calendar.MONTH, Calendar.JANUARY);
                        c.set(Calendar.DAY_OF_MONTH, 1);
                        start = c.getTime();
                        c.add(Calendar.MONTH, 12);
                        end = c.getTime();

                        otherContent.setController(new MainController(createNewList(start, end)));
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
        if (content.getChildren().isEmpty()) {
            content.getChildren().add(parent);
        } else {
            content.getChildren().remove(0);
            content.getChildren().add(0, parent);
        }
    }

    private ObservableList<UsersEvent> createNewList(Date start, Date end) {
        ObservableList<UsersEvent> contentList = FXCollections.observableArrayList();

        for (UsersEvent i : RootLayoutController.list) {
            Date curDate = i.getDateOfEvent().getTime();

            if (!curDate.before(start) && !curDate.after(end)) {
                contentList.add(i);
            }
        }

        return contentList;
    }
}
