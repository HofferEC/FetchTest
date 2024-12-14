package hoffer.ec.fetch.model.exception;

/**
 * Thrown when a client tries to upload a receipt which fails to process when we're tallying up the points.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String s) {
        super(s);
    }
    public BadRequestException(String s, Exception e) {
        super(s, e);
    }
}
