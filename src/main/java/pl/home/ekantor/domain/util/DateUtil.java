package pl.home.ekantor.domain.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class DateUtil {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static LocalDate convert(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Date string cannot be null or blank");
		}

		try {
			return LocalDate.parse(value, FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format. Expected format is yyyy-MM-dd", e);
		}
	}
}
