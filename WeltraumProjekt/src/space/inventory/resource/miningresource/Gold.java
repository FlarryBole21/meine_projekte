package space.inventory.resource.miningresource;

public class Gold extends Ore{

	private static final long serialVersionUID = 1L;

	public Gold(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Gold");
	}

}
