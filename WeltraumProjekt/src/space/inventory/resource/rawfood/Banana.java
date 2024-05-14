package space.inventory.resource.rawfood;

public class Banana extends Fruit{

	private static final long serialVersionUID = 1L;

	public Banana(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Banane");
	}
	
	@Override
	public void eat() {
		
	}


}
