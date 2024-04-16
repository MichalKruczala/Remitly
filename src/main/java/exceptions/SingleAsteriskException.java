package exceptions;

public class SingleAsteriskException extends Exception{
    @Override
    public String getMessage() {
        return "Found single asterisk in field - Resource";
    }
}
