import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String input) throws Exception {
        if (input.isEmpty()) {
            return 0;
        }

        if (input.length() == 1) {
            return Integer.parseInt(input);
        }

        String[] delimiterAndString = processString(input);
        String processedString = delimiterAndString[1];
        String[] numbers = processedString.split(delimiterAndString[0]);

        return calculateExpression(numbers);
    }

    private String[] processString(String str) {
        String delimiter = ",";

        if (str.startsWith("//")) {
            String[] tokenAndString = str.split("\n",2);
            String token = tokenAndString[0].replace("//","");
            if (token.startsWith("[") && token.endsWith("]")) {
                delimiter = token.substring(1,token.length()-1);
            }
            else {
                delimiter = token;
            }
            str = tokenAndString[1];
        }

        delimiter = switch (delimiter) {
            case "$", "+", ".", "^", "*", "?", "|", "(", ")", "[", "]", "{", "}", "\\" -> "\\" + delimiter;
            default -> delimiter;
        };

        return new String[]{delimiter,str};
    }

    private int calculateExpression(String[] numbers) throws Exception {
        int sum = 0;
        boolean isNegative = false;
        List<String> negativeNumbers = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Negative numbers are not allowed ");

        for (String number: numbers) {
            number = number.replaceAll("\n", "");
            int val = Integer.parseInt(number);
            if (val < 0) {
                isNegative = true;
                negativeNumbers.add(number);
            }

            if (val <= 1000) {
                sum += Integer.parseInt(number.replaceAll("\n", ""));
            }
        }

        if (isNegative) {
            builder.append(String.join(", ", negativeNumbers));
            throw new Exception(builder.toString());
        }
        return sum;
    }
}
