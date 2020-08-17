package telran.time.tests;
import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import telran.time.DateTimeOperations;

class DateTimeOperationsTest {

	@Test
	void nextFriday13() {
		LocalDate fr13=LocalDate.of(2020, 11, 13);
		assertEquals(fr13, DateTimeOperations.nextFriday13());
	}
	@Test
	void workingDays() {
		DayOfWeek[]allDaysOff = DayOfWeek.values();
		DayOfWeek[]noDaysOff= {};
		LocalDate from = LocalDate.now();
		LocalDate to = from.plusYears(1);
//		Period period = Period.between (from,to);
//		System.out.printf("%d %d %d", period.getYears(),period.getMonths(),period.getDays());
		assertEquals(0, DateTimeOperations.workingDays(from, to,
				allDaysOff));
		assertEquals(ChronoUnit.DAYS.between(from, to),
				DateTimeOperations.workingDays(from, to, noDaysOff));
	}
	@Test
	void zoneTime() {
		DateTimeFormatter dtf=DateTimeFormatter
				.ofPattern("YYYY-MM-dd HH:mm:ss");
		ZonedDateTime expected = 
				ZonedDateTime.now(ZoneId.of("America/Toronto"));
		assertEquals(expected.format(dtf),
				DateTimeOperations.getDateTimeZoneFromMilli
				(System.currentTimeMillis(),"America/Toronto").format(dtf));
	}
	@Test
	void workingDays2() {	
		LocalDate from = LocalDate.of(2020, 8, 14);
		LocalDate to   = LocalDate.of(2020, 10, 14);
		DayOfWeek[] euroDaysOff = {DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
		assertEquals(43, DateTimeOperations.workingDays(from, to, euroDaysOff));		
	}

}
