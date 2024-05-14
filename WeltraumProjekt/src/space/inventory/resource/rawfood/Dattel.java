package space.inventory.resource.rawfood;

public class Dattel extends Fruit{

	private static final long serialVersionUID = 1L;

	public Dattel(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Dattel");
	}

	@Override
	public void eat() {
		
	}

}
