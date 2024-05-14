package space.inventory.item.weapon;

public class GuardianGatling extends PointDefenseSystem{

	private static final long serialVersionUID = 1L;

	public GuardianGatling(int strength, int defense) {
		super(strength, defense);
		super.setType("Wächter-Gatling");
	}

	@Override
	public String getType() {

		return "Wächter-Gatling";
	}

}
