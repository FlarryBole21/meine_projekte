package immaterial.oracle;

import java.util.LinkedList;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import immaterial.Game;
import space.celestial.EnterableCelestial;
import space.environment.atmosphere.Atmosphere;
import space.inventory.resource.Resource;
import space.lifeform.role.Player;

public class LocalActionOracle extends ActionOracle{

	private static final long serialVersionUID = 1L;

	public LocalActionOracle() {
		super.setType("Orakel der lokalen Aktionen");
	}
	
	//Subschleife des Spiels
	//Der Spieler wird gefragt welche Option er wählen möchte
	//Spieler befindet sich hier NICHT im Raumschiff und ist auf einem Planeten / Mond
	//Von hier aus kann man aber wieder zurück ins Hauptmenü --> Spieler kann wieder in sein Schiff steigen
	//Reisen ist hier nicht möglich
	@Override
	public void run() throws RuntimeException{
		testBeforeMain();
		//Planet planet = (Planet) getGame().getPlayer().getCurrentPlace();
		String input;
		Player player = getGame().getPlayer();
		while(true) {
			System.out.println("Du befindest dich zur Zeit in keinem Raumschiff am Ort " 
			+ getGame().getPlayer().getCurrentPlace().getType()+ " <"+ getGame().getPlayer().getCurrentPlace().getName()+">");
			System.out.println("im System <"+ getGame().getPlayer().getCurrentSystem().getName()+">");
			testAtmoToPlayer(); 
			Game.MAINACTIONORACLE.checkPlayerHealth();
			Game.INPUTORACLE.printBreakLine();
			System.out.println("Was möchtest du jetzt machen?");
			System.out.println("0 -> Spiel beenden");
			System.out.println("1 -> Charakter-Info einsehen");
			System.out.println("2 -> Orts-Info einsehen");
			System.out.println("3 -> Zurück zum Raumschiff");
			System.out.println("4 -> Aktuellen Fortschritt speichern");
			input = Game.INPUTORACLE.inputEmptyCheck(getScanner());
			
			if(input.equals("0")) {
				Game.INPUTORACLE.printEndProgram();
				System.exit(0);
			}else if(input.equals("1")) {
				//Charakter-Informationen -> Siehe Hauptmenü
				Runnable runnable = ()->{
					for(String information: player.getInformation()) {
				    	System.out.println(information);
				    }
				};
				Game.MAINACTIONORACLE.subMenu(runnable, true);
				Game.INPUTORACLE.printBreakLineMultiple();
			
			}else if(input.equals("2")) {
				//Orts-Informationen -> Siehe Hauptmenü
				Runnable runnable = ()->{
					Consumer<EnterableCelestial> roundConsumer = (round) -> {
						for(String information: round.getInformation()) {
					    	System.out.println(information);
					    }
			        };
			        roundConsumer.accept(player.getCurrentPlace());
				};
				
				Game.MAINACTIONORACLE.subMenu(runnable, true);
		        Game.INPUTORACLE.printBreakLineMultiple();
			}else if(input.equals("3")) {
				//Spieler steigt wieder in sein Schiff ein -> Rückkehr ins Hauptmenü
				Game.MAINACTIONORACLE.setLocalLoop(false);
				player.enterShip();
				Game.INPUTORACLE.printBreakLineMultiple();
				System.out.println("Du steigst wieder in dein Raumschiff ein...");
				Game.INPUTORACLE.printBreakLineMultiple();
				break;
				
			}else if(input.equals("4")) {
				//Speichern
				Game.INPUTORACLE.printBreakLineMultiple();
				Game.FILEORACLE.saveGame(getScanner(), getGame());
				Game.INPUTORACLE.printBreakLineMultiple();
				
			}else {
				Game.INPUTORACLE.printBreakLineMultiple();
				System.err.println("Error --> Ungültige Eingabe!");
				Game.INPUTORACLE.printBreakLineMultiple();
			}

		}
		
		
		
	}
	
	//Spieler wird gefragt welche Ressource er gerne abbaucen möchte und wie viel
	private void inputForResource() {
		
		LinkedList<Resource> listedResources = new LinkedList<>();
		Set<Resource> resources = getGame().getPlayer().getCurrentPlace().getResource();
		String input;
		int numberValue;
		for(Resource resource: resources) {
			listedResources.add(resource);
		}
		
		int index=1;
		System.out.println("Welche Ressource möchtest du abbauen?");
		
		for(Resource resource: getGame().getPlayer().getCurrentPlace().getResource()) {
			System.out.println(index +"-> " + resource.getType() + " <" + resource.getAmount() + ">");
			index++;
		}
	
		Resource resource = Game.RESOURCEORACLE.getResource(getScanner(),listedResources);
		if(resource.getAmount() > 0) {
			System.out.println("Geforderte Ressource gefunden -> " + resource.getType() + " <" + resource.getAmount()+">");
			System.out.println("Wie viele möchtest du abbauen?");
			while(true) {
				input = Game.INPUTORACLE.inputEmptyCheck(getScanner());
				try {
					numberValue = Integer.valueOf(input);
					if(numberValue <= resource.getAmount()) {
						
						//.setAmount(numberValue);
						getGame().getPlayer().addResourceToInventory(resource, numberValue);
						//getGame().getPlayer().getCurrentPlace().removeResource(resource, numberValue);
					}else {
						System.err.println("Error -> So viel ist von der Ressource nicht vorhanden!");
						System.err.println("Nur " + resource.getType() + " <"+ resource.getAmount()+ "> vorhanden!" );
					}
					break;
				}catch(RuntimeException e) {
					System.err.println("Error -> Keine Zahl" + e.getMessage());
				}
			}
		}
	
	}
	
	//Bei jedem mal, wenn man den Ort betritt oder wenn man nach der Auswahl einer Option wieder ins Submenü zurückkehrt
	//wird geprüft ob der Spieler einen normalen Anzug anhat und ob die Atmosphäre normal ist
	//Wenn er beispielsweise nur einen normalen Anzug anhat und in einer toxischen Atmospähre ist --> Spieler bekommt Schaden
	private void testAtmoToPlayer() {
		
		Atmosphere atmosphere = getGame().getPlayer().getCurrentPlace().getAtmosphere();
		
		Predicate<String> normalSuit = (check) ->{
			return check.equals("Weltraumwanderer-Anzug");
		};
		
		Boolean checked = normalSuit.test(getGame().getPlayer().getCurrentSuit().getType());
		
		atmosphere.inhale(checked, getGame().getPlayer());
		
	}
}


