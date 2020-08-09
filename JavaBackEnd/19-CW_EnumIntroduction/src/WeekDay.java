
public enum WeekDay {
SUN, MON, TUE, WED, THU,FRI, SAT;
	
	int between(WeekDay wd) {
		int indexFrom = ordinal();
		int indexTo = wd.ordinal();
		if(indexTo < indexFrom) {
			indexTo +=7;
		}
		return indexTo - indexFrom;
	}
}
