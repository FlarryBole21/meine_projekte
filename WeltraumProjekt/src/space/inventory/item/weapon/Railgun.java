package space.inventory.item.weapon;

public class Railgun extends EnergyRangeWeapon{

	private static final long serialVersionUID = 1L;

	public Railgun() {
		super(20);
		super.setType("Schienenkanone");
	}

	@Override
	public String getType() {
		return "Schienenkanone";
	}

}
