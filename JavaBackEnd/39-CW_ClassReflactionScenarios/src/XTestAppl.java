import java.lang.reflect.Constructor;

import telran.reflaction.Ix;

public class XTestAppl {

	public static void main(String[] args) throws Exception {

		if(args.length == 0) {
			System.out.println("no argument in the method main");
			return;
		}
		Class<?> clazz = Class.forName(args[0]);
		Constructor<?> constructor = clazz.getConstructor(int.class);
		Ix x = (Ix) constructor.newInstance(Integer.parseInt(args[1]));
		x.action();

	}

}
