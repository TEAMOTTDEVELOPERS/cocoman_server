package orangetaxiteam.cocoman.domain.validation;

import java.util.Calendar;

public class ValueValidation {
    // Year validation, allowed in range from 1800 to (nowYear + 5), and only integer format
    public static boolean isYearInRange(String year) {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int intYear = Integer.parseInt(year);

        return intYear <= nowYear + 5 && intYear >= 1800;
    }

    // Date validation, allowed in range from 1800 to (nowYear + 5) for year, and 1 ~ 12 for month, 1 ~ 31 for date
    public static boolean isDateInRange(String date) {
        String[] dateData = date.split("\\.|-");

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int intYear = Integer.parseInt(dateData[0]);
        int intMonth = Integer.parseInt(dateData[1]);
        int intDate = Integer.parseInt(dateData[2]);

        if (intYear > (nowYear + 5) || intYear < 1800) return false;
        if (intMonth < 1 || intMonth > 12) return false;
        if (intDate < 1 || intDate > 31) return false;
        return true;

    }

    // Score validation, allowed in range from 0.5 to 5.0
    public static boolean isScoreInRange(Double score){
        return !(score < 0.5) && !(score > 5.0);
    }
}
