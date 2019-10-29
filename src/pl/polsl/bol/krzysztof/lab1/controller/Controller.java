package pl.polsl.bol.krzysztof.lab1.controller;

import pl.polsl.bol.krzysztof.lab1.exceptions.EmptyNameEnteredException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import pl.polsl.bol.krzysztof.lab1.models.*;

/**
 * FXML Controller class. Implements controller initialization interface.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class Controller implements Initializable {

    /**
     * Timer that is called every frame.
     */
    private AnimationTimer timer;

    /**
     * Object that controls game objects.
     */
    private Game game;

    /**
     * Value injected by FXML. An image that game is drawn on.
     */
    @FXML
    private Canvas gameCanvas;

    /**
     * Used for drawing on {@link #gameCanvas}.
     */
    private GraphicsContext gameGraphicsContext;

    /**
     * Value injected by FXML. Used for displaying player's score.
     */
    @FXML
    private Label playerScoreLabel;

    /**
     * Value injected by FXML. Used for displaying "game over" text on
     * {@link #gameCanvas}.
     */
    @FXML
    private Label gameOverLabel;

    /**
     * Value injected by FXML. Used for displaying player's name.
     */
    @FXML
    private TextField playerNameTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameGraphicsContext = gameCanvas.getGraphicsContext2D();
        game = new Game((int) gameCanvas.getHeight(), (int) gameCanvas.getWidth());
        gameOverLabel.setText("Game over");
        gameOverLabel.setVisible(false);
        timer = createAnimationTimer();

        //property binding
        playerScoreLabel.textProperty().bind(game.getPlayer().scoreProperty().asString());
        playerNameTextField.textProperty().bindBidirectional(game.getPlayer().nameProperty());
    }

    /**
     * Gets the name of a player from command line arguments.
     *
     * @param parameterList the command line arguments
     */
    public void getPlayerNameFromParameter(List<String> parameterList) {
        if (parameterList.size() > 0) {
            try {
                //first parameter should be name
                game.getPlayer().setName(parameterList.get(0));
            } catch (EmptyNameEnteredException e) {
                showEmptyNameAlert();
            }
        }
    }

    /**
     * Creates and shows an Alert that player entered an empty string.
     */
    private void showEmptyNameAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Please enter your name.");
        alert.showAndWait();
    }

    /**
     * Draws game objects (snake and food) on {@link #gameCanvas}.
     *
     * @param gc context used to draw on {@link #gameCanvas}
     */
    private void draw(GraphicsContext gc) {
        //clearing canvas
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        //drawing snake
        for (GameObject obj : game.getSnake().getSnakeBody()) {
            gc.setFill(obj.getColor());
            gc.fillRect(obj.getPosition().x, obj.getPosition().y, obj.getSize(), obj.getSize());
        }

        //drawing food
        gc.setFill(game.getFood().getColor());
        gc.fillRect(game.getFood().getPosition().x, game.getFood().getPosition().y,
                game.getFood().getSize(), game.getFood().getSize());
    }

    /**
     * Called when a key is pressed.
     *
     * @param e contains event's key code
     */
    @FXML
    private void keyHandler(KeyEvent e) {
        game.processDirectionChange(e.getCode());
    }

    /**
     * Starts {@link #timer} and disables {@link #playerNameTextField}. Called
     * by FXML when "start game" button is pressed.
     */
    @FXML
    private void startGame() {
        try {
            game.getPlayer().setName(playerNameTextField.getText());
            playerNameTextField.setDisable(true);
            gameOverLabel.setVisible(false);
            timer.start();
            game.startGame();
        } catch (EmptyNameEnteredException e) {
            showEmptyNameAlert();
        }
    }

    /**
     * Stops the timer.
     */
    private void stopGame() {
        timer.stop();
    }

    /**
     * Makes the {@link #gameOverLabel} text visible and enables
     * {@link #playerNameTextField}.
     */
    private void gameOver() {
        gameOverLabel.setVisible(true);
        playerNameTextField.setDisable(false);
    }

    /**
     * Creates a timer, that is called in each frame while it is active.
     *
     * @return new instance of {@link AnimationTimer}
     */
    private AnimationTimer createAnimationTimer() {
        return new AnimationTimer() {
            /**
             * Previous update time given in nanoseconds.
             */
            private long prevUpdateTime = 0;
            /**
             * One frame time given in nanoseconds.
             */
            private final long frameTime = 100_000_000; // = 0.1 s 

            /**
             * Method that is called every frame.
             *
             * @param now timestamp of the current frame given in nanoseconds
             */
            @Override
            public void handle(long now) {
                if (game.getIsGameOver()) //if player lost 
                {
                    stopGame();
                    gameOver();
                    return;
                }
                if (now - prevUpdateTime < frameTime) {
                    return;
                }
                game.update();
                draw(gameGraphicsContext);
                prevUpdateTime = now;
            }
        };
    }
}
