package by.tr.web.exception.service;

public class MovieServiceException extends Exception {
    public MovieServiceException() {
        super();
    }

    public MovieServiceException(String message) {
        super(message);
    }

    public MovieServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieServiceException(Throwable cause) {
        super(cause);
    }
}
