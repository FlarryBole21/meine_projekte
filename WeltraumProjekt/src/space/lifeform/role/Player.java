package space.lifeform.role;

import java.util.LinkedList;
import immaterial.Game;
import space.SolarSystem;
import space.Upgradable;
import space.buildable.ship.BattleShip;
import space.buildable.ship.Ship;
import space.celestial.Moon;
import space.celestial.Planet;
import space.celestial.EnterableCelestial;
import space.inventory.InventoryObject;
import space.inventory.item.suit.SpacewalkerSuit;
import space.inventory.item.suit.Suit;
import space.inventory.resource.Resource;
import space.lifeform.Human;
import space.lifeform.Killable;

public class Player extends Human implements Upgradable, Killable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private double strengthExperience;
	private double defenseExperience;
	private double healthExperience;
	private double miningExperience;
	private double harvestExperience;
	private double woodCuttingExperience;
	private double cookingExperience;
	private double craftingExperience;
	private double buildingExperience;
	private int overAllBattle;
	private int overAllLevel;
	private SolarSystem currentSystem;
	private EnterableCelestial currentPlace;
	private Ship currentShip;
	private Ship shipParked;
	private Suit currentSuit;
	private LinkedList<InventoryObject> inventory;
	private int inventorySize;
	private LinkedList<Planet> visitedPlanets;
	private LinkedList<Moon> visitedMoons;
	private LinkedList<Planet> colonizedPlanets;

	public Player(String name) {
		super(5, 5, 10);
		super.setType("Spieler");
		this.name = name;
		this.inventory = new LinkedList<>();
		this.inventorySize = 10;
		this.strengthExperience=super.getStrength();
		this.defenseExperience=super.getDefense();
		this.healthExperience=super.getHealth();
		this.miningExperience=1;
		this.harvestExperience=1;
		this.woodCuttingExperience=1;
		this.cookingExperience=1;
		this.craftingExperience=1;
		this.buildingExperience=1;
		this.currentShip = new BattleShip();
		this.currentSuit = new SpacewalkerSuit();
		this.currentSuit.wear(this);
		this.visitedPlanets = new LinkedList<>();
		this.visitedMoons = new LinkedList<>();
		this.colonizedPlanets = new LinkedList<>();
		setOverallLevel();
		setOverallBattle();
	}
	
	public boolean getShipBool() {
		if(currentShip instanceof Ship) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public LinkedList<String> getInventoryNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getInventory().size(); i++) {
			names.add(getInventory().get(i).getType() + " <" + getInventory().get(i).getAmount()+">");
		}
		return names;
	}
	
	public LinkedList<String> getVisitedPlanetNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getVisitedPlanets().size(); i++) {
			names.add(getVisitedPlanets().get(i).getName());
		}
		return names;
	}
	
	
	public LinkedList<String> getVisitedMoonNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getVisitedMoons().size(); i++) {
			names.add(getVisitedMoons().get(i).getName());
		}
		return names;
	}
	
	
	public LinkedList<String> getColonizedPlanetNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getColonizedPlanets().size(); i++) {
			names.add(getColonizedPlanets().get(i).getName());
		}
		return names;
	}
	
	
	public void leaveShip() {
		setShipParked(currentShip);
		setCurrentShip(null);
	}
	
	public void enterShip() {
		setCurrentShip(shipParked);
		setShipParked(null);
	}
	
	

	public int getInventorySize() {
		return inventorySize;
	}

	public void setInventorySize(int inventorySize) {
		this.inventorySize = inventorySize;
	}

	public Ship getCurrentShip() {
		return currentShip;
	}

	private void setCurrentShip(Ship currentShip) {
		this.currentShip = currentShip;
	}
	
	
	public Ship getShipParked() {
		return shipParked;
	}

	private void setShipParked(Ship shipParked) {
		this.shipParked = shipParked;
	}
	
	public Suit getCurrentSuit() {
		return currentSuit;
	}

	public void setCurrentSuit(Suit currentSuit) {
		this.currentSuit = currentSuit;
	}
	
	public void addResourceToInventory(Resource resource, int amount) {
		
		if(inventory.size() ==0) {
			resource.setAmount(amount);
			inventory.add(resource);
		}else {
			for(int i=0; i<inventory.size();i++) {
				if(!(inventory.get(i).getType().equals(resource.getType()))) {
					resource.setAmount(amount);
					inventory.add(resource);
				}else {
					inventory.get(i).addAmount(amount);
				}
			}
			
		}

	}
	
	
	public void removeObjectFromInventory(InventoryObject inventoryObject, int amount) {
		if(inventory.size() <0) {
			System.err.println("Keine Items im Inventar vorhanden!");
			return;
		}
		
		for(int i=0; i<inventory.size();i++) {
			if(!(inventory.get(i).getType().equals(inventoryObject.getType()))) {
				System.err.println("Objekt nicht im Inventar vorhanden");
			}
			else {
				if(inventory.get(i).getAmount() <= 1) {
					inventory.remove(inventoryObject);
					
				}else {
					inventory.get(i).removeAmount(amount);
				}
			}
		}
		
	}
	
	
	public SolarSystem getCurrentSystem() {
		return currentSystem;
	}

	public void setCurrentSystem(SolarSystem currentSystem) {
		this.currentSystem = currentSystem;
	}
	
	public EnterableCelestial getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(EnterableCelestial currentPlace) {
		this.currentPlace = currentPlace;
	}
	
	public LinkedList<InventoryObject> getInventory() {
		return inventory;
	}

	public LinkedList<Planet> getVisitedPlanets() {
		return visitedPlanets;
	}

	public void addVisited(EnterableCelestial visited) {
		if(visited instanceof Planet && (! visitedPlanets.contains(visited))) {
			visitedPlanets.add((Planet) visited);
		}else if(visited instanceof Moon && (!visitedMoons.contains(visited))) {
			visitedMoons.add((Moon) visited);
		}
	}

	public LinkedList<Moon> getVisitedMoons() {
		return visitedMoons;
	}

	
	public LinkedList<Planet> getColonizedPlanets() {
		return colonizedPlanets;
	}



	public void setInventory(LinkedList<InventoryObject> inventory) {
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getOverallBattle() {
		return overAllBattle;
	}
	
	public double getOverallLevel() {
		return overAllLevel;
	}
	
	public void setOverallBattle() {
		this.overAllBattle = super.getStrength()+super.getDefense()+super.getHealth();
	}

	public void setOverallLevel() {
		this.overAllLevel = (int)strengthExperience+(int)defenseExperience+(int)healthExperience+(int)miningExperience+
				(int)harvestExperience+(int)woodCuttingExperience+(int)cookingExperience
				+(int)craftingExperience+(int)buildingExperience;
	}
	
	
	public double getMiningExperience() {
		return miningExperience;
	}

	public void setMiningExperience(double miningExperience) {
		this.miningExperience += miningExperience;
	}
	
	public void setStrengthExperience(double strengthExperience) {
		this.strengthExperience += strengthExperience;
		super.setStrength((int)this.strengthExperience);
	}
	
	public void setDefenseExperience(double defenseExperience) {
		this.defenseExperience += defenseExperience;
		super.setDefense((int)this.defenseExperience);
	}
	
	public void setHealthExperience(double healthExperience) {
		this.healthExperience += healthExperience;
		super.setHealth((int)this.healthExperience);
	}

	public double getHarvestExperience() {
		return harvestExperience;
	}

	public void setHarvestExperience(double harvestExperience) {
		this.harvestExperience += harvestExperience;
	}

	public double getWoodCuttingExperience() {
		return woodCuttingExperience;
	}

	public void setWoodCuttingExperience(double woodCuttingExperience) {
		this.woodCuttingExperience += woodCuttingExperience;
	}

	public double getCookingExperience() {
		return cookingExperience;
	}

	public void setCookingExperience(double cookingExperience) {
		this.cookingExperience += cookingExperience;
	}

	public double getCraftingExperience() {
		return craftingExperience;
	}

	public void setCraftingExperience(double craftingExperience) {
		this.craftingExperience += craftingExperience;
	}

	public double getBuildingExperience() {
		return buildingExperience;
	}

	public void setBuildingExperience(double buildingExperience) {
		this.buildingExperience += buildingExperience;
	}

	public LinkedList<String> getInformation() {
		LinkedList<String> information = new LinkedList<>();
		information.add("Name <" + name+">");
		information.add("Typ <" + super.getType()+">");
		information.add("Kampf-Level <" + (overAllBattle) + ">");
		information.add("Gesamtstärke <" + super.getStrength() + ">");
		information.add("Gesamtverteidigung <" + super.getDefense() + ">");
		information.add("Gesamtlebenspunkte <" + super.getHealth() + ">");
		information.add("Gesamt-Level <" + ((int)overAllLevel) + ">");
		information.add("Stärke-Level <" + ((int)strengthExperience) + "> "
		        + "<" +  Game.MATHORACLE.calcPercent(strengthExperience) + "%>");
		information.add("Verteidigung-Level <" + ((int)defenseExperience) + "> "
		        + "<" +  Game.MATHORACLE.calcPercent(defenseExperience) + "%>");
		information.add("Lebenspunkte-Level <" + ((int)healthExperience) + "> "
		        + "<" +  Game.MATHORACLE.calcPercent(healthExperience) + "%>");
		information.add("Bergbau-Level <" + ((int)miningExperience) + "> "
		        + "<" +  Game.MATHORACLE.calcPercent(miningExperience) + "%>");
		information.add("Ernte-Level <" + ((int)harvestExperience) + "> "
		        + "<" +  Game.MATHORACLE.calcPercent(harvestExperience) + "%>");
		information.add("Holzfäller-Level <" + ((int)woodCuttingExperience) + "> "
		        + "<" + Game.MATHORACLE.calcPercent(woodCuttingExperience) + "%>");
		information.add("Koch-Level <" + ((int)cookingExperience) + "> "
		        + "<" + Game.MATHORACLE.calcPercent(cookingExperience) + "%>");
		information.add("Crafting-Level <" + ((int)craftingExperience) + "> "
		        + "<" + Game.MATHORACLE.calcPercent(craftingExperience) + "%>");
		information.add("Bau-Level <" + ((int)buildingExperience) + "> "
		        + "<" + Game.MATHORACLE.calcPercent(buildingExperience) + "%>");

		information.add("Im Schiff <" + getShipBool()+">");
		if(getCurrentPlace() != null) {
			information.add("Aktueller Ort <" + getCurrentPlace().getType()  + " <" +
						getCurrentPlace().getName() + ">>");
		}else {
			information.add("Aktueller Ort <Du befindest dich an keinem Ort!>");
		}
		if(getCurrentSuit() != null) {
			information.add("Anzug <" + getCurrentSuit().getType() 
					+"> Verteidigungswert <" + getCurrentSuit().getDefense()+">");
		}else {
			information.add("Anzug <Kein Anzug an!>");
		}
		if(getInventoryNames().size() != 0) {
			information.add("Inventar <" + getInventoryNames() +">");
		}else {
			information.add("Inventar <Inventar ist leer!>");
		}
		if(getVisitedPlanetNames().size() != 0) {
			information.add("Besuchte Planeten <" + getVisitedPlanetNames() +">");
		}else {
			information.add("Besuchte Planeten <Keine Planeten besucht!>");
		}
		if(getVisitedMoonNames().size() != 0) {
			information.add("Besuchte Monde <" + getVisitedMoonNames() +">");
		}else {
			information.add("Besuchte Monde <Keine Monde besucht!>");
		}
		if(getColonizedPlanetNames().size() != 0) {
			information.add("Kolonisierte Planeten <" + getColonizedPlanetNames() +">");
		}else {
			information.add("Kolonisierte Planeten <Keine Planeten kolonisiert!>");
		}
		return information;
		
	}

	@Override
	public void upgrade(Runnable setExperience) {
		setExperience.run();
		setOverallLevel();
		setOverallBattle();
	}

	@Override
	public void kill() {
		this.setHealth(0);
		
	}

	

}
