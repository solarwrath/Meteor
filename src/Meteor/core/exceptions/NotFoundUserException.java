package Meteor.core.exceptions;

public class NotFoundUserException extends Exception {
    public NotFoundUserException() {

    }

    public NotFoundUserException(String message) {
        super (message);
    }

    public NotFoundUserException(Throwable cause) {
        super (cause);
    }

    public NotFoundUserException(String message, Throwable cause) {
        super (message, cause);
    }
}
