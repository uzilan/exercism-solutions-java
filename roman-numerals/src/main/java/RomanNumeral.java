import java.util.List;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

public class RomanNumeral {

    private final String romanNumber;

    private static List<Integer> sortedNumbers;

    public RomanNumeral(int number) {
        romanNumber = romanify(number);
    }

    public String getRomanNumeral() {
        return romanNumber;
    }

    private String romanify(int number) {
        if (number > 1000) {
            return "M" + romanify(number - 1000);
        }
        if (number > 100) {
            return toRoman(number / 100, "C", "D", "M") + romanify(number % 100);
        }
        if (number > 10) {
            return toRoman(number / 10, "X", "L", "C") + romanify(number % 10);
        }
        if (number >= 1) {
            return toRoman(number, "I", "V", "X");
        }
        return "";
    }

    private String toRoman(int number, String one, String five, String ten) {
        switch (number) {
            case 10:
                return ten;
            case 9:
                return one + ten;
            case 8:
            case 7:
            case 6:
                return five + repeat(number - 5, one);
            case 5:
                return five;
            case 4:
                return one + five;
            case 3:
            case 2:
            case 1:
                return repeat(number, one);
        }
        return "";
    }

    private String repeat(int number, String string) {
        return join("", nCopies(number, string));
    }
}
