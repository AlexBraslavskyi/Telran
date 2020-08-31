/**
 * The task is to implement method rateShooting with linear complexity O[N]
 */
public class ShootingRating {
	/**
	 * Accepts array of "hitting" values, returns the "rate" 
	 * @param hittingResults - array of numbers, each number in range [0 to 10]
	 * @return the rate of shooting - the lower rate [0 to 10] of 70% of best hits (see example in main)
	 */
	public static int rateShooting(int[] hittingResults) {
		int lookUpTable[] = new int[11];
for(int i = 0; i < hittingResults.length; i++) {
	if(hittingResults[i] < 0 || hittingResults[i] > 10) {
		throw new IllegalArgumentException("Number must be in range 1-10");
	}
	lookUpTable[hittingResults[i]]++;
}

int unCheckedNum = hittingResults.length - (hittingResults.length*70/100);
int res = 0;
for(int i = 0; unCheckedNum >= 0; i++) {
	if(lookUpTable[i]!=0) {
		res = i;
		unCheckedNum = unCheckedNum - lookUpTable[i];
	}
	}
return res;
		
	}
	
	public static void main(String[] args) {
		// Testing example
		int[] hittingResults = {4,3,6,7,2,9,9,4,10,8};
		// The length is 10, so 70% of best hits is 7 best values.
		// So, we can ignore 3 worst values: 2,3,4
		// The next value is 4. This is the result.
		if (rateShooting(hittingResults) != 4) {
			throw new AssertionError();
		}
	}
}
