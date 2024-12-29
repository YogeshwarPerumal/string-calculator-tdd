import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String str) throws Exception {
        if (str.isEmpty()) {
            return 0;
        }

        if (str.length() == 1) {
            return Integer.parseInt(str);
        }

        String delimiter = ",";

        if (str.startsWith("//")) {
            String[] strs = str.split("\n",2);
            delimiter = strs[0].replace("//","");
            str = strs[1];
        }

        delimiter = switch (delimiter) {
            case "$", "+", ".", "^", "*", "?", "|", "(", ")", "[", "]", "{", "}", "\\" -> "\\" + delimiter;
            default -> delimiter;
        };

        String[] numbers = str.split(delimiter);
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
