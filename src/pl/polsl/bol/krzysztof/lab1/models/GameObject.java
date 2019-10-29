package pl.polsl.bol.krzysztof.lab1.models;

import java.awt.Point;
import javafx.scene.paint.Color;

/**
 * Class representing a single game object.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class GameObject {

    /**
     * Point representing the object's position.
     */
    private Point position;

    /**
     * Color of the object.
     */
    private final Color color;

    /**
     * Height and width of the object.
     */
    private final int size;

    /**
     * Constructs a game object.
     *
     * @param position Point representing the object's position
     * @param color color of the object
     * @param size size of the object
     */
    public GameObject(Point position, Color color, int size) {
        this.position = new Point(position);
        this.color = color;
        this.size = size;
    }

    /**
     * Returns the private field {@link #color}.
     *
     * @return Color object representing color of the game object
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the private field {@link #size}.
     *
     * @return Integer value representing size of the game object
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the private field {@link #position}.
     *
     * @return Point object representing the game object's position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets the value of private field {@link #position}.
     *
     * @param position new game object's position
     */
    public void setPosition(Point position) {
        this.position = position;
    }
}
