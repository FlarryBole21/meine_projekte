package immaterial.oracle;

import java.util.function.Consumer;
import immaterial.Game;
import space.celestial.Moon;
import space.celestial.Planet;
import space.celestial.EnterableCelestial;
import space.lifeform.role.Player;

public class MainActionOracle extends ActionOracle{
	
	private static final long serialVersionUID = 1L;
	private boolean localLoop;

	public MainActionOracle() {
		super.setType("Orakel der Hauptaktionen");
	}
	
	public boolean getLocalLoop() {
		return localLoop;
	}

	public void setLocalLoop(boolean localLoop) {
		this.localLoop = localLoop;
	}

	//Hauptschleife des Spiels
	//Der Spieler wird gefragt welche Option er wählen möchte
	//Spieler befindet sich hier im Raumschiff an einem Planeten oder Mond
	//Von hier aus kann man aber in weitere Untermenüs kommen
	@Override
	public void run() throws RuntimeException {
		testBeforeMain();
		String input;
		Player player = getGame().getPlayer();
		while(true) {
			checkPlayerHealth();
			if(!getLocalLoop()) {
				System.out.println("Hallo " + player.getName());
				System.out.println("Du befindest dich zur Zeit in einem Raumschiff <"+
				player.getCurrentShip().getType() + "> am Ort " 
						+ player.getCurrentPlace().getType()+ " <"+ player.getCurrentPlace().getName()+">");
				System.out.println("im System <"+ player.getCurrentSystem().getName()+">");
				Game.INPUTORACLE.printBreakLine();
				
				System.out.println("Was möchtest du jetzt machen?");
				System.out.println("0 -> Spiel beenden");
				System.out.println("1 -> Charakter-Info einsehen");
				System.out.println("2 -> Orts-Info einsehen");
				System.out.println("3 -> Schiffs-Info einsehen");
				System.out.println("4 -> Anliegenden lokalen Ort betreten");
				System.out.println("5 -> Zum einem anderen lokalen Ort (Planten oder Mond) reisen");
				System.out.println("6 -> Aktuellen Fortschritt speichern");
				input = Game.INPUTORACLE.inputEmptyCheck(getScanner());
			}else {
				input = "4";
			}
			
			if(input.equals("0")) {
			    break;
			}else if(input.equals("1")) {
				//Anzeigen der Spielerinformationen -> Name, Stats und co.
				Runnable runnable = ()->{
					for(String information: player.getInformation()) {
				    	System.out.println(information);
				    }
				};
				subMenu(runnable,true); 
			}else if(input.equals("2")) {
				Runnable runnable = ()->{
					//Prüfe, ob der Spieler den lokalen Ort schon besucht hat
					//Wenn ja, kann er sich die Informationen des lokalen orts anzeigen lassen
					if(player.getVisitedPlanets().contains(player.getCurrentPlace()) 
							|| player.getVisitedMoons().contains(player.getCurrentPlace())) {
						
						Consumer<EnterableCelestial> roundConsumer = (round) -> {
							for(String information: round.getInformation()) {
						    	System.out.println(information);
						    }
				        };
				        
				        roundConsumer.accept(player.getCurrentPlace());
						
					}else {
						System.err.println("Error --> Ort noch nicht besucht!");
						System.err.println("Nur Informationen über bereits besuchte Orte können abgefragt werden!");
						System.err.println("Rufe über das Hauptmenü die Charakter-Info auf, um zu sehen,");
						System.err.println("welche Orte du schon bereits besucht hast!");
						Game.INPUTORACLE.printBreakLineMultiple();
					}
		
				};
				subMenu(runnable,true); 
			}else if(input.equals("3")) {
				//Spieler kann Informatioennüber sein Raumschiff abrufen -> Stats und co.
				Runnable runnable = ()->{
					if(player.getCurrentShip() != null) {
						for(String information: player.getCurrentShip().getInformation()) {
					    	System.out.println(information);
					    }
						
					}else if(player.getShipParked() != null) {
						for(String information: player.getShipParked().getInformation()) {
					    	System.out.println(information);
					    }
					}else {
						System.err.println("Schiffs-Informationen konnten nicht geladen werden!");
						Game.INPUTORACLE.printBreakLine();
					}
					
				};
				subMenu(runnable,true); 
			}else if(input.equals("4")) {
				//Spieler betritt den lokalen Ort
				//Falls die Atmosphäre nicht normal ist --> Warnung
				Runnable runnable;
				
				if(getLocalLoop()) {
					runnable = ()->localLoop();
				}else {
					runnable = ()->{
						
						Consumer<EnterableCelestial> roundConsumer = (round) -> {
							if((!player.getCurrentPlace().getAtmosphere().getType().equals("Normal")) &&
									player.getCurrentSuit().getType().equals("Weltraumwanderer-Anzug")) {
								System.err.println("Warnung! " + "Die Atmosphäre ist " + round.getAtmosphere().getType() 
										+ " und du hast nur einen " + player.getCurrentSuit().getType()+ " an!");
								System.err.println("Es könnten ungewünschte Effekte auftreten!");
								enterLocalPlace(true);
								
							}else {
								enterLocalPlace(false);
							}
				        };
				
				        roundConsumer.accept(player.getCurrentPlace());
					};	
				}	
				subMenu(runnable,false); 
			}else if(input.equals("5")) {
				//Spieler wird gefragt wohin er reisen möchte -> Da kann er noch zum hauptmenü zurück
				//Spieler reist zu einem anderen Ort -> Sobald die Runden starten geht es nicht mehr
				//Reise benötigt Zeit in Runden und ist nicht garantiert
				//Spieler kann während der Reise auch nicht speichern
				Runnable runnable = () -> {
					Game.TRAVELACTIONORACLE.checkAndRun(Game.TRAVELACTIONORACLE,getGame(),getScanner());
				};
				
				subMenu(runnable,true); 
			}
			else if(input.equals("6")) {
				//Speichern des Spiels
				Game.INPUTORACLE.printBreakLineMultiple();
				Game.FILEORACLE.saveGame(getScanner(), getGame());
				Game.INPUTORACLE.printBreakLineMultiple();
			}else {
				Game.INPUTORACLE.printBreakLineMultiple();
				System.err.println("Error --> Ungültige Eingabe!");
				Game.INPUTORACLE.printBreakLineMultiple();
			}

		}
		
		super.getScanner().close();
		
	}
	
	

	//Spieler wird gefragt, ob er den lokalen Ort betreten möchte
	private void enterLocalPlace(boolean askEnter) {
		String input="1";
		if(askEnter) {
			System.out.println("Möchtest du diesen Planeten wirklich betreten?");
			System.out.print(
					"(1 -> Ja, Irgendwas anderes -> Nein): ");
			input = Game.INPUTORACLE.inputEmptyCheck(getScanner());
		}
		if(input.equals("1")) {
			getGame().getPlayer().addVisited(getGame().getPlayer().getCurrentPlace());
			getGame().getPlayer().leaveShip();
			Game.INPUTORACLE.printBreakLineMultiple();
			System.out.println("Du bist aus deinem Raumschiff ausgestiegen...");
			localLoop();
			
		}
		
	}

	//Übergang zum Submenü -> localLoop --> Spieler steigt aus seinem Raumschiff aus
	private void localLoop() {
		if(!getLocalLoop()) {
			Game.INPUTORACLE.printBreakLineMultiple();
		}
		Game.MAINACTIONORACLE.setLocalLoop(true);
		Game.LOCALACTIONORACLE.checkAndRun(Game.LOCALACTIONORACLE,getGame(),getScanner());
	}
	
	
	//Ausführen einer Funktion aus den Optionen, die der Spieler gewäht hat
	//Danach fragen, ob er zurück will in die Auswahl
	public void subMenu(Runnable runnable, boolean askBack) {
		boolean output;
		 while(true) {
		    	Game.INPUTORACLE.printBreakLineMultiple();
			    runnable.run();
			    if(askBack) {
			    	 output = backToMainMenu();
			    }else {
			    	break;
			    }
			    if(output) {
			    	break;
			    }
		    }
	}
	
	//Wenn das Leben des Spielers 0 ist -> Tot -> Programm-Ende
	public void checkPlayerHealth() {
		if(getGame().getPlayer().getHealth() <= 0) {
			Game.INPUTORACLE.printBreakLineMultiple();
			System.out.println("Spieler " + getGame().getPlayer().getName() + " ist gestorben!");
			Game.INPUTORACLE.printEndProgram();
		}
	}
	
	
	//Frage ob zurück zum Hauptmenü
	private boolean backToMainMenu() {
		
		Game.INPUTORACLE.printBreakLineMultiple();
	    Runnable runnable = () -> System.out.println("Zurück zum Haupt-Menü? "
	            + "1 -> Zurück, Irgendwas anderes -> Noch nicht");
	    boolean output = Game.ASKORACLE.trueFalseQuestion(getScanner(),runnable);
	    return output;
		
	}
	

}
