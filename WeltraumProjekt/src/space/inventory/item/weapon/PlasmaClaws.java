package space.inventory.item.weapon;

public class PlasmaClaws extends MeleeWeapon{

	private static final long serialVersionUID = 1L;

	public PlasmaClaws() {
		super(30);
		super.setType("Plasma-Krallen");
	}

	@Override
	public String getType() {
		return "Plasma-Krallen";
	}

}
