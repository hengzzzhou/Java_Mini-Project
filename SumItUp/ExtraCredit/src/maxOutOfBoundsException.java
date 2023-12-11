/**
 * The maxOutOfBoundsException is a custom exception class that represents an exception
 * thrown when the maximum value is invalid.
 */
public class maxOutOfBoundsException extends RuntimeException {

    /**
     * Constructs a new maxOutOfBoundsException with no detail message.
     */
    public maxOutOfBoundsException() {
    }

    /**
     * Constructs a new maxOutOfBoundsException with the specified detail message.
     *
     * @param message the detail message
     */
    public maxOutOfBoundsException(String message) {
        super(message);
    }
}

