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
    public void testEmptyString() throws Exception {
        assertEquals(stringCalculator.add(""),0);
    }

    @Test
    public void testSingleNumber() throws Exception {
        assertEquals(stringCalculator.add("4"),4);
    }

    @Test
    public void testTwoNumberAddition() throws Exception {
        assertEquals(stringCalculator.add("1,5"),6);
    }

    @Test
    public void testAdditionOfMultipleNumbers() throws Exception {
        assertEquals(stringCalculator.add("1,9,10,12,14"), 46);
    }

    @Test
    public void testAdditionWithNewLineDelimiter() throws Exception {
        assertEquals(stringCalculator.add("1\n,4,\n5"), 10);
    }

    @Test
    public void testDynamicDelimiter() throws Exception {
        assertEquals(stringCalculator.add("//;\n1;2"),3);
        assertEquals(stringCalculator.add("//;\n1;2\n\n;3;4\n\n\n\n"), 10);
    }

    @Test
    public void testSpecialCharacterDelimiter() throws Exception {
        assertEquals(stringCalculator.add("//$\n1$2\n\n$3$4\n\n\n\n"), 10);
    }

    @Test
    public void testNegativeNumbers() {
        Exception exception = assertThrows(Exception.class, () -> stringCalculator.add("//;\n1;2;\n-3"));
        assertEquals("Negative numbers are not allowed -3", exception.getMessage());
    }

    @Test
    public void testMultipleNegatives() {
        Exception exception = assertThrows(Exception.class, () -> stringCalculator.add("//;\n1;2;\n-3;-4;-5"));
        assertEquals("Negative numbers are not allowed -3, -4, -5", exception.getMessage());
    }

    @Test
    public void testIgnoreLargeNumbers() throws Exception {
        assertEquals(stringCalculator.add("//.\n1.1002.4.5.\n\n1594"), 10);
    }

}
