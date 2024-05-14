package space.buildable;

import java.util.LinkedList;

import space.buildable.ship.BattleShip;
import space.buildable.ship.CargoShip;
import space.buildable.ship.ColonyShip;
import space.inventory.item.Item;

public class SpaceStation extends Building{
	
	private static final long serialVersionUID = 1L;
	private int capacity;
	private int level;
	
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

	public SpaceStation(int capacity) {
		super.setType("Raumstation");
		this.capacity = capacity;
		this.level = 1;
	}

	@Override
	public void demolition() {
	
	}

	@Override
	public void build() {
		
	}

	@Override
	public void upgrade(Runnable setLevel) {
		setLevel.run();
	}
	
    public BattleShip craftBattleShip(LinkedList<Item> items, int amount){
		
		return new BattleShip();
		
	}
	
	public ColonyShip craftColonyShip(LinkedList<Item> items, int amount){
		
		return new ColonyShip(200, 100);
		
	}
	
	public CargoShip craftCargoShip(LinkedList<Item> items, int amount){
		
		return new CargoShip(200, 100);
	}

}
