package capstone.project.exceptions;

public class TripNotFoundException extends RuntimeException{
  //private static final long serialVersionUID =
    public TripNotFoundException() {super();}
    public TripNotFoundException(String message) {
        super(message);
    }
    public TripNotFoundException(Throwable cause) {
        super(cause);
    }
    public TripNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
