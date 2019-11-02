package pl.polsl.bol.krzysztof.lab2.exceptions;

/**
 * Exception class for objects thrown when attempting to enter empty name.
 *
 * @author Krzysztof Ból
 * @version 1.0
 */
public class EmptyNameEnteredException extends Exception {

    /**
     * Non-parameter constructor.
     */
    public EmptyNameEnteredException() {
    }

    /**
     * Exception class constructor.
     *
     * @param message display message
     */
    public EmptyNameEnteredException(String message) {
        super(message);
    }
}
