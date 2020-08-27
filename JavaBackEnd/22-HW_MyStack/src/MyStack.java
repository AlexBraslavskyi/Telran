import java.util.List;
import java.util.NoSuchElementException;

public class MyStack {

	private static final int STACK_SIZE = 100;

	private List<Integer> list;
	int max = 0;
	public MyStack(List<Integer> list) {
		super();
		this.list = list;

	}

	public void push(int number) throws Exception {
		if (list.size() >= STACK_SIZE) {
			throw new StackOverflowError("stack size: " + STACK_SIZE);
		}
		 if (number > max){ 
	           list.add(2 * number - max); 
	           max = number; 
	        }else {
	            list.add(number); 
	        }
	}

	public int pop() throws Exception {  //get tail or peek O(1)

		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
		int peek = list.get(list.size()-1);
		int pop = list.remove(list.size()-1);
		int maxTmp = max;
		 if (peek > max) { 
			 max = 2 * max - peek; 
	           return maxTmp;
	        } 
	
		return pop;
	}

	public boolean isEmpty() {
		return list.size() == 0 ? true : false;
	}

	public int getMax() throws Exception { 
		if (isEmpty()) {
			throw new NoSuchElementException("Stack underflow");
		}
		 return max;

	}
	 
}
