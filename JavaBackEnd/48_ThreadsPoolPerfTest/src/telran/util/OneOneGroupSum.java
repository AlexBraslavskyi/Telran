package telran.util;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class OneOneGroupSum implements Callable<Long> {
	int[] group;
	public OneOneGroupSum(int[] group) {
		super();
		this.group = group;
	}

	@Override
	public Long call() throws Exception {
		return Arrays.stream(group).asLongStream().sum();
	}

}
