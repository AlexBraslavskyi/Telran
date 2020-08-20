import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintCalendareAppl {

	private static Locale locale = Locale.US;
	private static int columnWidth = 4;;

	public static void main(String[] args) {

int monthYear[];
try {
	monthYear = getMonthYear(args);
} catch (Exception e) {
System.out.println(e.getMessage());
return;
}
printCalendar(monthYear[0],monthYear[1]);

	}

	private static void printCalendar(int month, int year) {
	
		printTitle(month, year);
		printWeekDays();
		printDays(month,year);
	}

	private static void printDays(int month, int year) {
	int firstColumn = getFirstColumn(month, year);
		printDatesFromFirstColumn(firstColumn, month, year);
		
	}

	private static void printDatesFromFirstColumn(int firstColumn, int month, int year) {
	printOffSet(firstColumn);
	int lastDayOfMonth = getLastDayOfMonth(month, year); 
	for(int day = 1; day <= lastDayOfMonth; day++) {
		System.out.printf("%" + columnWidth + "d", day);
		firstColumn++;
		if(firstColumn == 8) {
			firstColumn = 1;
			System.out.println();
		}
	}
	}

	private static int getLastDayOfMonth(int month, int year) {
YearMonth yearMonth = YearMonth.of(year, month);
		return yearMonth.lengthOfMonth();
	}

	private static void printOffSet(int firstColumn) {
		int limit = (firstColumn - 1) * columnWidth ;
//		for (int i = 0; i < limit; i++) {
//			System.out.print(" ");
//		}
		
		System.out.print(" ".repeat(limit));
	}

	private static int getFirstColumn(int month, int year) {
		LocalDate firstDate = LocalDate.of(year, month, 1);
		
		return firstDate.getDayOfWeek().getValue();
	}

	private static void printWeekDays() {
		System.out.printf("%" + columnWidth/2 + "s", " ");
		for(DayOfWeek weekDay: DayOfWeek.values()) {
			System.out.printf("%s ", weekDay.getDisplayName(TextStyle.SHORT, locale));
		}
		System.out.println();
	}

	private static void printTitle(int month, int year) {
	Month monthName = Month.of(month);
	System.out.printf("\t%s, %d\n", monthName.getDisplayName(TextStyle.FULL, locale),year);
		
	}

	private static int[] getMonthYear(String[] args) throws Exception {
	
		
		return args.length == 0 ? currentMonthYear() : getMonthYearBArgs(args);
	}

	private static int[] getMonthYearBArgs(String[] args) throws Exception {
	int month;
	try {
		month = Integer.parseInt(args[0]);
		if(month < 1 || month > 12) {
			throw new Exception(String.format("you have entered %d but month value shuuld be in range 1 - 12", month));
		}
	} catch (NumberFormatException e) {
	throw new Exception(String.format("you have entered %s but month value shuuld be number ", args[0]));
	}
		int year;
		try {
			year = args.length > 1 ?Integer.parseInt(args[1]) : LocalDate.now().getYear();
			if(year < 0) {
				throw new Exception("year should be only positive value");
			}
		} catch (NumberFormatException e) {
			throw new Exception(String.format("you have entered %s but year value shuuld be a positive number ", args[1]));
			
		}
		return new int[] {month, year};
	}

	private static int[] currentMonthYear() {
	LocalDate current = LocalDate.now();
		return new int[] {current.getMonthValue(),current.getYear()};
	}

}
