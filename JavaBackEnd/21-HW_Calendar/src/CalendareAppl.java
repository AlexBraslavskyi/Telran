import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendareAppl {

	private static Locale locale = Locale.US;
	private static int columnWidth = 4;

	public static void main(String[] args) {

		int monthYear[];
		try {
			monthYear = getMonthYear(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		printCalendar(monthYear[0], monthYear[1], monthYear[2]);
	}

	private static void printCalendar(int month, int year, int weekDay) {

		printTitle(month, year);
		printWeekDays(weekDay);
		printDays(month, year, weekDay);
	}

	private static void printDays(int month, int year, int weekDay) {
		int firstColumn = getFirstColumn(month, year, weekDay);
		printDatesFromFirstColumn(firstColumn, month, year);

	}

	private static void printDatesFromFirstColumn(int firstColumn, int month, int year) {
		printOffSet(firstColumn);
		int lastDayOfMonth = getLastDayOfMonth(month, year);
		for (int day = 1; day <= lastDayOfMonth; day++) {
			System.out.printf("%" + columnWidth + "d", day);
			firstColumn++;
			if (firstColumn == 8) {
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
		int limit = (firstColumn - 1) * columnWidth;
//		for (int i = 0; i < limit; i++) {
//			System.out.print(" ");
//		}

		System.out.print(" ".repeat(limit));
	}

	private static int getFirstColumn(int month, int year, int firstDayOfWeek) {
		LocalDate firstDate = LocalDate.of(year, month, 1);
		int count = 1;
		for (int i = firstDayOfWeek; i <= 7; i++) {
			if (i == firstDate.getDayOfWeek().getValue()) {
				return count;
			}
			count++;
		}
		for (int i = 1; i < firstDayOfWeek; i++) {
			if (i == firstDate.getDayOfWeek().getValue()) {
				return count;
			}
			count++;
		}
		return count;
	}

	private static void printWeekDays(int firstWeekDay) {
		System.out.printf("%" + columnWidth / 2 + "s", " ");

		for (int i = firstWeekDay; i <= 7; i++) {
			DayOfWeek day = DayOfWeek.of(i);
			System.out.printf("%s ", day.getDisplayName(TextStyle.SHORT, locale));
		}
		for (int i = 1; i < firstWeekDay; i++) {
			DayOfWeek day = DayOfWeek.of(i);
			System.out.printf("%s ", day.getDisplayName(TextStyle.SHORT, locale));
		}
		System.out.println();
	}

	private static void printTitle(int month, int year) {
		Month monthName = Month.of(month);
		System.out.printf("\t%s, %d\n", monthName.getDisplayName(TextStyle.FULL, locale), year);

	}

	private static int[] getMonthYear(String[] args) throws Exception {

		return args.length == 0 ? currentMonthYear() : getMonthYearArgs(args);
	}

	private static int[] getMonthYearArgs(String[] args) throws Exception {
		int month;
		try {
			month = Integer.parseInt(args[0]);
			if (month < 1 || month > 12) {
				throw new Exception(
						String.format("you have entered %d but month value shuuld be in range 1 - 12", month));
			}
		} catch (NumberFormatException e) {
			throw new Exception(String.format("you have entered %s but month value shuuld be number ", args[0]));
		}
		int year;
		try {
			year = args.length > 1 ? Integer.parseInt(args[1]) : LocalDate.now().getYear();
			if (year < 0) {
				throw new Exception("year should be only positive value");
			}
		} catch (NumberFormatException e) {
			throw new Exception(
					String.format("you have entered %s but year value shuuld be a positive number ", args[1]));

		}
		String day;

		try {
			day = args.length > 2 ? args[2].toUpperCase() : "SUNDAY";
			DayOfWeek.valueOf(day);
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("%s is wrong weekday name", args[2]));
		}
		return new int[] { month, year, DayOfWeek.valueOf(day).getValue() };
	}

	private static int[] currentMonthYear() {
		LocalDate current = LocalDate.now();
		return new int[] { current.getMonthValue(), current.getYear(), current.getDayOfWeek().getValue() };
	}

}
