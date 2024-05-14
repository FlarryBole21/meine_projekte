package space.inventory.resource.wood;

public class NormalWood extends Wood{


	private static final long serialVersionUID = 1L;

	public NormalWood(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Normales Holz");
	}

}
