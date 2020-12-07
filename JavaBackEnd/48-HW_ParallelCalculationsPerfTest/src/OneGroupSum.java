import java.util.Arrays;
import java.util.concurrent.Callable;

public class OneGroupSum implements Callable<Long> {

	int []group;
	
	public OneGroupSum(int[] group) {
		super();
		this.group = group;
	}

	@Override
	public Long call() throws Exception {
	
		return Arrays.stream(group).asLongStream().sum();
	}

}
