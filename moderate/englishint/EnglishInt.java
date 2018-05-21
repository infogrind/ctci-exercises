import java.util.ArrayList;
import java.util.Collections;

public class EnglishInt {

    public static String englishInt(int n) {

        if (n == 0)
            return "zero";

        String[] thousands = {"", " thousand", " million", " billion"};
        int tCounter = 0;
        final String prefix;
        if (n < 0) {
            prefix = "minus ";
            n = -n;
        }
        else prefix = "";

        ArrayList<String> terms = new ArrayList<String>();
        while (n != 0) {
            int hundreds = n % 1000;
            if (hundreds != 0)
                terms.add(hundredsInWords(hundreds) + thousands[tCounter]);
            tCounter++;
            n = n / 1000;
        }

        Collections.reverse(terms);
        return prefix + arrayListToString(terms);
    }

    private static String hundredsInWords(int n) {

        if (n < 0 || n > 999)
            throw new IllegalArgumentException("Number " + n + " out of bounds");

        ArrayList<String> terms = new ArrayList<String>();

        String[] onesWords = {"", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine"};
        String[] teensWords = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tensWords = {"twenty", "thirty", "fourty", "fifty", "sixty", "seventy",
            "eighty", "ninety"};

        int hundreds = n / 100;
        if (hundreds > 0)
            terms.add(onesWords[hundreds] + " hundred");
        int rest = n % 100;
        if (rest > 0) {
            if (rest < 10)
                terms.add(onesWords[rest]);
            else if (rest < 20)
                terms.add(teensWords[rest - 10]);
            else { // 20 <= rest <= 99
                int tens = rest / 10;
                int ones = rest % 10;
                terms.add(tensWords[tens - 2]);
                if (ones > 0)
                    terms.add(onesWords[ones]);
            }
        }

        return arrayListToString(terms);
    }

    private static String arrayListToString(ArrayList<String> list) {
        StringBuilder builder = new StringBuilder();
        if (list.isEmpty())
            return "";
        else {
            for (int i = 0; i < list.size() - 1; i++)
                builder.append(list.get(i) + " ");
            builder.append(list.get(list.size() - 1));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Syntax: java EnglishInt <number>");
            System.exit(1);
        }

        try {
            int number = Integer.parseInt(args[0]);
            System.out.println(englishInt(number));
        }
        catch (NumberFormatException e) {
            System.err.println("Could not parse number: " + args[0]);
        }
    }
}
