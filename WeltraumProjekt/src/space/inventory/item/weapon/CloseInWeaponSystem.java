package space.inventory.item.weapon;

public class CloseInWeaponSystem extends PointDefenseSystem {

	private static final long serialVersionUID = 1L;

	public CloseInWeaponSystem() {
		super(5, 10);
		super.setType("CloseIn-Waffensystem");
	}

	@Override
	public String getType() {
		return "Close-In-Waffensystem";
	}

}
