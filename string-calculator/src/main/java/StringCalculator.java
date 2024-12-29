public class StringCalculator {

    public int add(String str) {
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

        try {
            for (String number: numbers) {
                sum += Integer.parseInt(number.replaceAll("\n", ""));
            }
            return sum;
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
            return 0;
        }
    }
}
