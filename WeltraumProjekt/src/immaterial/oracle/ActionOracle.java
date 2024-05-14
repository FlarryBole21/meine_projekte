package immaterial.oracle;

import java.util.Scanner;

import immaterial.Game;

public abstract class ActionOracle extends Oracle implements Runnable{

	private static final long serialVersionUID = 1L;
	private Game game;
	private Scanner scanner;
	
	public Scanner getScanner() {
		return scanner;
	}


	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}


	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void checkAndRun(ActionOracle oracle, Game game, Scanner scanner) {
		oracle.setGame(game);
		oracle.setScanner(scanner);
		oracle.run();
	}
	
	//Spiel muss von überall erreichbar sein
	//Scanner, Player und Galaxie dürfen nicht null sein
	public void testBeforeMain() throws RuntimeException{
		if(getGame() == null) {
			throw new RuntimeException("Spiel nicht gesetzt");
		}
		
		if(getGame().getGalaxy() == null) {
			throw new RuntimeException("Galaxie nicht gesetzt");
		}
		
		if(getGame().getPlayer() == null) {
			throw new RuntimeException("Spieler nicht gesetzt");
		}
		
		if(getScanner() == null) {
			Scanner scanner = new Scanner(System.in);
			setScanner(scanner);
		}
		
	}
	
}
