package exceptions;

public class WrongValueJsonException extends Exception{
   public String message;
   public WrongValueJsonException(String message){
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
