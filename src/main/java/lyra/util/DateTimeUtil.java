package lyra.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting dates for tasks in the Lyra application.
 */
public final class DateTimeUtil {
    public static final DateTimeFormatter STORAGE_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");

    private DateTimeUtil() {}

    public static LocalDate parseDate(String input) throws DateTimeParseException {
        return LocalDate.parse(input.trim(), STORAGE_DATE);
    }

    public static String formatForDisplay(LocalDate date) {
        return date.format(DISPLAY_DATE);
    }
}


