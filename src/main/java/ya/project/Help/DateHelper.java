package ya.project.Help;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Helper class for handling a most common subset of ISO 8601 strings
 * (in the following format: "2008-03-01T13:00:00+01:00"). It supports
 * parsing the "Z" timezone, but many other less-used features are
 * missing.
 */
public final class DateHelper {
    /** Transform Date to ISO 8601 string. */
    public static String fromDate(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
    }

    public static String fromAnotherDate(final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").format(date);
    }

    /** Get current date and time formatted as ISO 8601 string. */
    public static String now() {
        return new Date().toString();
    }

    /** Transform ISO 8601 string to Date. */
    public static Date toDate(final String iso8601string) throws ParseException {
        String s = iso8601string;
        try {
            s = s.substring(0, 23) + "000";
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Invalid length", 0);
        }
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").parse(s);
    }

}
