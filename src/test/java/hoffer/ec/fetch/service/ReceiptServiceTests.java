package hoffer.ec.fetch.service;

import hoffer.ec.fetch.model.Item;
import hoffer.ec.fetch.model.exception.BadRequestException;
import hoffer.ec.fetch.persistence.IReceiptDAO;
import hoffer.ec.fetch.model.Receipt;
import hoffer.ec.fetch.model.exception.ReceiptNotFoundException;
import hoffer.ec.fetch.service.helper.IdGenerator;
import hoffer.ec.fetch.service.helper.PointCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTests {

    private static final String BAD_ID = " ";
    private static final String VALID_ID = UUID.randomUUID().toString();
    private static final Receipt BAD_RECEIPT = Receipt.builder().build();
    private static final Receipt VALID_RECEIPT = Receipt.builder()
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

    @Mock
    private IReceiptDAO receiptDAO;

    @Mock
    private IdGenerator idGenerator;

    @Mock
    private PointCalculator pointCalculator;

    @InjectMocks
    private ReceiptService receiptService;

    @AfterEach
    public void close() {
        verifyNoMoreInteractions(idGenerator, receiptDAO, pointCalculator);
    }

    // PROCESS RECEIPT TESTS
    @Test
    public void when_processReceipt_then_returnsId() {
        final long expectedPoints = 1998;
        when(idGenerator.generateId()).thenReturn(VALID_ID);
        when(pointCalculator.tallyPoints(VALID_RECEIPT)).thenReturn(expectedPoints);

        final String id = receiptService.process(VALID_RECEIPT);

        assertEquals(id, VALID_ID);

        verify(idGenerator, times(1)).generateId();
        verify(pointCalculator, times(1)).tallyPoints(VALID_RECEIPT);
        verify(receiptDAO, times(1)).put(VALID_ID, expectedPoints);
    }

    @Test
    public void when_processReceiptFailsValidation_then_wrapWithProcessingException() {
        assertThrows(BadRequestException.class, () -> receiptService.process(BAD_RECEIPT));
    }

    // GET POINTS TESTS
    @Test
    public void when_getPointsHasGoodID_then_returnsPointValue() {
        final long expectedPoints = 128;
        when(receiptDAO.get(VALID_ID)).thenReturn(expectedPoints);

        final double points = receiptService.getPoints(VALID_ID);

        assertEquals(expectedPoints, points);
        verify(receiptDAO, times(1)).get(VALID_ID);
    }

    @Test
    public void when_getPointsDaoCallThrowsException_then_wrapWithIDException() {
        when(receiptDAO.get(VALID_ID)).thenThrow(new NullPointerException("Oops"));

        assertThrows(ReceiptNotFoundException.class, () -> receiptService.getPoints(VALID_ID));

        verify(receiptDAO, times(1)).get(VALID_ID);
    }

    @Test
    public void when_getPointsWithBadId_then_throwNotFoundException() {
        assertThrows(ReceiptNotFoundException.class, () -> receiptService.getPoints(BAD_ID));
    }
}
