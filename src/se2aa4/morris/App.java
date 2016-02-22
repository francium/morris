package se2aa4.morris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    /**
     * Start point of javafx application
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root =  FXMLLoader.load(getClass().getResource("UI.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Fail-safe for environments without javafx support
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
