import java.util.List;

public class ListAddRemoveFirstTest extends ListCreationPerformanceTest {
	
	public ListAddRemoveFirstTest(String testName, int nRuns, List<Integer> list, int nElements) {
		super(testName, nRuns, list, nElements);
		for(int i = 0; i < nElements; i++) {
			this.list.add(100);
		}
	}

	@Override
	protected void runTest() {
			list.remove(0);
			list.add(0,100);
	
	}
}
