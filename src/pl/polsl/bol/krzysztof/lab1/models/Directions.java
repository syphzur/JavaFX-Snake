package pl.polsl.bol.krzysztof.lab1.models;

/**
 * Directions that {@link Snake} head can be moved in: null {@link #NONE}, 
 * {@link #UP},
 * {@link #DOWN},
 * {@link #LEFT},
 * {@link #RIGHT}.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public enum Directions {
    /**
     * No direction (object isn't moving).
     */
    NONE,
    /**
     * Up direction.
     */
    UP,
    /**
     * Down direction.
     */
    DOWN,
    /**
     * Left direction.
     */
    LEFT,
    /**
     * Right direction.
     */
    RIGHT
}
