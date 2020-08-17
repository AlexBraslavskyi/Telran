package telran.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class DateTimeOperations {

	public static LocalDate nextFriday13() {
		LocalDate current = LocalDate.now().withDayOfMonth(13);
		while (true) {
			if (current.getDayOfWeek() == DayOfWeek.FRIDAY) {
				return current;
			}
			current = current.plusMonths(1);
		}
//	 LocalDate current = LocalDate.now();
//	while(true) {
//		if(current.getDayOfWeek() == DayOfWeek.FRIDAY && current.getDayOfMonth() == 13) {
//		return current;
//		}
//		current = current.plusDays(1);
//		}
	}

	public static int workingDays(LocalDate from, LocalDate to, DayOfWeek[] daysOff) {
		int count = 0;
		int period = (int) ChronoUnit.DAYS.between(from, to);
		LocalDate week = from.plusWeeks(1);
		while (from.isBefore(week)) {
			if (Arrays.asList(daysOff).contains(from.getDayOfWeek())) {
				count++;
			}
			from = from.plusDays(1);
		}
		return (int) (period - ((double) count * (double) period / 7));
	
//		while (from.isBefore(to)) {
//			if (Arrays.asList(daysOff).contains(from.getDayOfWeek())) {
//				count++;
//			}
//			from = from.plusDays(1);
//		}
//		return period - count;
	}

	static public ZonedDateTime getDateTimeZoneFromMilli(long timeInMilli, String zoneId) {
		ZonedDateTime dateFromMilli = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeInMilli), ZoneId.of(zoneId));
		return dateFromMilli;
	}
}
