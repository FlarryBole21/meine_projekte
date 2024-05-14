package space.inventory.item.suit;

import space.Upgradable;
import space.inventory.item.Item;
import space.lifeform.LifeformObject;

public abstract class Suit extends Item implements Wearable, Upgradable{
	
	private static final long serialVersionUID = 1L;
	private int defense;
	
	public Suit(int defense) {
		this.defense = defense;
	}
	
	@Override
	public void wear(LifeformObject lifeform) {
	}
	
	
	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	
	@Override
	public void upgrade(Runnable setLevel) {
		setLevel.run();
	}
	

}
