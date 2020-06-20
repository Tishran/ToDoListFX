package mainpack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/RootLayout.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("ToDo List");
        //primaryStage.setHeight(HEIGHT);
        //primaryStage.setWidth(WIDTH);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
