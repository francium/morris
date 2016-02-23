package se2aa4.morris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application class
 * Initializes javafx application
 * @author Varun Hooda 001412942
 * @author Aushim Lakhana 001201528
 * @author Matthew Shortt 001417616
 * @version 1.0
 */
public class App extends Application{

    /**
     * Start point of javafx application
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // load UI
        Parent root =  FXMLLoader.load(getClass().getResource("UI.fxml"));

        // create and show UI
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
