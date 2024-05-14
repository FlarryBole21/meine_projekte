package space.lifeform;

public class Snail extends KillableAnimal{

	private static final long serialVersionUID = 1L;


	public Snail(int strength, int defense, int health) {
		super(strength, defense, health);
		super.setType("Schnecke");
	}


	@Override
	public void kill() {
		
	}

}
