package space.buildable.ship;

import java.util.LinkedList;

import space.buildable.Vehicle;

public abstract class Ship extends Vehicle{

	private static final long serialVersionUID = 1L;
	private int health;
	private int capacity;
	private int level;
	
	public Ship (int health, int capacity) {
		this.health = health;
		this.capacity = capacity;
		this.level = 1;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void removeHealth(int health) {
		this.health-=health;
	}
	
	public void addHealth(int health) {
		this.health+=health;
	}


	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public void upgrade(Runnable setLevel) {
		setLevel.run();
	}
	
	public LinkedList<String>  getInformation() {
		LinkedList<String> information = new LinkedList<>();
		information.add("Lebenspunkte <" + health+">");
		information.add("Kapazität <" + capacity+">");
		information.add("Level <" + level+">");
		
		return information;
	}
	
	
	


}
