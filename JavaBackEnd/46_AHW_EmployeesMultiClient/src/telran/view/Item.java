package telran.view;

import java.util.function.Consumer;

public interface Item {
String displayName();
void perform(InputOutput io);
default boolean isExit() {
	return false;
}
static public Item of(String name, Consumer<InputOutput> action, boolean isExit) {
	return new Item() {
		
		@Override
		public void perform(InputOutput io) {
			action.accept(io);
			
		}
		
		@Override
		public String displayName() {
			
			return name;
		}
		@Override
		public boolean isExit() {
			return isExit;
		}
	};
	
}
static public Item of(String name, Consumer<InputOutput> action) {
	return of(name, action, false);
}
static public Item exit() {
	return of("Exit", a->{}, true);
}
}
