package hoffer.ec.fetch.service.helper;

import hoffer.ec.fetch.model.Item;
import hoffer.ec.fetch.model.Receipt;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class PointCalculator {

    /**
     * Applies the various point-counting criteria to tally up the total points for a given receipt.
     *
     * @param receipt | the client's receipt
     * @return the total points the receipt is worth
     */
    public long tallyPoints(
            @NonNull final Receipt receipt
    ) {
        long points = 0;

        points += countAlphanumericsInRetailer(receipt);
        points += checkRoundDollarAmount(receipt);
        points += checkMultipleOfQuarter(receipt);
        points += checkPairsOfItems(receipt);
        points += checkItemDescMultipleOfThree(receipt);
        points += checkDayOddness(receipt);
        points += checkTimeOfPurchase(receipt);

        return points;
    }

    // One point for every alphanumeric character in the retailer name.
    public long countAlphanumericsInRetailer(
            @NonNull final Receipt receipt
    ) {
        long points = 0;
        for (final Character c : receipt.getRetailer().toCharArray()) {
            if (Character.isLetterOrDigit(c)) points++;
        }
        return points;
    }

    // 50 points if the total is a round dollar amount with no cents.
    public long checkRoundDollarAmount(
            @NonNull final Receipt receipt
    ) {
        final BigDecimal totalAsBigDecimal = new BigDecimal(receipt.getTotal());
        if (totalAsBigDecimal.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
            return 50;
        }
        return 0;
    }

    // 25 points if the total is a multiple of 0.25.
    public long checkMultipleOfQuarter(
            @NonNull final Receipt receipt
    ) {
        final BigDecimal totalAsBigDecimal = new BigDecimal(receipt.getTotal());
        if (totalAsBigDecimal.remainder(new BigDecimal(".25")).compareTo(BigDecimal.ZERO) == 0) {
            return 25;
        }
        return 0;
    }

    // 5 points for every two items on the receipt.
    public long checkPairsOfItems(
            @NonNull final Receipt receipt
    ) {
        final int itemCount = receipt.getItems().size();
        return 5 * (itemCount / 2);
    }

    // If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the
    // nearest integer. The result is the number of points earned.
    public long checkItemDescMultipleOfThree(
            @NonNull final Receipt receipt
    ) {
        long points = 0;
        for (final Item item : receipt.getItems()) {
            final String trimmedDesc = item.getShortDescription().trim();
            if (trimmedDesc.length() % 3 == 0) {
                double itemPrice = Double.parseDouble(item.getPrice());
                points += Math.ceil(itemPrice * .2);
            }
        }
        return points;
    }

    // 6 points if the day in the purchase date is odd.
    public long checkDayOddness(
            @NonNull final Receipt receipt
    ) {
        final String[] dateBits = receipt.getPurchaseDate().split("-");
        int day = Integer.parseInt(dateBits[2]);
        return day % 2 == 1 ? 6 : 0;
    }

    // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
    // Note: I'm assuming 2:00PM is inclusive and 4:00PM is exclusive
    public long checkTimeOfPurchase(
            @NonNull final Receipt receipt
    ) {
        final String[] dateBits = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(dateBits[0]);
        return hour >= 14 && hour < 16 ? 10 : 0;
    }
}
