
public class EnumTestAppl {

	public static void main(String[] args) {
//		WeekDay wd = args.length == 0 ? WeekDay.SUN : WeekDay.valueOf(args[0]);
//	displayWeekDayComment(wd);//WeekDay.THU
//	for(WeekDay wd: WeekDay.values()) {
//		displayWeekDayComment(wd);
//	}

		WeekDay wdFrom = WeekDay.FRI;
		WeekDay wdTo = WeekDay.TUE;
		System.out.printf("between %s and %s there %d days\n",wdFrom,wdTo,wdFrom.between(wdTo));
	}
static void displayWeekDayComment(WeekDay wd) {
	switch(wd) {
	case SUN: displayComment(wd,"weekend if you are not in Israel");break;
	case FRI: displayComment(wd,"weekend if you are in Israel");break;
	case SAT:displayComment(wd,"weekdend if are in most countrris including Israel");break;
	default:displayComment(wd,"regular day");
	
}	}
private static void displayComment(WeekDay wd, String string) {
	System.out.printf("%s %s\n",wd,string);

}
}
