package mainpack.later;

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
import javafx.stage.Stage;
import mainpack.model.UsersEvent;
import mainpack.views.GeneralController;
import mainpack.views.InformationPaneController;
import mainpack.views.RootLayoutController;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class TodayController extends GeneralController {

    @FXML
    private ListView<UsersEvent> todayTasks;

    @FXML
    public void initialize() {
        initListView();
    }

    @Override
    protected void initListView() {
        ObservableList<UsersEvent> todayEvents = FXCollections.observableArrayList();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date start = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date end = c.getTime();

        for (UsersEvent i : RootLayoutController.list) {
            Date curDate = i.getDateOfEvent().getTime();

            if (!curDate.before(start) && !curDate.after(end)) {
                todayEvents.add(i);
            }
        }

        todayTasks.setItems(todayEvents);
        todayTasks.setCellFactory(CheckBoxListCell.forListView(event -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    todayEvents.remove(event);
                    System.out.println(event + " is done!");
                }
            });
            return observable;
        }));

        todayTasks.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            try {
                InformationPaneController controller = new InformationPaneController(t1, todayTasks.getSelectionModel().getSelectedIndex());

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

            Platform.runLater(() -> todayTasks.getSelectionModel().clearSelection());
        });
    }

    @Override
    @FXML
    protected void addBtnClicked() throws IOException {
        super.addBtnClicked();
    }
}
