package telran.utils;

public class MemoryService {

	/**
	 * 
	 * @return maximal size of memory in bytes that may be allocated using the
	 *         operator new
	 */
	public static int getMaxNewMemory() {
		int right = Integer.MAX_VALUE;
		int maxSize = 0;
		int left = 0;
		int middle = 0;
		while (left<=right) {
			middle = (int)(((long)right + left) >>1);//binary shift to left /2  * <<
			try {
				byte ar[] = new byte[middle];
				maxSize = middle;
				left = middle + 1;
					} catch (Throwable q) {
						right = middle - 1;
					}
		}
		return maxSize;
	}
}
