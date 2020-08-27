import java.util.Collection;
import java.util.Random;

import telran.performance.PerformanceTest;
public class CollectionContainsPerformanceTest extends PerformanceTest {
Collection <Integer> collection;
Random gen = new Random();

public CollectionContainsPerformanceTest(String testName, int nRuns, Collection<Integer> collection, int nElements) {
	super(testName, nRuns);
	this.collection = collection;
	for(int i = 0; i < nElements; i++) {
		this.collection.add(gen.nextInt());
	}
}

@Override
protected void runTest() {
	this.collection.contains(gen.nextInt());
	
}
	
}
