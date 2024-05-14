package space.inventory.resource.miningresource;

public class Iron extends Ore{

	private static final long serialVersionUID = 1L;

	public Iron(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Eisen");
	}
	

	
}
