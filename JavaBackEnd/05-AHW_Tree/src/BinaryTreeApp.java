import java.util.Comparator;
import java.util.Random;

public class BinaryTreeApp {

	private static final int GEN_CNT = 20;
	static Random gen = new Random(12345);
	
	public static void main(String[] args) {
		Integer[] intData = {17, 20, 10, -4, 100, 12, 18, 15, 19, 48}; 
		
		Comparator<Integer> comp = Comparator.naturalOrder();
		BinaryTree<Integer> treeSet = new BinaryTree<>(comp);
		
		for (int i=0; i < GEN_CNT; i++) {
			treeSet.add(gen.nextInt(100));
		}
		System.out.println(treeSet); // test recursive print
		
		for (Integer i : treeSet) { // test iterator
			System.out.print(" " + i);
		}
		System.out.println();
		
		System.out.println(treeSet.size());
		System.out.println(treeSet.depth());
		System.out.println(treeSet.contains(7));
		
		treeSet.remove(51);
		System.out.println(treeSet);
//		treeSet.printTree();
	}

}
