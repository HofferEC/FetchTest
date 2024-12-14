package hoffer.ec.fetch.service;

import hoffer.ec.fetch.model.exception.BadRequestException;
import hoffer.ec.fetch.persistence.IReceiptDAO;
import hoffer.ec.fetch.model.Receipt;
import hoffer.ec.fetch.model.exception.ReceiptNotFoundException;
import hoffer.ec.fetch.service.helper.IdGenerator;
import hoffer.ec.fetch.service.helper.PointCalculator;
import hoffer.ec.fetch.service.helper.ReceiptValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Holds the business logic for receipt processing.
 */
@Service
@RequiredArgsConstructor
public class ReceiptService {

    @Autowired
    final private IReceiptDAO receiptDAO;

    @Autowired
    final private IdGenerator idGenerator;

    @Autowired
    final private PointCalculator pointCalculator;

    /**
     * Given an ID from a previous receipt upload, queries our db for the points value and returns it.
     *
     * @param id | the id of the receipt
     * @return the point value of that receipt
     */
    public long getPoints(
            @NonNull final String id
    ) {
        if (!ReceiptValidator.isValidId(id)) {
            throw new ReceiptNotFoundException("ID failed validation.");
        }
        long points;
        try {
            points = receiptDAO.get(id);
        } catch (NullPointerException npe) {
            throw new ReceiptNotFoundException("No receipt found for that ID.");
        }
        return points;
    }

    /**
     * Given a receipt upload from the client, tallies up the point value, assigns it a unique ID, and stores it in
     * the db.
     *
     * @param receipt | the receipt uploaded by the client
     * @return the id which the receipt's point value is stored under
     */
    public String process(
            final Receipt receipt
    ) {
        if (!ReceiptValidator.isValidReceipt(receipt)) {
            throw new BadRequestException("Receipt failed validation.");
        }

        final String id = idGenerator.generateId();
        final long points = pointCalculator.tallyPoints(receipt);
        receiptDAO.put(id, points);
        return id;
    }
}
