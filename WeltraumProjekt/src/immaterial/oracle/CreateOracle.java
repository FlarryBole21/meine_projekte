package immaterial.oracle;


import java.util.Scanner;
import immaterial.Game;
import space.Galaxy;
import space.SolarSystem;
import space.Universe;
import space.celestial.Moon;
import space.celestial.Planet;
import space.celestial.star.MainSequenceStar;
import space.lifeform.role.Player;

public class CreateOracle extends Oracle{
	
	
	private static final long serialVersionUID = 1L;
	private Universe universe;
	
	public CreateOracle() {
		super.setType("Orakel der Kreation");
	}
	
	//Erstellung des Universums und einer Galaxie
	public Galaxy setUniverseGalaxy(Scanner scanner) throws RuntimeException{
		String input;
		System.out.println("Als Erstes brauchen wir ein Universum!");
		System.out.print("Bitte gebe dem Universum einen Namen: ");
		input = Game.INPUTORACLE.inputEmptyCheck(scanner);
		System.out.println();
		Universe universe = new Universe(input);
		this.universe = universe;
		universe.setPlanetdata(Game.FILEORACLE.readFile(Game.FILEORACLE.getPlanetCreatePath() + ".txt"));
		universe.setSundata(Game.FILEORACLE.readFile(Game.FILEORACLE.getSunCreatePath()+ ".txt"));
		universe.setMoondata(Game.FILEORACLE.readFile(Game.FILEORACLE.getMoonCreatePath() + ".txt"));
		System.out.println("Als Zweites brauchen wir eine Galaxie!");
		System.out.print("Bitte gebe deiner Galaxie einen Namen: ");
		input = Game.INPUTORACLE.inputEmptyCheck(scanner);
		System.out.println();
		Galaxy galaxy = universe.addGalaxy(input);
		galaxy.setUniverse(universe);
		return galaxy;
	}
	
	//Erstellung der Sonnensysteme 
	public void setSolar(Scanner scanner, Galaxy galaxy) throws RuntimeException {
		String input;
		int numberInput;
		boolean numberValue;
		System.out.println(
				"Als Nächstes brauchen wir Sonnensysteme! (Planten, Sonnen, Monde etc. werden zufällig erstellt)");
		while (true) {
			System.out.print("Wie viele Sonnensysteme möchtest du deiner Galaxie hinzufügen ? (MIN 1 - MAX 3): ");
			input = Game.INPUTORACLE.inputEmptyCheck(scanner);
			numberValue = Game.INPUTORACLE.inputIsNumberAndRangeCheck(1, 3, input);
			if(numberValue) {
				numberInput = Integer.valueOf(input);
				break;
			}
			
		}
		
		System.out.println();
		for (int i = 0; i < numberInput; i++) {
			if (numberInput > 1) {
				System.out.print("Bitte gebe den Namen für das " + (i + 1) + ".Sonnensystem ein: ");
			} else {
				System.out.print("Bitte gebe den Namen für das Sonnensystem ein: ");
			}
			input = Game.INPUTORACLE.inputEmptyCheck(scanner);
			SolarSystem solarsystem = galaxy.addSolarSystem(input);
			createRandomSolarSystem(solarsystem);
		}
		Game.INPUTORACLE.printBreakLineAfter();

	}
	
	//Erstellung des Spielers
	public Player setPlayer (Scanner scanner) throws RuntimeException {
		String input;
		Game.INPUTORACLE.printBreakLineBefore();
		System.out.println("Als nächstes erstellen wir dich!, Du brauchst einen Namen!");
		System.out.print("Wie heißt du?: ");
		input = Game.INPUTORACLE.inputEmptyCheck(scanner);
		Player player = new Player(input);
		Game.INPUTORACLE.printBreakLineAfter();
		return player;
		
	}
	
	//Erstellung der Planeten, Sonnen und Monde innerhalb der Sonnensysteme
	//Es kann nicht mehr (Sonnen + 2) als Planeten geben
	//Es muss mindestens 3 und mindestens eine Sonne geben
	private void createRandomSolarSystem(SolarSystem solarsystem) throws RuntimeException {
		int randomPlanetNumber = 0;
		int randomSunNumber = 0;
		int randomMoonNumber = 0;
		do {
			randomPlanetNumber = (int) (Math.random() * (10));
			randomSunNumber = (int) (Math.random() * (10)) - ((int) randomPlanetNumber / 2);
		} while ((randomSunNumber + 2) > randomPlanetNumber || randomPlanetNumber < 3 || randomSunNumber < 1);

		createRandomPlanets(solarsystem, randomPlanetNumber);
		createRandomSuns(solarsystem, randomSunNumber);
		createRandomMoons(solarsystem, randomMoonNumber);
		resourcesToPlanets(solarsystem);
		
	}

	//Erstelung zufälliger Planeten
	//Sonnensysteme müssen immer mindestens einen Planeten besitzen mit einer normalen Atmosphäre
	//Spieler wird dann später einem Planeten mit normaler Atmosphäre zugeordnet
	//Falls kein Planet mit normaler Atmosphäre in der ausgelesenen Datei vorhanden ist --> Fehler
	//Erstellte Planeten werden dem jeweiligen Sonnensystem zugeordnet
	private void createRandomPlanets(SolarSystem solarsystem, int randomPlanetNumber) throws RuntimeException {
		boolean normalAtmosphere = false;
		for (int i = 0; i < randomPlanetNumber; i++) {
			String[] randomData;
			int randomIndex;
			int errorCount = 0;
			String atmosphereType;
			while (true) {
				randomIndex = (int) (Math.random() * universe.getPlanetdata().size());
				randomData = universe.getPlanetdata().get(randomIndex).split(" ");
				atmosphereType = universe.getAtmospheres().get((Integer.valueOf(randomData[4]))).getType();
				if (atmosphereType.equals("Normal")) {
					normalAtmosphere = true;
				}

				if (randomData.length > 1 && (i != randomPlanetNumber - 1)) {
					break;
				} else if ((randomData.length > 1) && (i == randomPlanetNumber - 1) && (normalAtmosphere)) {
					break;
				} else {
					errorCount++;
				}

				if (errorCount >= 100) {
					throw new RuntimeException("Planeten konnten nicht ordungsgemäß erstellt werden!");
				}
			}
			universe.getPlanetdata().remove(randomIndex);
			solarsystem.addPlanet(randomData[0], Double.valueOf(randomData[1]), Double.valueOf(randomData[2]),
					Double.valueOf(randomData[1]), universe.getAtmospheres().get((Integer.valueOf(randomData[4]))),
					universe.getPlanetTerrains().get((Integer.valueOf(randomData[5]))));

		}
		
		System.out.println();

	}
	
	
	//Erstelle zufällige Sonnen
	//Jedes Sonnensystem hat genau einen Hauptsequenzstern
	//Nur um diesen Stern kreisen die Planeten
	//Die anderen Sonnen sind einfach nur da im System, aber haben keine Planten, da sie nicht die Vorrausetzungen liefern
	//um Leben auf Planeten zu ermöglichen -> Nur Hauptsequenzsterne haben diese Vorraussetzung
	//Falls kein Hauptsequenzstern erstelt werden konnte -> Fehler
	private void createRandomSuns(SolarSystem solarsystem, int randomSunNumber) throws RuntimeException{
		boolean mainStar = false;
		for (int i = 0; i < randomSunNumber; i++) {
			String[] randomData;
			int randomIndex;
			int errorCount = 0;
			String starType;
			while (true) {
				if (!mainStar) {
					randomIndex = (int) (Math.random() * universe.getSundata().size());
					randomData = universe.getSundata().get(randomIndex).split(" ");
					starType = universe.getSuns().get((Integer.valueOf(randomData[5]))).getType();

					if (starType.equals("Hauptsequenzstern")) {
						mainStar = true;
					}
				} else {
					do {
						randomIndex = (int) (Math.random() * universe.getSundata().size());
						randomData = universe.getSundata().get(randomIndex).split(" ");
						starType = universe.getSuns().get((Integer.valueOf(randomData[5]))).getType();
					} while (starType.equals("Hauptsequenzstern"));

				}
				if (randomData.length > 1 && (i != randomSunNumber - 1)) {
					break;
				} else if ((randomData.length > 1) && (i == randomSunNumber - 1) && (mainStar)) {
					break;
				} else {
					errorCount++;
				}
				if (errorCount >= 100) {
					throw new RuntimeException("Sonnen konnten nicht ordungsgemäß erstellt werden!");
				}
			}
			universe.getSundata().remove(randomIndex);
			solarsystem.addSun(randomData[0], Double.valueOf(randomData[1]), Double.valueOf(randomData[2]),
					Double.valueOf(randomData[3]), Double.valueOf(randomData[4]),
					universe.getSuns().get((Integer.valueOf(randomData[5]))));
			planetsToMainStar(solarsystem);
		}
	}

	//Planeten werden dem Hauptsequenzstern zugeordnet und umgekehrt
	private void planetsToMainStar(SolarSystem solarsystem) throws RuntimeException{
		for (int m = 0; m < solarsystem.getSuns().size(); m++) {
			if (solarsystem.getSuns().get(m) instanceof MainSequenceStar) {
				MainSequenceStar mainstar = (MainSequenceStar) solarsystem.getSuns().get(m);
				if (mainstar.getPlanetNames().size() > 0) {
					break;
				}
				solarsystem.setMainstar(mainstar);
				for (int n = 0; n < solarsystem.getPlanets().size(); n++) {
					mainstar.addPlanet(solarsystem.getPlanets().get(n));
					solarsystem.getPlanets().get(n).setMainstar(mainstar);
				}
				break;
			}
		}
	}

	//Erstellung von zufälligen Monden für die Planeten
	//Ein Planet muss nicht zwangsläufig Monde besitzen
	private void createRandomMoons(SolarSystem solarsystem, int randomMoonNumber) throws RuntimeException {
		MainSequenceStar mainstar = solarsystem.getMainstar();

		for (int i = 0; i < mainstar.getPlanets().size(); i++) {
			randomMoonNumber = (int) (Math.random() * (5));

			for (int j = 0; j < randomMoonNumber; j++) {
				String[] randomData;
				int randomIndex;
				int errorCount = 0;
				while (true) {
					randomIndex = (int) (Math.random() * universe.getMoondata().size());
					randomData = universe.getMoondata().get(randomIndex).split(" ");

					if (randomData.length > 1) {
						break;
					} else {
						errorCount++;
					}

					if (errorCount >= 100) {
						throw new RuntimeException("Monde konnten nicht ordungsgemäß erstellt werden!");
					}

				}
				if (randomMoonNumber != 0) {
					universe.getMoondata().remove(randomIndex);
					mainstar.getPlanets().get(i).addMoon(randomData[0], Double.valueOf(randomData[1]),
							Double.valueOf(randomData[2]), Double.valueOf(randomData[3]),
							universe.getAtmospheres().get((Integer.valueOf(randomData[4]))),
							universe.getMoonTerrains().get((Integer.valueOf(randomData[5]))));

					moonsToPlanet(mainstar.getPlanets().get(i));
				}
			}
		}
	}
	
	//Zufällige (mit Einschrönkungen zufällige) Ressourcen werden den Planeten zugeordent
	//Kategorien bestimmen welche Ressourcen überhaupt möcglich sind bzw. welche überhaupt vorkommen könnten
	//Kategorien selbst haben aber nochmal Zufallsmechanismen
	private void resourcesToPlanets(SolarSystem solarsystem) throws RuntimeException{
		
		int randomResourceNumber = (int) (Math.random() * (3));

		int count=0;
		for(Planet planet: solarsystem.getPlanets()) {
			
			switch (planet.getPlanetTerrain().getType()) {
            case "Canyon":
            	Game.RESOURCEORACLE.addResourcesCategoryA(solarsystem, count);
                break;
            case "Krater":
            	Game.RESOURCEORACLE.addResourcesCategoryA(solarsystem, count);
                break;
            case "Wüste":
            	Game.RESOURCEORACLE.addResourcesCategoryA(solarsystem, count);
            	break;
            case "Grassland":
            	Game.RESOURCEORACLE.addResourcesCategoryB(solarsystem, count);
                break;
            case "Dschungel":
            	Game.RESOURCEORACLE.addResourcesCategoryC(solarsystem, count);
                break;
            case "Berge":
            	Game.RESOURCEORACLE.addResourcesCategoryD(solarsystem, count);
                break;
            case "Normaler Wald":
            	if(randomResourceNumber == 2) {
            		Game.RESOURCEORACLE.addResourcesCategoryD(solarsystem, count);
            	}else {
            		Game.RESOURCEORACLE.addResourcesCategoryB(solarsystem, count);
            	}
            	
                break;
            case "Savanne":
            	Game.RESOURCEORACLE.addResourcesCategoryC(solarsystem, count);
                break;
            case "Sumpf":
            	Game.RESOURCEORACLE.addResourcesCategoryB(solarsystem, count);
                break;
            case "Tundra":
            	Game.RESOURCEORACLE.addResourcesCategoryD(solarsystem, count);
                break;
            case "Vulkangebiet":
            	Game.RESOURCEORACLE.addResourcesCategoryA(solarsystem, count);
                break;
            default:
            	throw new RuntimeException("Planeten haben einen ungültigen Terrain-Typ!");
        }
			resourcesToMoons(planet);
			count++;
		}
		
		
	}
	
	//Zufällige (mit Einschrönkungen zufällige) Ressourcen werden den Monden zugeordnet
	//Kategorien bestimmen welche Ressourcen überhaupt möcglich sind bzw. welche überhaupt vorkommen könnten
	//Kategorien selbst haben aber nochmal Zufallsmechanismen
    private void resourcesToMoons(Planet planet) throws RuntimeException{
		
		//int randomResourceNumber = (int) (Math.random() * (3));
		int count=0;
		for(Moon moon : planet.getMoons()) {
			
			switch (moon.getMoonTerrain().getType()) {
            case "Mondkrater":
            	Game.RESOURCEORACLE.addResourcesCategoryE(planet, count);
                break;
            case "Eisige Mondebenen":
            	Game.RESOURCEORACLE.addResourcesCategoryF(planet, count);
                break;
            case "Vulkanische Mondebenen":
            	Game.RESOURCEORACLE.addResourcesCategoryG(planet, count);
            	break;
            default:
            	throw new RuntimeException("Monde haben einen ungültigen Terrain-Typ!");
			}
			count++;
		}
		
	}
    
    
    //Monde werden den Planten zugeordnet und umgekehrt
	private void moonsToPlanet(Planet planet)throws RuntimeException {
		for (int m = 0; m < planet.getMoons().size(); m++) {
			if (planet.getMoons().get(m).getPlanet() == null) {
				planet.getMoons().get(m).setPlanet(planet);
			}
		}
	}
	

}
