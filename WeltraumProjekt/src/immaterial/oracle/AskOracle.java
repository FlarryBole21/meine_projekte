package immaterial.oracle;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiPredicate;

import immaterial.Game;
import space.Galaxy;
import space.SolarSystem;
import space.celestial.Moon;
import space.celestial.Planet;
import space.celestial.star.Sun;


public class AskOracle extends Oracle{
	
	
	private static final long serialVersionUID = 1L;

	public AskOracle() {
		super.setType("Orakel der Fragen");
	}
	
	//Man kann sich am Anfang des Spiels bei der Erstellung der Sonnensystem über deren Planten und co. spoilern
	//also einen kurzen Einblick in die Daten bekommen
	//Dies ist nur eine einmalige Mögichkeit, danach müssen Platen und co. betreten werden
	//Hier wird der Spieler gefragt, ob er sich spoiler möchte
	public void askSunSystemInfo(Scanner scanner, Galaxy galaxy) {
		String input;
		int count=0;
		Game.INPUTORACLE.printBreakLineBefore();
		System.out.println("<SPOILER> Ein einmaliger Einblick in die erstellten Planeten & Sonnen");
		System.out.println("Planten- & Sonnen-Daten einsehen?");
		System.out.println(
				"(1 -> Infos einsehen (Nur Namen), 2 -> Infos einsehen (Mehr Details), "+
		"Irgendwas anderes -> Keine Infos): ");
		
		input = Game.INPUTORACLE.inputEmptyCheck(scanner);
		System.out.println();
		if (input.equals("1")) {
			for (SolarSystem solarsystem : galaxy.getSolarSystems()) {
				solarsystem.getBasicInformation();
			}
		} else if (input.equals("2")) {
			for (SolarSystem solarsystem : galaxy.getSolarSystems()) {
				System.out.println("||<----- Sonnensystem <" + solarsystem.getName() + "> ----->||");
				System.out.println();
				System.out.println("|<-- Sonnen-Daten -->|");
				System.out.println();
				System.out
						.println("Hauptsequenzstern <" + solarsystem.getMainstar().getName() + ">");
				System.out.println();
				count=0;
				for (Sun sun: solarsystem.getSuns()) {
					System.out.println("|<-- Sonnen-Daten <"+ sun.getName() + "> -->|");
					System.out.println();
					for(String information: sun.getInformation()) {
						System.out.println(information);
					}
					
					if (count != solarsystem.getSuns().size() - 1) {
						System.out.println();
					}
					count++;
				}
				System.out.println();
				System.out.println("|<-- Planeten-Daten -->|");
				System.out.println();
				count=0;
				for (Planet planet : solarsystem.getPlanets()) {
					System.out.println("|<-- Planeten-Daten <"+ planet.getName() + "> -->|");
					System.out.println();
					for (int b = 0; b < planet.getInformation()
							.size(); b++) {
						System.out.println(planet.getInformation().get(b));

					}
					if (count != solarsystem.getPlanets().size() - 1) {
						System.out.println();
					}
				}

			}

		}
		Game.INPUTORACLE.printBreakLineAfter();

	}
	
	//Das Gleiche wie oben aber nochmal für die Monde
	public void askMoonInfo(Scanner scanner, Galaxy galaxy) {
		String input;
		Game.INPUTORACLE.printBreakLineBefore();
		System.out.println("<SPOILER> Ein einmaliger Einblick in die erstellten Monde");
		System.out.println("Mond-Daten einsehen?");
		System.out.println(
				"(1 -> Infos einsehen (Nur Namen), 2 -> Infos einsehen (Mehr Details), Irgendwas anderes -> Keine Infos): ");
		input = Game.INPUTORACLE.inputEmptyCheck(scanner);
		System.out.println();
		if (input.equals("1")) {
			for (SolarSystem solarsystem : galaxy.getSolarSystems()) {
				for (Planet planet : solarsystem.getPlanets()) {
					String planetName = planet.getName();
					if (planet.getMoons().size() != 0) {
						System.out.println("Mond-Namen <Planet " + planetName + "> <Monde "
								+ planet.getMoonNames() + ">");
					} else {
						System.out.println("Mond-Namen <Planet " + planetName + "> <Keine Monde vorhanden>");
						System.out.println();
					}
				}
				System.out.println();
			}
		} else if (input.equals("2")) {
			for (SolarSystem solarsystem : galaxy.getSolarSystems()) {
				System.out.println();
				System.out.println("|<-- Mond-Daten -->|");
				System.out.println();
				int count1=0;
				for (Planet planet : solarsystem.getPlanets()) {
					String planetName = planet.getName();
					int count2 = 0;
					if (planet.getMoons().size() != 0) {
						count2 = 0;
						for (Moon moon: planet.getMoons()) {
							System.out.println("|<-- Mond-Daten <"+ moon.getName()+ ">-->|");
							System.out.println();
							for (String information: moon.getInformation()) {
								System.out.println(information);
							}
							if (count2 != planet.getMoons().size() - 1) {
								System.out.println();
							}
							
							count2++;
							
						}
						
						if (count1 != solarsystem.getPlanets().size() - 1) {
							System.out.println();
						}
						count1++;
					} else {
						System.out.println("Mond-Daten <Planet " + planetName + "> <Keine Monde vorhanden>");
						System.out.println();
					}
				}
				System.out.println();
			}
		}
		Game.INPUTORACLE.printBreakLineBefore();
		System.out.println("Diese Daten waren ein einmaliger Einblick. Planeten & Monde müssen danach mindestens");
		System.out.println("einmal betreten werden, bevor Ihre Informationen angezeigt werden können");
		Game.INPUTORACLE.printBreakLineAfter();
		
	}
	
	//Der Spieler wird gefragt zu welchem Sonnensystem er reisen möchte?
	//Am Anfang des Spiels
	//Spieler kann das System dnach nicht mehr wechslen
	public Planet askDestinationStart(Scanner scanner,Galaxy galaxy) {
		String input;
		Planet start = null;
		Game.INPUTORACLE.printBreakLineBefore();
		LinkedList<SolarSystem> solarsystems = galaxy.getSolarSystems();
		if(solarsystems.size() > 1) {
			System.out.println("Zu welchem Sonnensystem möchtest du reisen?");
		}
		if(solarsystems.size() == 3) {
			while (true) {
				System.out.print(
						"(1 -> " + solarsystems.get(0).getName()+", "
						+ "2 -> "+ solarsystems.get(1).getName()+", "+"3 -> "+
						solarsystems.get(2).getName()+"): ");
				input = scanner.nextLine();
				if (input.equals("1")) {
					SolarSystem solarsystem = solarsystems.get(0);
					start=checkNormalAtmosphere(solarsystem);
					break;
				}else if(input.equals("2")) {
					SolarSystem solarsystem = solarsystems.get(1);
					start=checkNormalAtmosphere(solarsystem);
					break;
					
				}else if(input.equals("3")) {
					SolarSystem solarsystem = solarsystems.get(2);
					start=checkNormalAtmosphere(solarsystem);
					break;	
				}else {
					System.err.println("Error --> Keine gültige Zahl!");
				}
			}
		}else if(solarsystems.size() == 2) {
			while (true) {
				System.out.print(
						"(1 -> " + solarsystems.get(0).getName()+", "
						+ "2 -> "+ solarsystems.get(1).getName()+"): ");
				input = scanner.nextLine();
				if (input.equals("1")) {
					SolarSystem solarsystem = solarsystems.get(0);
					start=checkNormalAtmosphere(solarsystem);
					break;
				}else if(input.equals("2")) {
					SolarSystem solarsystem = solarsystems.get(1);
					start=checkNormalAtmosphere(solarsystem);
					break;
					
				}else {
					System.err.println("Error --> Keine gültige Zahl!");
				}
			}
		}else if(solarsystems.size() == 1) {
			SolarSystem solarsystem = solarsystems.get(0);
			start=checkNormalAtmosphere(solarsystem);
		}
		Game.INPUTORACLE.printBreakLineAfter();
		return start;
	}
	
	
	//Es wird gerprüft ob das System mindestens einen Planeten mit normaler Atmosphäre besitzt
	//Aus der Liste wird dann der erste genommen
	//Relevant für Methode oben
	private Planet checkNormalAtmosphere(SolarSystem solarSystem) {
		Planet start = null;
		Optional<Planet> planetOptional = solarSystem.getPlanets().stream()
		        .filter(planet -> planet.getAtmosphere().getType().equals("Normal"))
		        .findFirst();
		if (planetOptional.isPresent()) {
		    start = planetOptional.get(); 
		
		} else {
			throw new RuntimeException("Keine normale Atmosphäre im System vorhanden!");
		}

		return start;
		
	}
	
	//Ein Ja Nein Frage
	// 1 -> Ja
	// Irgendwas anderers -> Nein
	public boolean trueFalseQuestion(Scanner scanner, Runnable runnable) {
		String input;
		runnable.run();
    	input = Game.INPUTORACLE.inputEmptyCheck(scanner);
    	if(input.equals("1")) {
    		return true;
    	}else {
    		return false;
    	}
	}
	


	
}
