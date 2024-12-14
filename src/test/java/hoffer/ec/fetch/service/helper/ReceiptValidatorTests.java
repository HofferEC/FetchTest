package hoffer.ec.fetch.service.helper;

import hoffer.ec.fetch.model.Item;
import hoffer.ec.fetch.model.Receipt;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReceiptValidatorTests {

    // ID validation
    @Test
    public void when_validateGoodID_then_returnsTrue() {
        assertTrue(ReceiptValidator.isValidId(UUID.randomUUID().toString()));
    }

    @Test
    public void when_validateNullId_then_returnsFalse() {
        assertFalse(ReceiptValidator.isValidId(null));
    }

    @Test
    public void when_validateBadId_then_returnsFalse() {
        assertFalse(ReceiptValidator.isValidId(""));
    }

    // Receipt validation
    @Test
    public void when_validateGoodReceipt_then_returnsTrue() {
        assertTrue(ReceiptValidator.isValidReceipt(getValidReceipt()));
    }

    @Test
    public void when_validateNullReceipt_then_returnsFalse() {
        assertFalse(ReceiptValidator.isValidReceipt(null));
    }

    @Test
    public void when_validateReceiptWithNullRetailer_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setRetailer(null);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithBadRetailer_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setRetailer("");
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithNullTotal_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setTotal(null);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithEmptyTotal_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setTotal("");
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithNullPurchaseDate_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setPurchaseDate(null);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithNullPurchaseTime_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setPurchaseTime(null);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithEmptyItems_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setItems(Collections.emptyList());
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithANullItem_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        List<Item> mutableItemList = new ArrayList<Item>(receipt.getItems());
        mutableItemList.add(null);
        receipt.setItems(mutableItemList);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithANullDescriptionItem_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.getItems().get(0).setShortDescription(null);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithAnEmptyDescriptionItem_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.getItems().get(0).setShortDescription("");
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithANullAmountItem_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.getItems().get(0).setPrice(null);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithAnEmptyAmountItem_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.getItems().get(0).setPrice("");
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    @Test
    public void when_validateReceiptWithNullItems_then_returnsFalse() {
        final Receipt receipt = getValidReceipt();
        receipt.setItems(null);
        assertFalse(ReceiptValidator.isValidReceipt(receipt));
    }

    private Receipt getValidReceipt() {
        return Receipt.builder()
                .retailer("Target")
                .purchaseDate("2022-01-01")
                .purchaseTime("13:01")
                .items(List.of(
                                Item.builder()
                                        .shortDescription("Mountain Dew 12PK")
                                        .price("6.49")
                                        .build(),
                                Item.builder()
                                        .shortDescription("Emils Cheese Pizza")
                                        .price("12.25")
                                        .build(),
                                Item.builder()
                                        .shortDescription("Knorr Creamy Chicken")
                                        .price("1.26")
                                        .build(),
                                Item.builder()
                                        .shortDescription("Doritos Nacho Cheese")
                                        .price("3.35")
                                        .build(),
                                Item.builder()
                                        .shortDescription("   Klarbrunn 12-PK 12 FL OZ  ")
                                        .price("12.00")
                                        .build()
                        )
                )
                .total("35.35")
                .build();
    }
}
