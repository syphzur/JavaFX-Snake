package pl.polsl.bol.krzysztof.lab2.mainapp;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.polsl.bol.krzysztof.lab2.controller.Controller;

/**
 * Main class of the project. Extends from JavaFX Application class.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class MainApp extends Application {

    /**
     * Entry point of application. Loads view from fxml file. Passes the command
     * line arguments to the controller.
     *
     * @param primaryStage primary stage for this application, onto which scene
     * can be set
     * @throws IOException when failed to load file.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //loading fxml view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/polsl/bol/krzysztof/lab2/view/View.fxml"));
        Parent root = loader.load();

        //passing command line arguments
        Controller controller = loader.getController();
        controller.getPlayerNameFromParameter(getParameters().getRaw());

        //creating and setting a new scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("SnakeGame");
        primaryStage.show();
    }

    /**
     * Main method. Launches a standalone application. First argument contains
     * the name of a player .
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
