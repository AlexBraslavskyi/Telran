package telran.util;

import java.util.function.Consumer;

public class MdArray {
	
	private MdArray [] array; //array of MdArray objects on one dimension less if array is not null the value should be null
	private Integer value; //MdArray with dimension 0 - scalar; if value is not null the array should be null
	
	private MdArray(int []dimentions, int dimention, Integer value) {
		if(dimention == dimentions.length) {
			array = null;
			this.value = value;
		}else {
			array = new MdArray[dimentions[dimention]];
			for(int i = 0; i < array.length; i++) {
				array[i] = new MdArray(dimentions,dimention + 1, value);
			}
		}
		
	}
	
	public MdArray(int [] dimentions, Integer value) {
		this(dimentions, 0, value);
		
	}
/**
 * performs a given action defined by Consumer functional interface
 * for each value in multidimensional array  //?recursion
 * @param consumer
 */
	
	public void forEach(Consumer<Integer> consumer) {
		
		//TODO
	}
	
	
	/**
	 * from multidimensional array creates and fills regular array of the values
	 * @return
	 */
	public Integer[] flatMap() {
		//TODO
		return null;
	}
	
	/**
	 * set a given value into MdArray as scalar 
	 * throws a following exceptions
	 * Wrong index exception
	 * Wrong number of indexes
	 * @param indexes
	 * @param value
	 */
	public void setValue(int []indexes, Integer value) {
		
		MdArray scalar = getMdScalar(indexes);
		scalar.value = value;
		
	}

	/**
	 * get value from MdArray as scalar 
	 * throws a following exceptions
	 * Wrong index exception
	 * Wrong number of indexes
	 * @param indexes
	 */
	private Integer getValue(int[] indexes) {
		MdArray scalar = getMdScalar(indexes);
		return scalar.value;
		
	}

	private MdArray getMdScalar(int[] indexes) {
	
		try {
			MdArray res = array[indexes[0]];
					for(int i = 1; i < indexes.length; i++) {
						res = res.array[indexes[i]];
					}
					if(res.array!=null) {
						throw new RuntimeException("Too few indexess");
					}
					return res;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("Wrong index exception" + e.getMessage());
		}
		catch (NullPointerException e) {
			throw new RuntimeException("Too much indexes");
		}
	
	}
}
