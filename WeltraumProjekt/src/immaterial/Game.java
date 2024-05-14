package immaterial;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import immaterial.oracle.AskOracle;
import immaterial.oracle.AsynchronOracle;
import immaterial.oracle.CreateOracle;
import immaterial.oracle.FileOracle;
import immaterial.oracle.InputOracle;
import immaterial.oracle.LocalActionOracle;
import immaterial.oracle.MainActionOracle;
import immaterial.oracle.MathOracle;
import immaterial.oracle.ResourceOracle;
import immaterial.oracle.SearchOracle;
import immaterial.oracle.ShipBattleActionOracle;
import immaterial.oracle.TravelActionOracle;
import space.Galaxy;
import space.SolarSystem;
import space.celestial.Planet;
import space.lifeform.role.Player;

public class Game implements Serializable {
	

	//Konstanten, die von überall über die Game-Klasse zugegriffen werden können
	//Orakel liefern verschiedene Funktionen -> Reduktion von statischen Methoden innerhalb
	//der Orakel durch Konstanten
	private static final long serialVersionUID = 1L;
	public final static FileOracle FILEORACLE = new FileOracle();
	public final static AskOracle ASKORACLE = new AskOracle();
	public final static CreateOracle CREATEORACLE = new CreateOracle();
	public final static InputOracle INPUTORACLE = new InputOracle();
	public final static AsynchronOracle ASYNCHRONORACLE = new AsynchronOracle();
	public final static ResourceOracle RESOURCEORACLE = new ResourceOracle();
	public final static MainActionOracle MAINACTIONORACLE = new MainActionOracle();
	public final static LocalActionOracle LOCALACTIONORACLE = new LocalActionOracle();
	public final static TravelActionOracle TRAVELACTIONORACLE= new TravelActionOracle();
	public final static ShipBattleActionOracle SHIPBATTLEACTIONORACLE= new ShipBattleActionOracle();
	public final static MathOracle MATHORACLE = new MathOracle();
	public final static SearchOracle SEARCHORACLE = new SearchOracle();
	
	private Galaxy galaxy;
	private Player player;
	private transient Scanner scanner;

	public static void main(String[] args) {
		Game game;
		//Abfangen von Fehlern
		//Es wird getest, ob Files vorhanden sind --> Falls nicht, werden sie geschrieben
		//Außerdem werden Spielstände geladen --> Falls vorhanden
		//Hier werden die meisten Fehler des Programms abgefangen --> Durchreichung von Fehlern und hier abgefangen
		try {
		    FILEORACLE.testFiles();
		    game =FILEORACLE.loadGame();
		    if(game == null) {
		    	game = new Game();
		    	game.start();
			    FILEORACLE.saveGame(game.scanner, game);
		    }
		    INPUTORACLE.printBreakLineMultiple();
			System.out.println("Hauptspiel wird nun gestartet... Bitte warten...");
			INPUTORACLE.printBreakLineMultiple();
		    Thread.sleep(7000); 
		    INPUTORACLE.consoleClear();
		    if(game.getPlayer() != null) {
		    	if(game.getPlayer().getCurrentShip() == null) {
		    		game.mainLoop(false);
		    	}else {
		    		game.mainLoop(true);
		    	}
		    }
		} catch (FileNotFoundException e) {
		    System.err.println("FileNotFoundException --> " + e.getMessage() + "!");
		} catch (IOException e) {
		    System.err.println("IOException --> " + e.getMessage() + "!");
		} catch (NumberFormatException e) {
		    System.err.println("NumberFormatException --> " + e.getMessage() + "!");
		} 
		 catch (RuntimeException e) {
		    System.err.println("RuntimeException --> " + e.getMessage() + "!");
		} catch (Exception e) {
		    System.err.println("Exception --> " + e.getMessage() + "!");
		} finally {
			INPUTORACLE.printEndProgram();
		}


	}
	
	//Startet den Anfnagsprozess, da am Anfang noch kein Universum und co. existiert
	//Spieler kann Namen für Universum und co. festlegen
	//Plus Generierung des einzelnen Sonnensysteme
	//Plus Erstellung des Spielers
	//Spieler wird dabei von Anfang durch Text durch den Erstellungsprzess durchgeführt
	public void start() throws RuntimeException {
		INPUTORACLE.printBreakLineMultiple();
		System.out.println("Willkommen in der Weltraumsimulation");
		Scanner scanner = new Scanner(System.in);
		this.scanner = scanner;
		Galaxy galaxy = CREATEORACLE.setUniverseGalaxy(scanner);
		this.galaxy=galaxy;
		CREATEORACLE.setSolar(scanner, galaxy);
		galaxy.getInformation();
		ASKORACLE.askSunSystemInfo(scanner, galaxy);
		ASKORACLE.askMoonInfo(scanner, galaxy);
		Player player = CREATEORACLE.setPlayer(scanner);
		this.player=player;
		Planet start =ASKORACLE.askDestinationStart(scanner,galaxy);
		player.setCurrentPlace(start);
		player.setCurrentSystem(start.getSolarsystem());
		INPUTORACLE.printBreakLineBefore();
		System.out.println("Hallo " + player.getName() + "!");
		System.out.println();
		System.out.println("Du startest am Planeten <" + start.getName() 
		+ "> und im System <" + start.getSolarsystem().getName()+">");
		System.out.println("Sammle Ressourcen, Baue Schiffe & Lager, Reise von Planet zu Planet & Mond");
		System.out.println("und versuche zu überleben, denn du bist nicht der Einzige in dieser Welt");
		INPUTORACLE.printBreakLineMultiple();
		
	}


	//Das Hauptgeschehen spielt sich in dieser Methode ab
	//wird erst aufgerufen, wenn bereits Univerusm und co. erstellt wurden
	//Spieler kann Speicherstand innerhalb der mainLoop (im Schiff) oder der localLoop (ausgestiegen auf dem Planeten)
	//speichern und laden
	//MAINACTIONORACLE reguliert das Hauptgeschehen
	public void mainLoop(boolean mainLoop) {
		if(mainLoop) {
			INPUTORACLE.printBreakLineMultiple();
		}else {
			MAINACTIONORACLE.setLocalLoop(true);
		}
		MAINACTIONORACLE.checkAndRun(MAINACTIONORACLE,this,scanner);
	
	}
	
	
	public Galaxy getGalaxy() {
		return galaxy;
	}


	public Player getPlayer() {
		return player;
	}


	

}
