package immaterial.oracle;

import java.util.LinkedList;
import immaterial.Game;
import space.celestial.Moon;
import space.celestial.Planet;
import space.celestial.EnterableCelestial;

public class TravelActionOracle extends ActionOracle {

	private static final long serialVersionUID = 1L;
	
	private int round;
	private int maxRound;
	private double battleChance;
	
	public TravelActionOracle() {
		super.setType("Orakel der Reise-Aktionen");
		this.round=3;
		this.maxRound = round;
		this.battleChance=0.4;
	}
	
	
	public boolean removeRound() {
		if(round > 0) {
			this.round -= 1;
			return true;
		}else {
			return false;
		}
	}
	
	//Spieler wird gefragt wo er hinreisen möchte
	//Er kann ins Hauptmenü zurück, wenn er sich nocht nicht komplett entschieden hat (genauer Ortsname)
	//Nachdem die Runden starten wird er entweder heile ankommen oder sterben
	@Override
	public void run() {
		testBeforeMain();
		EnterableCelestial destinationCelestial;
		if(getGame().getPlayer().getCurrentPlace() instanceof Planet) {
			destinationCelestial = whereYouWannaGoFromPlanet();
			
		}else {
			destinationCelestial = backToPlanetFromMoon();
		}
		if(destinationCelestial == null) {
			return;
		}
		if(destinationCelestial.getName().equals(getGame().getPlayer().getCurrentPlace().getName())) {
			return;
		}
		boolean travelOutput = travelPhase(destinationCelestial);
		if(travelOutput) {
			System.out.println("Du hast die Fahrt überstanden!");
			getGame().getPlayer().setCurrentPlace(destinationCelestial);
			Game.INPUTORACLE.printBreakLineMultiple();
			System.out.println("Du bist jetzt in " + destinationCelestial.getType() 
			+ " <" + destinationCelestial.getName()+">");
			
		}else {
			System.err.println("Dein Schiff wurde zerstört");
			System.err.println("Fahrt fehlgeschlagen!");
			System.err.println("Du hast die Fahrt nicht überlebt und bist nun tot!");
			Game.INPUTORACLE.printBreakLineMultiple();
			getGame().getPlayer().setHealth(0);
			Game.MAINACTIONORACLE.checkPlayerHealth();
			Game.INPUTORACLE.printBreakLineMultiple();
		}
	}
	

	//Fragt wohin der Spieler hin will
	//Diese Methode wird nur ausgeführt, wenn der Spieler sich auf einem Planeten befindet
	//Spieler kann nur in direkt benachbarte Planeten oder Monde des aktuellen Planeten reisen
	private EnterableCelestial whereYouWannaGoFromPlanet() {
		String input;
		EnterableCelestial destinationCelestial = null;
		System.out.println("Du kannst nur zur sich in der Nähe befindenen Planeten & Monden reisen");
		System.out.println("Reisen? "
	            + "1 -> Planet, 2 -> Mond,  Irgendwas anderes -> Doch nicht");
		while(true) {
			input = Game.INPUTORACLE.inputEmptyCheck(super.getScanner());
			if(input.equals("1")) {
				destinationCelestial =checkPlanetsOfPlanet();
				return destinationCelestial;
				
			}else if (input.equals("2")) {
				
				destinationCelestial = checkMoonsOfPlanet();
				if(destinationCelestial == null) {
					System.out.println("Planet besitzt keine Monde!");
					return destinationCelestial;
				}else {
					return destinationCelestial;
				}
			}
			
			return destinationCelestial;
			
		}
	}
	
	
	//Spieler kann vom Mond nur zum jeweiligen Planeten zurückreisen
	//Wenn also der Spieler irgendo anders hinmöchte, dann muss er erst zum Planeten zurückreisen
	private EnterableCelestial backToPlanetFromMoon() {
		String input;
		Moon moon = (Moon) getGame().getPlayer().getCurrentPlace();
		EnterableCelestial destinationCelestial = null;
		System.out.println("Du kannst nur zum jeweiligen Planeten zurückreisen!");
		System.out.println("Reisen? "
	            + "1 -> Züruck zum Planeten "+ moon.getPlanet().getName()+", Irgendwas anderes -> Doch nicht");
		while(true) {
			input = Game.INPUTORACLE.inputEmptyCheck(super.getScanner());
			if(input.equals("1")) {
				destinationCelestial = moon.getPlanet();
				return destinationCelestial;
				
			}else {
				break;
			}
			
		}
		return destinationCelestial;
	}
	
	//Hier ist der Punkt wo die Reise beginnt -> Spieler hat sich entschieden und kann nciht mehr umkehren
	//Die Fahrt dauert eine gewisse Anzahl an Runden
	//Der Spieler kann währenddessen von anderen Schiffen angegriffen werden
	private boolean travelPhase(EnterableCelestial destination){
		
		double randomChance;
		
		System.out.println("Du bist nun unterwegs nach " 
		+ destination.getType() + " <" + destination.getName() + ">");
		System.out.println("Dieser Prozess nimmt jedoch etwas Zeit in Anspruch.");
		System.out.println("Hoffentlich läuft alles gut!");
		
		while(true) {
			System.out.println("Du befindest dich nun in Runde " + (round+1) + "/" + (maxRound+1));
			randomChance = Math.random();
			 if (randomChance <= battleChance) {
				 System.out.println("Du fährst eine Runde...");
				 travelTime();
	             System.out.println("Ein gegnerisches Schiff nährt sich!");
	             travelTime();
	             Game.INPUTORACLE.printBreakLine();
	             System.out.println("Betrete Kampf-Phase");
	             travelTime();
	             Game.INPUTORACLE.printBreakLine();
	             Game.SHIPBATTLEACTIONORACLE.checkAndRun(Game.SHIPBATTLEACTIONORACLE,getGame(),getScanner());
	             if(getGame().getPlayer().getCurrentShip().getHealth() <= 0) {
	     			return false;
	     		 }else {
	     			Game.INPUTORACLE.printBreakLine();
	     			System.out.println("Gegnerisches Schiff erfolgreich besiegt!");
	     			travelTime();
	     		 }
	             
	        } else { 
	     
	            System.out.println("Du fährst eine Runde...");
	            travelTime();
	            System.out.println("Nichts passiert!");
	            travelTime();
	            Game.INPUTORACLE.printBreakLine();
	        }
			 
			if(removeRound()) {
				System.out.println("Runde beendet!");
				travelTime();
				Game.INPUTORACLE.printBreakLine();
				
			}else {
				System.out.println("Runde beendet!");
				travelTime();
				Game.INPUTORACLE.printBreakLine();
				break;
			}
		}
		
		
		if(getGame().getPlayer().getCurrentShip().getHealth() <= 0) {
			return false;
		}
		this.round=maxRound;
		return true;
		
	}
	
	//Prüft bie benachbarten Planeten des aktuellen Planeten
	//Basiernd darauf können die Reise-optionen bestimmt werden
	private Planet checkPlanetsOfPlanet() {
		LinkedList<Planet> currentPlanets = super.getGame().getPlayer().getCurrentSystem().getPlanets();
		Planet currentPlanet = (Planet) super.getGame().getPlayer().getCurrentPlace();
		Planet destinationPlanet = null;
		int index = Game.SEARCHORACLE.findIndex(currentPlanets, currentPlanet);
		if(Game.SEARCHORACLE.checkIfIndexIsPositive(index)) {
			if(index != 0 && (index != currentPlanets.size()-1)) {
				System.out.println("Planten in der Nähe wurden geprüft...");
				System.out.println("Zu welchem Reisen? "
			            + "1 -> "+ currentPlanets.get(index-1).getName() 
			            + ", 2 -> "+ currentPlanets.get(index+1).getName()+",  Irgendwas anderes -> Doch nicht");
				while(true) {
					String input = Game.INPUTORACLE.inputEmptyCheck(super.getScanner());
					if(input.equals("1")) {
						destinationPlanet =currentPlanets.get(index-1);
						break;
					}else if (input.equals("2")) {
						destinationPlanet =currentPlanets.get(index+1);
						break;
					}else {
						break;
					}
				}
				
			}else if( index == 0) {
				System.out.println("Zu diesem Planeten reisen? "
			            + "1 -> "+ currentPlanets.get(index+1).getName() 
			            +  ", Irgendwas anderes -> Doch nicht");
				while(true) {
					String input = Game.INPUTORACLE.inputEmptyCheck(super.getScanner());
					if(input.equals("1")) {
						destinationPlanet =currentPlanets.get(index+1);
						break;
					}else {
						break;
					}
				}
			}else {
				System.out.println("Zu diesem Planeten reisen? "
			            + "1 -> "+ currentPlanets.get(index-1).getName() 
			            +  ", Irgendwas anderes -> Doch nicht");
				while(true) {
					String input = Game.INPUTORACLE.inputEmptyCheck(super.getScanner());
					if(input.equals("1")) {
						destinationPlanet =currentPlanets.get(index-1);
						break;
					}else {
						break;
					}
				}
			}
			
		}else {
			throw new RuntimeException("Planet nicht im System vorhanden!");
		}
		
		return destinationPlanet;
	}
	
	
	//Prüft die Monde des aktuellen Planeten 
	//Basierend darauf können die Reise--Optionen bestimmt werden
	private EnterableCelestial checkMoonsOfPlanet() {
		LinkedList<Moon> currentMoons; 
		Planet planet = (Planet)super.getGame().getPlayer().getCurrentPlace();
		currentMoons = planet.getMoons();
		Moon destinationMoon = null;
		int numberInput=0;
			if(currentMoons.size() > 0) {
				System.out.println("Zu welchem Reisen?");
				for(int i =0; i<currentMoons.size();i++) {
					System.out.println((i+1) +"-> " + currentMoons.get(i).getName());
				}
				System.out.println("exit -> Doch nicht");
				while(true) {
					String input = Game.INPUTORACLE.inputEmptyCheck(super.getScanner());
					try {
						numberInput = Integer.valueOf(input);
						if (numberInput > currentMoons.size()) {
							System.err.println("Error -> Zu hohe oder niedrige Eingabe");
						}
					}catch(RuntimeException e) {
						System.err.println("Error -> Ungültige Eingabe!");
					}
					
					if(input.equals("1") && currentMoons.size() >= 1) {
						destinationMoon =currentMoons.get(0);
						break;
					}else if (input.equals("2") && currentMoons.size() >= 2) {
						destinationMoon =currentMoons.get(1);
						break;
						
					}else if (input.equals("3") && currentMoons.size() >= 2) {
						destinationMoon =currentMoons.get(2);
						break;
						
					}else if (input.equals("4") && currentMoons.size() >= 2) {
						destinationMoon =currentMoons.get(3);
						break;
						
					}else if (input.equals("5") && currentMoons.size() >= 2) {
						destinationMoon =currentMoons.get(4);
						break;
						
					}else if (input.toLowerCase().equals("exit")) {
						return getGame().getPlayer().getCurrentPlace();	
					}
					
				}
				
			}
			
		return destinationMoon;
	}
	
	
	private void travelTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

}
