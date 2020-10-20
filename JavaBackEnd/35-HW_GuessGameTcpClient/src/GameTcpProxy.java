import telran.games.GuessGame;

public class GameTcpProxy implements GuessGame{

	String host;
	int port;
	
	
	public GameTcpProxy(String host, int port) {
		super();
		host = host;
		port = port;
	}

	@Override
	public String startGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String prompt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String move(String userInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFineshed() {
		// TODO Auto-generated method stub
		return false;
	}

}
