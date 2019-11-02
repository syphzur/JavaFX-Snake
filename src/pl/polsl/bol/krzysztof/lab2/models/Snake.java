package pl.polsl.bol.krzysztof.lab2.models;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Class representing a snake.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class Snake {

    /**
     * Integer representing the size of one body segment.
     */
    private final int BODY_SEGMENT_SIZE = 20;
    
    /**
     * Double representing the speed of snake movement.
     */
    private double snakeSpeed;

    /**
     * ArrayList containing snake's body segments.
     */
    private ArrayList<GameObject> snakeBody;

    /**
     * An {@link Directions} value representing the direction of {@link Snake}
     * head.
     */
    private Directions direction;

    /**
     * Constructs a snake with one body segment (head).
     *
     * @param startingPositionX x-position of snake's head
     * @param startingPositionY y-position of snake's head
     */
    public Snake(int startingPositionX, int startingPositionY) {
        this.direction = Directions.NONE;
        this.snakeBody = new ArrayList<GameObject>();
        Point startingPosition = new Point(startingPositionX, startingPositionY);
        this.snakeBody.add(new GameObject(startingPosition, Color.LAWNGREEN, BODY_SEGMENT_SIZE));
        this.snakeSpeed = 1;
    }

    /**
     * Returns the private field {@link #snakeBody}.
     *
     * @return ArrayList containing snake's body segments.
     */
    public ArrayList<GameObject> getSnakeBody() {
        return snakeBody;
    }

    /**
     * Sets the value of private field {@link #direction}.
     *
     * @param direction {@link Directions} value representing new direction of
     * snake's body
     */
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    /**
     * Returns the private field {@link #direction}.
     *
     * @return {@link Directions} value representing direction that snake's body
     * is moving in
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     * Adds new segment to snake's body.
     */
    public void addNewBodyPart() {
        this.snakeBody.add(new GameObject(snakeBody.get(snakeBody.size() - 1).getPosition(),
                Color.AQUAMARINE, BODY_SEGMENT_SIZE));
    }

    /**
     * Moves all segments of the snake's body in direction defined by private
     * field {@link #direction}.
     */
    public void move() {
        //moving snake body without head
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            snakeBody.get(i).setPosition(snakeBody.get(i - 1).getPosition());
        }

        //moving the head
        GameObject head = snakeBody.get(0);
        Point newPosition = new Point();
        switch (this.direction) {
            case NONE:
                newPosition = head.getPosition();
                break;
            case UP:
                newPosition.x = head.getPosition().x;
                newPosition.y = head.getPosition().y - BODY_SEGMENT_SIZE;
                break;
            case DOWN:
                newPosition.x = head.getPosition().x;
                newPosition.y = head.getPosition().y + BODY_SEGMENT_SIZE;
                break;
            case LEFT:
                newPosition.x = head.getPosition().x - BODY_SEGMENT_SIZE;
                newPosition.y = head.getPosition().y;
                break;
            case RIGHT:
                newPosition.x = head.getPosition().x + BODY_SEGMENT_SIZE;
                newPosition.y = head.getPosition().y;
                break;
        }
        head.setPosition(newPosition);
    }

    /**
     * Returns the private field {@link #snakeSpeed}.
     *
     * @return double representing the speed of snake
     */
    public double getSnakeSpeed() {
        return snakeSpeed;
    }
    
    /**
     * Sets the value of private field {@link #snakeSpeed}.
     *
     * @param snakeSpeed double representing the speed of snake
     * 
     */
    public void setSnakeSpeed(double snakeSpeed) {
        this.snakeSpeed = snakeSpeed;
    }
}
