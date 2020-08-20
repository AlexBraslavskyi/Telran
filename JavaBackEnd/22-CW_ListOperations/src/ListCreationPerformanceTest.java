import java.util.List;

import telran.performance.PerformanceTest;

public class ListCreationPerformanceTest extends PerformanceTest {
	List<Integer> list;
	int nElements;

	
	
	public ListCreationPerformanceTest(String testName, int nRuns, List<Integer> list, int nElements) {
		super(testName, nRuns);
		this.list = list;
		this.nElements = nElements;
	}



	@Override
	protected void runTest() {
		for(int i = 0; i < nElements; i++) {
			list.add(100);
		}
		list.clear();
	}

}
