package telran.util.tests;

import java.util.function.Predicate;

public class DividorPredicate implements Predicate<Integer> {
int dividor;
public DividorPredicate(int dividor) {
	this.dividor = dividor;
}
	@Override
	public boolean test(Integer t) {
		
		return t % dividor == 0;
	}

}
