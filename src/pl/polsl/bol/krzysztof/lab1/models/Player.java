package pl.polsl.bol.krzysztof.lab1.models;

import pl.polsl.bol.krzysztof.lab1.exceptions.EmptyNameEnteredException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class representing a player.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class Player {

    /**
     * Property containing a string value with the name.
     */
    private final StringProperty name;

    /**
     * Property containing a integer value with the score.
     */
    private final IntegerProperty score;

    /**
     * Non-parameter constructor
     */
    public Player() {
        name = new SimpleStringProperty();
        score = new SimpleIntegerProperty();
        name.set("");
    }

    /**
     * Returns the wrapped value of the private property {@link #name}.
     *
     * @return string containing the value wrapped by the private property in
     * the {@link Player}.
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the wrapped value of the private property {@link #name}.
     *
     * @param name string with the player's name
     * @throws EmptyNameEnteredException when attempt to set empty name
     */
    public void setName(String name) throws EmptyNameEnteredException {
        if (name.trim().isEmpty()) {
            throw new EmptyNameEnteredException();
        }
        this.name.set(name);
    }

    /**
     * Returns the private property {@link #name}.
     *
     * @return StringProperty containing the player's name
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Returns the private property {@link #score}.
     *
     * @return IntegerProperty containing the player's score number
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Returns the wrapped value of the private property {@link #score}.
     *
     * @return Integer value of the player's score
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Sets the wrapped value of the private property {@link #score}.
     *
     * @param score integer value of the player's score
     */
    public void setScore(int score) {
        this.score.set(score);
    }
}
