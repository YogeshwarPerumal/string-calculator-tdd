import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    private static StringCalculator stringCalculator;

    @BeforeAll
    static void initialize() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testEmptyString() {
        assertEquals(stringCalculator.add(""),0);
    }

    @Test
    public void testSingleNumber() {
        assertEquals(stringCalculator.add("4"),4);
    }

    @Test
    public void testTwoNumberAddition() {
        assertEquals(stringCalculator.add("1+5"),6);
    }
}
