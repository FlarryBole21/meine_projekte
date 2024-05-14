package space.inventory.resource.miningresource;

public class Plasma extends ExoticResource{


	private static final long serialVersionUID = 1L;

	public Plasma(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Plasma");
	}
	

}
