package Meteor.core.exceptions;

public class NotCompletedUser extends Exception {
    public NotCompletedUser() {

    }

    public NotCompletedUser(String message) {
        super (message);
    }

    public NotCompletedUser(Throwable cause) {
        super (cause);
    }

    public NotCompletedUser(String message, Throwable cause) {
        super (message, cause);
    }
}
