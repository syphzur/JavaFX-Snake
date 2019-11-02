package pl.polsl.bol.krzysztof.lab2.models;

import java.awt.Point;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Class representing the game logic and controlling game objects.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class Game {

    /**
     * Height of the canvas that game is drawn on.
     */
    private final int GAME_CANVAS_HEIGHT;

    /**
     * Width of the canvas that game is drawn on.
     */
    private final int GAME_CANVAS_WIDTH;

    /**
     * Snake that is controlled by player.
     */
    private Snake snake;

    /**
     * Food that can be eaten by snake.
     */
    private GameObject food;

    /**
     * Player that is playing the game.
     */
    private Player player;

    /**
     * True when the game is over.
     */
    private boolean isGameOver;

    /**
     * Integer representing the size of one body segment.
     */
    private final int FOOD_SIZE = 20;

    /**
     * Double representing value that is added to the snake's speed when
     * collision with food occured.
     */
    private final double SPEED_STEP = 0.1;

    /**
     * Constructs {@link Game} object with canvas size specified by parameters.
     *
     * @param gameCanvasHeight canvas height
     * @param gameCanvasWidth canvas width
     */
    public Game(int gameCanvasHeight, int gameCanvasWidth) {
        this.GAME_CANVAS_HEIGHT = gameCanvasHeight;
        this.GAME_CANVAS_WIDTH = gameCanvasWidth;
        this.player = new Player();
        this.isGameOver = false;
    }

    /**
     * Updates {@link Game} object. Moves the snake and checks if any collision
     * occured. If collision with borders of the canvas occured, changes
     * {@link #isGameOver} value to true. If collision with food occured,
     * generates new food and increments {@link #player} score. If collision
     * with snake body occured, changes {@link #isGameOver} value to true.
     */
    public void update() {
        GameObject head = snake.getSnakeBody().get(0);
        //checks collision with borders
        if (collisionCheck(head)) {
            gameOver();
        }
        //checks collision with snake body
        for (int i = 1; i < snake.getSnakeBody().size(); i++) {
            if (collisionCheck(head, snake.getSnakeBody().get(i))) {
                gameOver();
            }
        }
        //checks collision with food
        if (collisionCheck(head, food)) {
            snake.addNewBodyPart();
            generateFood();
            player.setScore(player.getScore() + 1);
            snake.setSnakeSpeed(snake.getSnakeSpeed() + SPEED_STEP);
        }
        snake.move();
    }

    /**
     * Generates new {@link #food} at random position.
     */
    private void generateFood() {
        Random random = new Random();
        int randomPositionX = random.nextInt(GAME_CANVAS_WIDTH / 20) * 20;
        int randomPositionY = random.nextInt(GAME_CANVAS_HEIGHT / 20) * 20;
        this.food = new GameObject(new Point(randomPositionX, randomPositionY), Color.RED, FOOD_SIZE);
        for (GameObject obj : snake.getSnakeBody()) {
            if (collisionCheck(obj, this.food)) {
                generateFood();
            }
        }
    }

    /**
     * Processes {@link #snake} direction change.
     *
     * @param key KeyCode value of pressed key
     */
    public void processDirectionChange(KeyCode key) {
        if (key == KeyCode.UP && snake.getDirection() != Directions.DOWN) {
            snake.setDirection(Directions.UP);
        } else if (key == KeyCode.DOWN && snake.getDirection() != Directions.UP) {
            snake.setDirection(Directions.DOWN);
        } else if (key == KeyCode.LEFT && snake.getDirection() != Directions.RIGHT) {
            snake.setDirection(Directions.LEFT);
        } else if (key == KeyCode.RIGHT && snake.getDirection() != Directions.LEFT) {
            snake.setDirection(Directions.RIGHT);
        }
    }

    /**
     * Returns the private field {@link #snake}.
     *
     * @return {@link Snake}
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Returns the private field {@link #food}.
     *
     * @return {@link GameObject} object representing food
     */
    public GameObject getFood() {
        return food;
    }

    /**
     * Returns the private field {@link #food}.
     *
     * @return {@link Player} object representing a player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Starts (or restarts) the game. Sets {@link #isGameOver} value to false.
     * Creates new {@link #snake} and {@link #food}. Sets {@link #player} score
     * to 0.
     */
    public void startGame() {
        this.isGameOver = false;
        this.snake = new Snake((int) GAME_CANVAS_HEIGHT / 2, (int) GAME_CANVAS_WIDTH / 2);
        this.player.setScore(0);
        generateFood();
    }

    /**
     * Checks if a collision between two objects occured.
     *
     * @param obj1 first object
     * @param obj2 second object
     * @return true if collision occured
     */
    private boolean collisionCheck(GameObject obj1, GameObject obj2) {
        return obj1.getPosition().x == obj2.getPosition().x
                && obj1.getPosition().y == obj2.getPosition().y;
    }

    /**
     * Checks if a collision between an object and canvas borders occured.
     *
     * @param obj {@link #snake} body part
     * @return true if collision occured
     */
    private boolean collisionCheck(GameObject obj) {
        return obj.getPosition().x >= GAME_CANVAS_WIDTH
                || obj.getPosition().x < 0 || obj.getPosition().y < 0
                || obj.getPosition().y >= GAME_CANVAS_HEIGHT;
    }

    /**
     * Sets the {@link #isGameOver} value to true.
     */
    private void gameOver() {
        this.isGameOver = true;
    }

    /**
     * Returns the private field {@link #isGameOver}.
     *
     * @return boolean value containing information if the game is over
     */
    public boolean getIsGameOver() {
        return isGameOver;
    }
}
