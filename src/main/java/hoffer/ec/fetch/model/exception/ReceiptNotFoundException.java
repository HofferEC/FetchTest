package hoffer.ec.fetch.model.exception;

/**
 * Thrown when a client tries to get the point value for a receiptId which does not yet exist.
 */
public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException(String s) {
        super(s);
    }
}
