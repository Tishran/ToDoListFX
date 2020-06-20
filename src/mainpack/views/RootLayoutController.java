package mainpack.views;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import mainpack.model.UsersEvent;

import java.io.IOException;
import java.util.GregorianCalendar;

public class RootLayoutController {
    @FXML
    private ListView<String> modes;
    @FXML
    private ListView<UsersEvent> tasks;
    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane leftPane;

    public static ObservableList<UsersEvent> list = FXCollections.observableArrayList();

    private Stage informationStage = new Stage();

    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll("Inbox", "Today", "This week", "This month", "This year");
        modes.setItems(options);

        modes.getSelectionModel().select(0);
        modes.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                switch (t1) {
                    case "Inbox":
                        setContent(FXMLLoader.load(getClass().getResource("Inbox.fxml")));
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

        for (int i = 0; i < 10; i++) {
            list.add(new UsersEvent("Task " + (i + 1), "note " + (i + 1), 1, new GregorianCalendar(), "On time"));
        }
        tasks.setItems(list);

        tasks.setCellFactory(CheckBoxListCell.forListView(event -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    list.remove(event);
                    System.out.println(event + " is done!");
                }
            });
            return observable;
        }));

        tasks.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            try {
                InformationPaneController controller = new InformationPaneController(t1, tasks.getSelectionModel().getSelectedIndex());

                FXMLLoader loader = new FXMLLoader(RootLayoutController.this.getClass().getResource("InformationPane.fxml"));
                loader.setController(controller);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                informationStage.setScene(scene);
                informationStage.setTitle("Information about event");
                informationStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> tasks.getSelectionModel().clearSelection());
        });
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
