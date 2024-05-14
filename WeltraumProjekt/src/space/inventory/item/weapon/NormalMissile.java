package space.inventory.item.weapon;

public class NormalMissile extends Missile{

	private static final long serialVersionUID = 1L;

	public NormalMissile() {
		super(5);
		super.setType("Normale Rakete");
	}

	@Override
	public String getType() {
		return "Normale Rakete";
	}

}
