import telran.performance.framework.annotations.Test;

public class CustomTest {
private static final int N_RUNS = 1000000;
		

@Test(nRuns = N_RUNS)
void joinBuilder(int nRuns) {
	
	StringBuilder builder = new StringBuilder();
	for(int i = 0; i<nRuns;i++) {
		builder.append("Hello");
	}
}
@Test(nRuns = N_RUNS)
	void joinStrings(int nRuns) {
		String str = "";
		for(int i = 0; i< nRuns; i++) {
			str += "Hello";
		}
	}

}
