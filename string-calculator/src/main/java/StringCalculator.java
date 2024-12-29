import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String input) throws Exception {
        if (input.isEmpty()) {
            return 0;
        }

        if (input.length() == 1) {
            return Integer.parseInt(input);
        }

        String processedString = processString(input);
        String[] numbers = processedString.split(",");

        return calculateExpression(numbers);
    }

    private String processString(String expression) {
        if (expression.startsWith("//")) {
            String[] tokenAndString = expression.split("\n",2);
            String token = tokenAndString[0].replace("//","");
            expression = tokenAndString[1];
            if (token.startsWith("[") && token.endsWith("]")) {
                for (String ele: extractDelimitersFromBrackets(token)) {
                    expression = processSpecialCharactersInDelimiter(ele, expression);
                }
            }
            else {
                expression = processSpecialCharactersInDelimiter(token, expression);
            }
        }

        return expression;
    }

    private String processSpecialCharactersInDelimiter(String delimiter, String expression) {
        switch (delimiter) {
            case "$", "+", ".", "^", "*", "?", "|", "(", ")", "[", "]", "{", "}", "\\": {
                String builder = "\\" +
                        delimiter;
                expression = expression.replaceAll(builder, ",");
                break;
            }
            default:
                expression = expression.replaceAll(delimiter, ",");
        }

        return expression;
    }

    private List<String> extractDelimitersFromBrackets(String delimiter) {
        List<String> values = new ArrayList<>();

        // Regular expression to find content inside square brackets
        String regex = "\\[([^\\]]*)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(delimiter);

        // Find all matches and add to the list
        while (matcher.find()) {
            values.add(matcher.group(1));
        }

        return values;
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
