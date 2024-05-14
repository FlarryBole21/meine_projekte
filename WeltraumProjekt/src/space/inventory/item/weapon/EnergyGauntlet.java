package space.inventory.item.weapon;

public class EnergyGauntlet extends EnergyMeleeWeapon{

	private static final long serialVersionUID = 1L;

	public EnergyGauntlet() {
		super(15);
		super.setType("Energie-Handschuhe");
	
	}

	@Override
	public String getType() {
		return "Energie-Handschuhe";
	}

}
