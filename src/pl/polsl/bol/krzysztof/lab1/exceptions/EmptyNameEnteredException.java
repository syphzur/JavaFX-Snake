package pl.polsl.bol.krzysztof.lab1.exceptions;

/**
 * Exception class for objects thrown when attempting to enter empty
 * {@link Player} name.
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
    public EmptyNameEnteredException(String msessage) {
        super(msessage);
    }
}
