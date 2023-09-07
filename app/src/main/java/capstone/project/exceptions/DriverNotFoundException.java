package capstone.project.exceptions;

public class DriverNotFoundException extends RuntimeException{
    //private static final long serialVersionUID =
    public DriverNotFoundException() {super();}
    public DriverNotFoundException(String message) {
        super(message);
    }
    public DriverNotFoundException(Throwable cause) {
        super(cause);
    }
    public DriverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
