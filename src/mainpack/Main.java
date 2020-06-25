package mainpack;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainpack.Wrappers.EventListWrapper;
import mainpack.model.UsersEvent;
import mainpack.views.RootLayoutController;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/RootLayout.fxml"));
        RootLayoutController controller = new RootLayoutController(openTasks());
        loader.setController(controller);
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setOnCloseRequest(windowEvent -> saveTasks(controller.getList()));
        primaryStage.setScene(scene);
        primaryStage.setTitle("ToDo List");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void saveTasks(ObservableList<UsersEvent> list) {
        if (list.size() > 0) {
            FileOutputStream fos;
            ObjectOutputStream outputStream = null;

            try {
                fos = new FileOutputStream("all_tasks.txt");
                outputStream = new ObjectOutputStream(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (outputStream != null) {
                    outputStream.writeObject(new EventListWrapper(list));
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ObservableList<UsersEvent> openTasks() {
        try {
            FileInputStream e = new FileInputStream("all_tasks.txt");
            ObjectInputStream inputSteam = new ObjectInputStream(e);

            EventListWrapper wrapper = (EventListWrapper) inputSteam.readObject();

            return wrapper.getEvents();
        } catch (IOException | ClassNotFoundException e) {
            return FXCollections.observableArrayList();
        }
    }
}
