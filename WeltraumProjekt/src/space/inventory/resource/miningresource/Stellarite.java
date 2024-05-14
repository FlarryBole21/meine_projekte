package space.inventory.resource.miningresource;

public class Stellarite extends ExoticResource{

	private static final long serialVersionUID = 1L;

	public Stellarite(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Stellarite");
	}

}
