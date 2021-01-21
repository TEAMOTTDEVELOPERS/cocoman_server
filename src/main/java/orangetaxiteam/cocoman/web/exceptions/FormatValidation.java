package orangetaxiteam.cocoman.web.exceptions;

import java.util.regex.Pattern;

public class FormatValidation {
    // Number check
    public static boolean isNumeric(String str) {
        return Pattern.matches("^[0-9]*$", str);
    }

    // Date dot format check (YYYY.MM.DD)
    public static boolean isDateDot(String str) {
        return Pattern.matches("^([0-9]{4})\\.([0-9]{1,2})\\.([0-9]{1,2})$", str);
    }

    // Date hyphen format check (YYYY-MM-DD)
    public static boolean isDateHyphen(String str) {
        return Pattern.matches("^([0-9]{4})-([0-9]{1,2})-([0-9]{1,2})$", str);
    }
}