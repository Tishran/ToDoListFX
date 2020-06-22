package mainpack.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class GeneralController {

    protected abstract void initListView();

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
