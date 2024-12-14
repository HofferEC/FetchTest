package hoffer.ec.fetch.controller;

import hoffer.ec.fetch.model.GetPointsResponse;
import hoffer.ec.fetch.model.ProcessResponse;
import hoffer.ec.fetch.model.Receipt;
import hoffer.ec.fetch.service.ReceiptService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptControllerTests {

    private static final String ID = UUID.randomUUID().toString();

    @Mock
    ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    @AfterEach
    public void close() {
        verifyNoMoreInteractions(receiptService);
    }

    @Test
    public void when_processReceipt_then_returnsId() {
        final Receipt receipt = Receipt.builder().build();
        final ProcessResponse expectedResponse = new ProcessResponse(ID);
        when(receiptService.process(receipt)).thenReturn(ID);

        ProcessResponse response = receiptController.process(receipt);

        assertEquals(expectedResponse, response);
        verify(receiptService, times(1)).process(receipt);
    }

    @Test
    public void when_getPointsForValidId_then_returnPoints() {
        final long expectedPoints = 1241;
        when(receiptService.getPoints(ID)).thenReturn(expectedPoints);

        GetPointsResponse response = receiptController.getPoints(ID);

        assertEquals(expectedPoints, response.getPoints());
        verify(receiptService, times(1)).getPoints(ID);
    }
}
