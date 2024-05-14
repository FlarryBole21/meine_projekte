package space.inventory.item.weapon;

public class LaserCannon extends EnergyRangeWeapon{

	private static final long serialVersionUID = 1L;

	public LaserCannon() {
		super(20);
		super.setType("Laserkanone");
	}

	@Override
	public String getType() {
		return "Laser-Kanone";
	}

}
