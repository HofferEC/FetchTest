package hoffer.ec.fetch.service.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import hoffer.ec.fetch.model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointCalculatorTests {
    private PointCalculator pointCalculator;
    private ObjectMapper objectMapper;

    @BeforeEach
    private void setup() {
        this.pointCalculator = new PointCalculator();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void when_exampleOneProcessed_then_returnsCorrectPoints() throws IOException {
        File f = new File("src/test/testdata/ReceiptExample1.json");
        final Receipt receipt = objectMapper.readValue(f, Receipt.class);

        final double expectedPoints = 28;

        final double points = pointCalculator.tallyPoints(receipt);

        assertEquals(expectedPoints, points);
    }

    @Test
    public void when_exampleTwoProcessed_then_returnsCorrectPoints() throws IOException {
        File f = new File("src/test/testdata/ReceiptExample2.json");
        final Receipt receipt = objectMapper.readValue(f, Receipt.class);

        final double expectedPoints = 109;

        final double points = pointCalculator.tallyPoints(receipt);

        assertEquals(expectedPoints, points);
    }
}
