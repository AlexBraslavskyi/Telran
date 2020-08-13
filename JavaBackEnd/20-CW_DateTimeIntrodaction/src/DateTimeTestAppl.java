import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateTimeTestAppl {

	
	public static void main(String[] args) {

		LocalDate current = LocalDate.now();
		LocalDate past = LocalDate.of(1989, Month.APRIL, 6);
		LocalDate future = LocalDate.parse("2025-01-01");
		System.out.printf("Current date: %s; future date: %s; past date: %s\n", current, future, past);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.MMMM.y eeee", Locale.forLanguageTag("en"));
		System.out.printf("Current date: %s; future date: %s; past date: %s\n", current.format(dtf), future.format(dtf), 
				past.format(dtf));
		ChronoUnit cu = ChronoUnit.DAYS;
		System.out.printf("between past %s and future %s there are %d %s \n", past, future, cu.between(past, future), cu);
		future = current.plusDays(1000);
		System.out.printf("Current date: %s; future date: %s; past date: %s\n", current, future, past);
		Period period = Period.between (current,future);
		System.out.printf("between future %s and currend %s there are %d yers %d month %d days\n", future, current, 
				period.getYears(),period.getMonths(), period.getDays());
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime futureDateTime = LocalDateTime.of(2030, Month.APRIL, 10, 5, 20);
		dtf = DateTimeFormatter.ofPattern("d/M/y h:mm a");
		cu = ChronoUnit.NANOS;
		System.out.printf("between current %s and future %s there are %d %s \n", 
				currentDateTime.format(dtf), futureDateTime.format(dtf),
				cu.between(currentDateTime, futureDateTime), cu);
		String zona = "Canada/Atlantic";
		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(zona));
		System.out.printf("zone id %s current date time %s\n", zona, zdt.format(dtf));
		System.out.println("all available zone:");
		ZoneId.getAvailableZoneIds().forEach(System.out::println);
	}
}
