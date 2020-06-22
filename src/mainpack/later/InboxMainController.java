package mainpack.later;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import mainpack.model.UsersEvent;
import mainpack.views.GeneralController;
import mainpack.views.InformationPaneController;
import mainpack.views.RootLayoutController;

import java.io.IOException;

public class InboxMainController extends GeneralController {

    @FXML
    private ListView<UsersEvent> tasks;

    @FXML
    public void initialize() {
        initListView();
    }

    @Override
    protected void initListView() {
        tasks.setItems(RootLayoutController.list);

        tasks.setCellFactory(CheckBoxListCell.forListView(event -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    RootLayoutController.list.remove(event);
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
    @Override
    protected void addBtnClicked() throws IOException {
        super.addBtnClicked();
    }
}
