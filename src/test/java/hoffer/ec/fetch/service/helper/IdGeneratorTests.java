package hoffer.ec.fetch.service.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IdGeneratorTests {

    private IdGenerator idGenerator;

    @BeforeEach
    public void setup() {
        this.idGenerator = new IdGenerator();
    }

    @Test
    public void when_generateMultipleTimes_then_returnsUniqueIds() {
        final String id_1 = idGenerator.generateId();
        final String id_2 = idGenerator.generateId();

        assertNotEquals(id_1, id_2);
    }
}
