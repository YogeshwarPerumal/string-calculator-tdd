import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(stringCalculator.add("1,5"),6);
    }

    @Test
    public void testAdditionOfMultipleNumbers() {
        assertEquals(stringCalculator.add("1,9,10,12,14"), 46);
    }

    @Test
    public void testAdditionWithNewLineDelimiter() {
        assertEquals(stringCalculator.add("1\n,4,\n5"), 10);
    }

    @Test
    public void testDynamicDelimiter() {
        assertEquals(stringCalculator.add("//;\n1;2"),3);
        assertEquals(stringCalculator.add("//;\n1;2\n\n;3;4\n\n\n\n"), 10);
    }

    @Test
    public void testSpecialCharacterDelimiter() {
        assertEquals(stringCalculator.add("//$\n1$2\n\n$3$4\n\n\n\n"), 10);
    }

    @Test
    public void testNegativeNumbers() {
        Exception exception = assertThrows(Exception.class, () -> stringCalculator.add("//;\n1;2;\n-3"));
        assertEquals("Negative numbers are not allowed -3", exception.getMessage());
    }

}
