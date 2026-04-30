package main.java.com.nestdev4.model.exceptions;

public class DealershipException extends RuntimeException {

    public DealershipException(String message) {
        super(message);
    }

    public DealershipException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealershipException(Throwable cause) {
        super(cause);
    }
}
