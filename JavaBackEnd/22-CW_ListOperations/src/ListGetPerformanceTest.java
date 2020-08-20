import java.util.List;

public class ListGetPerformanceTest extends ListAddRemoveFirstTest {

	public ListGetPerformanceTest(String testName, int nRuns, List<Integer> list, int nElements) {
		super(testName, nRuns, list, nElements);
		
	}

	@Override
	protected void runTest() {
			list.get((int)(Math.random()*list.size()));
	
	}
}
