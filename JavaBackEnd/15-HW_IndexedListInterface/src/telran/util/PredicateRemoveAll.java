package telran.util;

import java.util.function.Predicate;
import telran.util.IndexedList;

public class PredicateRemoveAll<T> implements Predicate<T> {
	IndexedList <T> pattern;
	public PredicateRemoveAll(IndexedList <T> pattern) {
		this.pattern = pattern;
	}
	@Override
	public boolean test(T t) {

		return pattern.contains(t);
	}

}
