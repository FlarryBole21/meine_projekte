package space.inventory.resource.miningresource;

public class Chronium extends ExoticResource{

	private static final long serialVersionUID = 1L;

	public Chronium(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Chronium");
	}

}
