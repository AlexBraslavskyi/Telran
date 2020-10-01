package telran.util;

import java.util.ArrayList;
import java.util.function.Consumer;


public class MdArray <T> {

	private MdArray <T> [] array; //array of MdArray objects on one dimension less if array is not null the value should be null
	private T value; //MdArray with dimension 0 - scalar; if value is not null the array should be null
	
	private MdArray(int []dimentions, int dimention, T value) {
		if(dimention == dimentions.length) {
			array = null;
			this.value = value;
		}else {
			array = new MdArray[dimentions[dimention]];
			for(int i = 0; i < array.length; i++) {
				array[i] = new MdArray<T>(dimentions,dimention + 1, value);
			}
		}
		
	}
	
	public MdArray(int [] dimentions, T value) {
		this(dimentions, 0, value);
	}
/**
 * performs a given action defined by Consumer functional interface
 * for each value in multidimensional array  //?recursion
 * @param consumer
 */
	
	public void forEach(Consumer<T> consumer) {
		if(array == null) {
			consumer.accept(value);
			return;
		}
		for(MdArray<T> elmt : array) {
			elmt.forEach(consumer);
		}
	}
	
	

	
	/**
	 * from multidimensional array creates and fills regular array of the values
	 * @return
	 */
	public T[] flatMap(T[]a) {
		ArrayList<T> arr = new ArrayList<T>();
		forEach(arr::add);
		return  arr.toArray(a);
	}
	
	/**
	 * set a given value into MdArray as scalar 
	 * throws a following exceptions
	 * Wrong index exception
	 * Wrong number of indexes
	 * @param indexes
	 * @param value
	 */
	public void setValue(int []indexes, T value) {
		
		MdArray <T> scalar = getMdScalar(indexes);
		scalar.value = value;
		
	}

	/**
	 * get value from MdArray as scalar 
	 * throws a following exceptions
	 * Wrong index exception
	 * Wrong number of indexes
	 * @param indexes
	 * @return 
	 */
	T getValue(int[] indexes) {
		MdArray <T> scalar = getMdScalar(indexes);
		return scalar.value;
		
	}

	private MdArray <T>getMdScalar(int[] indexes) {
		MdArray<T> res = this;
		for (int ind: indexes) {
			try {		
				res = res.array[ind];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new RuntimeException("Wrong index exception " + e.getMessage());
			} catch (NullPointerException e) {
				throw new RuntimeException("Too much indexes");
			}
		}	
		if (res.array != null) {
			throw new RuntimeException("Too few indexes");
		}
		return res;
	}
}
