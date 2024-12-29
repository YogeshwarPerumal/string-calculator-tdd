public class StringCalculator {

    public int add(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        String[] numbers = str.split(",");
        int sum = 0;

        try {
            for (String number: numbers) {
                sum += Integer.parseInt(number);
            }
            return sum;
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
            return 0;
        }
    }
}
