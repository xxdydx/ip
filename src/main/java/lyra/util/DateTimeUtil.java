package lyra.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utilities for parsing and formatting dates for tasks.
 */
public final class DateTimeUtil {
	public static final DateTimeFormatter STORAGE_DATE = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd
	public static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");

	private DateTimeUtil() {}

	/**
 	 * Parses a date string using common accepted formats.
 	 * Currently supports only ISO yyyy-MM-dd as the minimal requirement.
 	 */
	public static LocalDate parseDate(String input) throws DateTimeParseException {
		String trimmed = input.trim();
		// Minimal requirement: accept yyyy-MM-dd
		return LocalDate.parse(trimmed, STORAGE_DATE);
	}

	/** Formats the given date into a human friendly string, e.g., Oct 15 2019. */
	public static String formatForDisplay(LocalDate date) {
		return date.format(DISPLAY_DATE);
	}
}


