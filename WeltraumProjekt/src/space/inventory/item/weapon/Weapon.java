package space.inventory.item.weapon;


import space.Upgradable;
import space.inventory.item.Item;

public abstract class Weapon extends Item implements Upgradable{
	
	private static final long serialVersionUID = 1L;
	private int strength;
	private int level;
	
	public Weapon(int strength) {
		this.strength = strength;
		this.level = 1;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void use() {
		
	}

	@Override
	public void upgrade(Runnable setLevel) {
		setLevel.run();
	}
	
	

}
