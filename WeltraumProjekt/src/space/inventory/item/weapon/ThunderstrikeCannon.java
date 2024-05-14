package space.inventory.item.weapon;

public class ThunderstrikeCannon extends KineticWeapon{

	private static final long serialVersionUID = 1L;

	public ThunderstrikeCannon() {
		super(25);
		super.setType("Donnerschlagkanone");
	}

	@Override
	public String getType() {
		return "Donnerschlag-Kanone";
	}

}
