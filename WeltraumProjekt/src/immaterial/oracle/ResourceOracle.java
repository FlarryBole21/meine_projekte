package immaterial.oracle;

import java.util.LinkedList;
import java.util.Scanner;

import immaterial.Game;
import space.SolarSystem;
import space.celestial.Planet;
import space.inventory.resource.Resource;
import space.inventory.resource.miningresource.Chronium;
import space.inventory.resource.miningresource.Copper;
import space.inventory.resource.miningresource.Crystal;
import space.inventory.resource.miningresource.Gold;
import space.inventory.resource.miningresource.Iron;
import space.inventory.resource.miningresource.Nanite;
import space.inventory.resource.miningresource.Neutronium;
import space.inventory.resource.miningresource.Plasma;
import space.inventory.resource.miningresource.QuantumVacuum;
import space.inventory.resource.miningresource.Silver;
import space.inventory.resource.miningresource.Stellarite;
import space.inventory.resource.miningresource.Titan;
import space.inventory.resource.rawfood.Apple;
import space.inventory.resource.rawfood.Banana;
import space.inventory.resource.rawfood.BlueBerry;
import space.inventory.resource.rawfood.Dattel;
import space.inventory.resource.rawfood.Mango;
import space.inventory.resource.rawfood.StrawBerry;
import space.inventory.resource.wood.MapleWood;
import space.inventory.resource.wood.NormalWood;
import space.inventory.resource.wood.OakWood;
import space.inventory.resource.wood.TeakWood;
import space.inventory.resource.wood.WillowWood;

public class ResourceOracle extends Oracle {


	private static final long serialVersionUID = 1L;
	private static final int LEVEL1 = 1;
	private static final int LEVEL2 = 5;
	private static final int LEVEL3 = 10;
	private static final int LEVEL4 = 15;
	private static final int LEVEL5 = 20;
	private static final int LEVEL6 = 25;
	private static final int LEVEL7 = 30;
	private static final int LEVEL8 = 35;
	private LinkedList<Resource> resources;
	
	
	public ResourceOracle() {
		super.setType("Orakel der Ressourcen");
		this.resources=new LinkedList<>();
		this.resources.add(getApple());
		this.resources.add(getBanana());
		this.resources.add(getBlueBerry());
		this.resources.add(getChronium());
		this.resources.add(getCopper());
		this.resources.add(getCrystal());
		this.resources.add(getDattel());
		this.resources.add(getGold());
		this.resources.add(getIron());
		this.resources.add(getMango());
		this.resources.add(getMapleWood());
		this.resources.add(getNanite());
		this.resources.add(getNeutronium());
		this.resources.add(getNormalWood());
		this.resources.add(getOakWood());
		this.resources.add(getPlasma());
		this.resources.add(getQuantumVaccum());
		this.resources.add(getSilver());
		this.resources.add(getStellarite());
		this.resources.add(getStrawBerry());
		this.resources.add(getTeakWood());
		this.resources.add(getTitan());
		this.resources.add(getWaterMelon());
		this.resources.add(getWillowWood());
	}

	private Crystal getCrystal() {
		return new Crystal(LEVEL1,500);
	}

	private NormalWood getNormalWood() {
		return new NormalWood(LEVEL1,200);
	}

	private Copper getCopper() {
		return new Copper(LEVEL1,200);
	}

	private Iron getIron() {
		return new Iron(LEVEL2,100);
	}

	private Apple getApple() {
		return new Apple(LEVEL2,200);
	}

	private Banana getBanana() {
		return new Banana(LEVEL4,200);
	}

	private BlueBerry getBlueBerry() {
		return new BlueBerry(LEVEL1,200);
	}

	private Dattel getDattel() {
		return new Dattel(LEVEL2,200);
	}
	
	private Silver getSilver() {
		return new Silver(LEVEL4,100);
	}
	
	private Gold getGold() {
		return new Gold(LEVEL6,50);
	}

	private Mango getMango() {
		return new Mango(LEVEL6,200);
	}

	private MapleWood getMapleWood() {
		return new MapleWood(LEVEL5,200);
	}

	private Nanite getNanite() {
		return new Nanite(LEVEL6,30);
	}

	private OakWood getOakWood() {
		return new OakWood(LEVEL2,200);
	}

	private Plasma getPlasma() {
		return new Plasma(LEVEL7,10);
	}

	private StrawBerry getStrawBerry() {
		return new StrawBerry(LEVEL1,200);
	}

	private TeakWood getTeakWood() {
		return new TeakWood(LEVEL4,200);
	}

	private Titan getTitan() {
		return new Titan(LEVEL5,50);
	}

	private WillowWood getWillowWood() {
		return new WillowWood(LEVEL3,200);
	}
	
	private Chronium getChronium() {
		return new Chronium(LEVEL6,30);
	}
	
	private Stellarite getStellarite() {
		return new Stellarite(LEVEL6,10);
	}
	
	private QuantumVacuum getQuantumVaccum() {
		return new QuantumVacuum(LEVEL8,5);
	}
	
	private Neutronium getNeutronium() {
		return new Neutronium(LEVEL8,5);
	}
	
	private Neutronium getWaterMelon() {
		return new Neutronium(LEVEL7,100);
	}

	//Basis-Ressourcen -> Jeder planet hat diese
	private void basicResources(SolarSystem solarsystem, int planetNumber) {
		solarsystem.getPlanets().get(planetNumber).addResource(getCrystal());
		solarsystem.getPlanets().get(planetNumber).addResource(getNormalWood());
		solarsystem.getPlanets().get(planetNumber).addResource(getCopper());
		solarsystem.getPlanets().get(planetNumber).addResource(getIron());
	}
	
	//Basis-Ressourcen für den Mond
	private void basicMoonResources(Planet planet, int moonNumber) {
		planet.getMoons().get(moonNumber).addResource(getCrystal());
		planet.getMoons().get(moonNumber).addResource(getIron());
	}

	//Ressourcen-Pools für bestimmte Planten, die bestimmte Terrains besitzen
	//Innerhalb wird aber auch nochmal mit Zufallsfaktoren reguliert
	public void addResourcesCategoryA(SolarSystem solarsystem, int planetNumber) {
		int randomResourceNumber = (int) (Math.random() * (4));
		int randomResourceNumber2 = (int) (Math.random() * (2));
		int randomResourceNumber3 = (int) (Math.random() * (2));
		
		solarsystem.getPlanets().get(planetNumber).addResource(getDattel());
		solarsystem.getPlanets().get(planetNumber).addResource(getBlueBerry());
		solarsystem.getPlanets().get(planetNumber).addResource(getWillowWood());
		solarsystem.getPlanets().get(planetNumber).addResource(getSilver());

		if(randomResourceNumber >= 1) {
			solarsystem.getPlanets().get(planetNumber).addResource(getTitan());
			
			if(randomResourceNumber3 == 1) {
				solarsystem.getPlanets().get(planetNumber).addResource(getGold());
			}
		}
	
		if(randomResourceNumber >= 2) {
			if(randomResourceNumber2 == 1) {
				solarsystem.getPlanets().get(planetNumber).addResource(getNanite());
			}else {
				solarsystem.getPlanets().get(planetNumber).addResource(getChronium());
			}
		}
		
		if(randomResourceNumber == 3) {
			solarsystem.getPlanets().get(planetNumber).addResource(getPlasma());
		}
	
		basicResources(solarsystem, planetNumber);
	}

	public void addResourcesCategoryB(SolarSystem solarsystem, int planetNumber) {
		int randomResourceNumber = (int) (Math.random() * (3));
		
		solarsystem.getPlanets().get(planetNumber).addResource(getStrawBerry());
		solarsystem.getPlanets().get(planetNumber).addResource(getOakWood());
		solarsystem.getPlanets().get(planetNumber).addResource(getApple());
		if(randomResourceNumber >= 1) {
			solarsystem.getPlanets().get(planetNumber).addResource(getWillowWood());
			solarsystem.getPlanets().get(planetNumber).addResource(getSilver());
			solarsystem.getPlanets().get(planetNumber).addResource(getTitan());
		}
		if(randomResourceNumber >= 2) {
			solarsystem.getPlanets().get(planetNumber).addResource(getChronium());
		}
		
		basicResources(solarsystem, planetNumber);
	}

	public void addResourcesCategoryC(SolarSystem solarsystem, int planetNumber) {
		int randomResourceNumber = (int) (Math.random() * (3));
		int randomResourceNumber2 = (int) (Math.random() * (2));
		
		solarsystem.getPlanets().get(planetNumber).addResource(getBanana());
		solarsystem.getPlanets().get(planetNumber).addResource(getTeakWood());
		if(randomResourceNumber >= 1) {
			solarsystem.getPlanets().get(planetNumber).addResource(getGold());
			solarsystem.getPlanets().get(planetNumber).addResource(getMango());
		}
		if(randomResourceNumber >= 2) {
			if(randomResourceNumber2 == 1) {
				solarsystem.getPlanets().get(planetNumber).addResource(getPlasma());
			}
			solarsystem.getPlanets().get(planetNumber).addResource(getWaterMelon());
			solarsystem.getPlanets().get(planetNumber).addResource(getNanite());
		}
		

		basicResources(solarsystem, planetNumber);
	}

	public void addResourcesCategoryD(SolarSystem solarsystem, int planetNumber) {
		int randomResourceNumber = 0;
		randomResourceNumber = (int) (Math.random() * (3));
		solarsystem.getPlanets().get(planetNumber).addResource(getBlueBerry());
		solarsystem.getPlanets().get(planetNumber).addResource(getApple());
		solarsystem.getPlanets().get(planetNumber).addResource(getMapleWood());
		if(randomResourceNumber >= 1) {
			solarsystem.getPlanets().get(planetNumber).addResource(getTitan());
			solarsystem.getPlanets().get(planetNumber).addResource(getSilver());
		}
		if(randomResourceNumber >= 2) {
			solarsystem.getPlanets().get(planetNumber).addResource(getStellarite());
			solarsystem.getPlanets().get(planetNumber).addResource(getChronium());
		}

		basicResources(solarsystem, planetNumber);
	}
	
	public void addResourcesCategoryE(Planet planet, int moonNumber) {
		int randomResourceNumber = 0;
		randomResourceNumber = (int) (Math.random() * (4));
		if(randomResourceNumber >= 1) {
			planet.getMoons().get(moonNumber).addResource(getTitan());
		}
		if(randomResourceNumber >= 2) {
			planet.getMoons().get(moonNumber).addResource(getStellarite());
		}
		if(randomResourceNumber >= 3) {
			planet.getMoons().get(moonNumber).addResource(getQuantumVaccum());
		}


		basicMoonResources(planet, moonNumber);
	}
	
	
	public void addResourcesCategoryF(Planet planet, int moonNumber) {
		int randomResourceNumber = 0;
		randomResourceNumber = (int) (Math.random() * (4));
		planet.getMoons().get(moonNumber).addResource(getSilver());
		if(randomResourceNumber >= 1) {
			planet.getMoons().get(moonNumber).addResource(getChronium());
		}
		if(randomResourceNumber >= 2) {
			planet.getMoons().get(moonNumber).addResource(getStellarite());
		}
		if(randomResourceNumber >= 3) {
			planet.getMoons().get(moonNumber).addResource(getNeutronium());
		}


		basicMoonResources(planet, moonNumber);
	}
	
	
	public void addResourcesCategoryG(Planet planet, int moonNumber) {
		int randomResourceNumber = 0;
		randomResourceNumber = (int) (Math.random() * (4));
		planet.getMoons().get(moonNumber).addResource(getGold());
		planet.getMoons().get(moonNumber).addResource(getSilver());
		if(randomResourceNumber >= 1) {
			planet.getMoons().get(moonNumber).addResource(getTitan());
			planet.getMoons().get(moonNumber).addResource(getPlasma());
			
		}
		if(randomResourceNumber >= 3) {
			planet.getMoons().get(moonNumber).addResource(getQuantumVaccum());
			planet.getMoons().get(moonNumber).addResource(getStellarite());
			planet.getMoons().get(moonNumber).addResource(getNeutronium());
		}


		basicMoonResources(planet, moonNumber);
	}
	
	//Gebe eine Ressource zurück -> Spieler fragt nach Ressource beim Abbau der Ressource
	public Resource getResource(Scanner scanner, LinkedList<Resource> resources) {
		int numberInput;
		Resource resource=null;
		String input = Game.INPUTORACLE.inputEmptyCheck(scanner);
		while(true) {
			try {
				numberInput = Integer.valueOf(input);
				if (numberInput > resources.size()) {
					System.err.println("Error -> Zu hohe oder niedrige Eingabe");
				}
			}catch(RuntimeException e) {
				System.err.println("Error -> Ungültige Eingabe!");
			}
			
			if(input.equals("1") && resources.size() >= 1) {
				resource =resources.get(0);
				break;
			}else if (input.equals("2") && resources.size() >= 2) {
				resource =resources.get(1);
				break;
				
			}else if (input.equals("3") && resources.size() >= 2) {
				resource =resources.get(2);
				break;
				
			}else if (input.equals("4") && resources.size() >= 2) {
				resource =resources.get(3);
				break;
				
			}else if (input.equals("5") && resources.size() >= 2) {
				resource =resources.get(4);
				break;
				
			}else if (input.equals("6") && resources.size() >= 2) {
				resource =resources.get(5);
				break;
				
			}else if (input.equals("7") && resources.size() >= 2) {
				resource =resources.get(6);
				break;
				
			}else if (input.equals("8") && resources.size() >= 2) {
				resource =resources.get(7);
				break;
				
			}else if (input.equals("9") && resources.size() >= 2) {
				resource =resources.get(8);
				break;
				
			}else if (input.equals("10") && resources.size() >= 2) {
				resource =resources.get(9);
				break;
				
			}else if (input.equals("11") && resources.size() >= 2) {
				resource =resources.get(10);
				break;
				
			}else if (input.equals("12") && resources.size() >= 2) {
				resource =resources.get(11);
				break;
				
			}else if (input.equals("13") && resources.size() >= 2) {
				resource =resources.get(12);
				break;
				
			}else if (input.equals("14") && resources.size() >= 2) {
				resource =resources.get(13);
				break;
				
			}else if (input.equals("15") && resources.size() >= 2) {
				resource =resources.get(14);
				break;
				
			}else if (input.equals("16") && resources.size() >= 2) {
				resource =resources.get(15);
				break;
				
			}else if (input.equals("17") && resources.size() >= 2) {
				resource =resources.get(16);
				break;
				
			}else if (input.equals("18") && resources.size() >= 2) {
				resource =resources.get(17);
				break;
				
			}else if (input.equals("19") && resources.size() >= 2) {
				resource =resources.get(18);
				break;
				
			}else if (input.equals("20") && resources.size() >= 2) {
				resource =resources.get(19);
				break;
				
			}else if (input.equals("21") && resources.size() >= 2) {
				resource =resources.get(20);
				break;
				
			}else if (input.equals("22") && resources.size() >= 2) {
				resource =resources.get(21);
				break;
				
			}else if (input.equals("23") && resources.size() >= 2) {
				resource =resources.get(22);
				break;
				
			}else if (input.equals("24") && resources.size() >= 2) {
				resource =resources.get(23);
				break;
				
			}
		}
		return resource;
	}
		
}

