package space.inventory.item.weapon;

public class TitanTurret extends KineticWeapon{

	private static final long serialVersionUID = 1L;

	public TitanTurret() {
		super(20);
		super.setType("Titan-Turret");
	}

	@Override
	public String getType() {
		return "Titan-Turret";
	}

}
