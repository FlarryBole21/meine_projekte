package space.lifeform;

import java.util.LinkedList;

import space.inventory.Droppable;

public abstract class KillableAnimal extends Animal implements Killable{
	
	private static final long serialVersionUID = 1L;
	private LinkedList<Droppable> drops;

	public KillableAnimal(int strength, int defense, int health) {
		super(strength, defense, health);
	}

}
