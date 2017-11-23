package by.tr.web.exception.dao;

public class MovieDAOException extends Exception {
    public MovieDAOException() {
        super();
    }

    public MovieDAOException(String message) {
        super(message);
    }

    public MovieDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieDAOException(Throwable cause) {
        super(cause);
    }

    protected MovieDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
