package exceptions;

public class MissingElementInJsonException extends Exception {
    String message;

    public MissingElementInJsonException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
