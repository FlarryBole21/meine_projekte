package space.inventory.resource.miningresource;

public class Neutronium extends ExoticResource{

	private static final long serialVersionUID = 1L;

	public Neutronium(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Neutronium");
	}

}
