package mainpack.views;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import mainpack.model.UsersEvent;

import java.io.IOException;

public class MainController {

    private ObservableList<UsersEvent> events;

    public MainController(ObservableList<UsersEvent> events) {
        this.events = events;
    }

    @FXML
    private ListView<UsersEvent> tasks;

    @FXML
    public void initialize() {
        initListView();
    }

    protected void initListView() {
        tasks.setItems(events);
        tasks.setCellFactory(CheckBoxListCell.forListView(event -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    events.remove(event);
                    System.out.println(event + " is done!");
                }
            });
            return observable;
        }));

        tasks.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            try {
                InformationPaneController controller = new InformationPaneController(t1, tasks.getSelectionModel().getSelectedIndex());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/InformationPane.fxml"));
                loader.setController(controller);
                Parent root = loader.load();

                Stage informationStage = new Stage();
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

    @FXML
    protected void addBtnClicked() throws IOException {
        Stage addStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/SaveAndAdd.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        addStage.setScene(scene);
        addStage.setTitle("Add new event");
        addStage.show();
    }
}
