package space.inventory.resource.wood;

public class MapleWood extends Wood{

	private static final long serialVersionUID = 1L;

	public MapleWood(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Ahornholz");
	}

}
