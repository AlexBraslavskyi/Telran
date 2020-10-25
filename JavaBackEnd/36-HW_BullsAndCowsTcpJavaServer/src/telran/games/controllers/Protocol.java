package telran.games.controllers;


import telran.games.impl.BullsCowsGameImpl;
import telran.games.interfaces.GuessGame;
import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
import telran.net.server.ProtocolJava;

public class Protocol implements ProtocolJava {
	GuessGame game = new BullsCowsGameImpl();

	@Override
	public ResponseJava getResponse(RequestJava request) {
		try {
			switch (request.requestType) {
			case "start":
				return new ResponseJava(TcpResponseCode.OK, game.startGame());
			case "prompt":
				return new ResponseJava(TcpResponseCode.OK, game.prompt());
			case "move":
				return new ResponseJava(TcpResponseCode.OK, game.move((String)request.requestData));
			case "is_finished":
				return new ResponseJava(TcpResponseCode.OK, game.isFinished() ? "yes" : "no");
			default:
				throw new Exception("Unknown Request" + request.requestType);
			}
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.WRONG_REQUEST, e.getMessage());
		}
	}
}
