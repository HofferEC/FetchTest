package hoffer.ec.fetch.service.helper;

import hoffer.ec.fetch.model.Item;
import hoffer.ec.fetch.model.Receipt;
import java.util.regex.Pattern;

public class ReceiptValidator {

    private static final Pattern AMOUNT_REGEX = Pattern.compile("^\\d+\\.\\d{2}$");
    private static final Pattern DESCRIPTION_REGEX = Pattern.compile("^[\\w\\s\\-]+$");
    private static final Pattern RETAILER_REGEX = Pattern.compile("^[\\w\\s\\-&]+$");
    private static final Pattern ID_REGEX = Pattern.compile("^\\S+$");

    /**
     * Checks Receipt fields for completeness & against patterns defined in the api spec.
     *
     * @param receipt | the receipt to validate
     * @return true, if and only if the Receipt is valid
     */
    public static boolean isValidReceipt(final Receipt receipt) {
        if (null == receipt) return false;

        if (null == receipt.getRetailer()) return false;
        if (!RETAILER_REGEX.matcher(receipt.getRetailer()).matches()) return false;

        if (null == receipt.getTotal()) return false;
        if (!AMOUNT_REGEX.matcher(receipt.getTotal()).matches()) return false;

        if (null == receipt.getPurchaseDate()) return false;
        if (null == receipt.getPurchaseTime()) return false;

        if (null == receipt.getItems()) return false;
        if (receipt.getItems().isEmpty()) return false;
        for (final Item item : receipt.getItems()) {
            if (null == item) return false;

            if (null == item.getShortDescription()) return false;
            if (!DESCRIPTION_REGEX.matcher(item.getShortDescription()).matches()) return false;

            if (null == item.getPrice()) return false;
            if (!AMOUNT_REGEX.matcher(item.getPrice()).matches()) return false;
        }

        return true;
    }

    /**
     * Validates an ID string against the openapi spec.
     *
     * @param id | the id to validate
     * @return true, if and only if the ID is valid
     */
    public static boolean isValidId(final String id) {
        if (null == id) return false;
        if (!ID_REGEX.matcher(id).matches()) return false;

        return true;
    }
}
