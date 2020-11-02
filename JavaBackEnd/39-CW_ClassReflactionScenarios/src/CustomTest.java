
public class CustomTest {

		

	void joinStrings(int nRuns) {
		String str = "";
		for(int i = 0; i< nRuns; i++) {
			str += "Hello";
		}
	}
	
	void joinBuilder(int nRuns) {
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i<nRuns;i++) {
			builder.append("Hello");
		}
	}
}
