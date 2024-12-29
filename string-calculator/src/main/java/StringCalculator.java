public class StringCalculator {

    public int add(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(str);
    }
}
