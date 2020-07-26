package telran.util;

public interface MyPredicate<T> {

	boolean test(T obj);

	default MyPredicate<T> negate() {
		return new NegateOperatorPredicate<T>(this);
	}

	default MyPredicate<T> and(MyPredicate<T> other) {
		return new AndOperatorPredicate<T>(this, other);
	}

	default MyPredicate<T> or(MyPredicate<T> other) {
		return new OrOperatorPredicate<T>(this, other);
	}
}

class NegateOperatorPredicate<T> implements MyPredicate<T> {
	MyPredicate<T> src;

	NegateOperatorPredicate(MyPredicate<T> src) {
		this.src = src;
	}

	@Override
	public boolean test(T obj) {
		return !src.test(obj);
	}
}

class AndOperatorPredicate<T> implements MyPredicate<T> {
	MyPredicate<T> src, other;

	AndOperatorPredicate(MyPredicate<T> src, MyPredicate<T> other) {
		this.src = src;
		this.other = other;
	}

	@Override
	public boolean test(T obj) {
		return src.test(obj) && other.test(obj);
	}
}

class OrOperatorPredicate<T> implements MyPredicate<T> {
	MyPredicate<T> src, other;

	OrOperatorPredicate(MyPredicate<T> src, MyPredicate<T> other) {
		this.src = src;
		this.other = other;
	}

	@Override
	public boolean test(T obj) {
		return src.test(obj) || other.test(obj);
	}
}
