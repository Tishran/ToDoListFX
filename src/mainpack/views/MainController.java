package mainpack.views;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import mainpack.model.UsersEvent;

import java.io.IOException;
import java.util.Calendar;

public class MainController {

    private ObservableList<UsersEvent> events;
    private int mode;
    private RootLayoutController root;

    @FXML
    private Label label;
    @FXML
    private ListView<UsersEvent> tasks;

    public MainController(ObservableList<UsersEvent> events, int mode, RootLayoutController root) {
        this.events = events;
        this.mode = mode;
        this.root = root;
    }

    public void removeItem(UsersEvent event) {
        events.remove(event);
    }

    @FXML
    public void initialize() {
        behavior();
        sorting();

        events.addListener((ListChangeListener<UsersEvent>) change -> {
            behavior();
            Platform.runLater(() -> tasks.getSelectionModel().clearSelection());
        });

        initListView();
    }

    protected void initListView() {
        tasks.setItems(events);
        tasks.setCellFactory(CheckBoxListCell.forListView(event -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    events.remove(event);
                    root.removeItem(event);
                }
            });
            return observable;
        }));

        tasks.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            try {
                InformationPaneController controller = new InformationPaneController(t1,
                        tasks.getSelectionModel().getSelectedIndex(), this, root);

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
        loader.setController(new SaveAndAddController(this, root));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        addStage.setScene(scene);
        addStage.setTitle("Add new event");
        addStage.show();
    }

    private void behavior() {
        if (!events.isEmpty()) {
            label.setVisible(false);
            label.setDisable(true);

            tasks.setDisable(false);
            tasks.setVisible(true);
        } else {
            label.setVisible(true);
            label.setDisable(false);

            tasks.setDisable(true);
            tasks.setVisible(false);
        }
    }

    public void setAndReturnList(int i, UsersEvent e) {
        if (mode != -1) {
            if (e.getDateOfEvent().get(mode) == Calendar.getInstance().get(mode)) {
                events.set(i, e);
            }
        }

        sorting();
    }

    public void setAndReturnList(UsersEvent e) {
        if (mode != -1) {
            if (e.getDateOfEvent().get(mode) == Calendar.getInstance().get(mode)) {
                events.add(e);
            }
        }

        sorting();
    }

    public void sorting() {
        if (events.size() > 1) {
            FXCollections.sort(events, UsersEvent::compareTo);
        }
    }
}
