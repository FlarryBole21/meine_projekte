package space.inventory.resource.miningresource;

public class Silver extends Ore{

	private static final long serialVersionUID = 1L;

	public Silver(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Silber");
	}

}
