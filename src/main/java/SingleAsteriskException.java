public class SingleAsteriskException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Found single asterisk in field - Resource";
    }
}
