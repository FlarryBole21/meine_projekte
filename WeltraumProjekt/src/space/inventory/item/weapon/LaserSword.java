package space.inventory.item.weapon;

public class LaserSword extends EnergyMeleeWeapon{

	private static final long serialVersionUID = 1L;

	public LaserSword() {
		super(15);
		super.setType("Laserschwert");
	}

	@Override
	public String getType() {
		return "Laser-Schwert";
	}

}
