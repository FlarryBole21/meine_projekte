package space.inventory.item.weapon;

public class MeteorMauler extends KineticWeapon{

	private static final long serialVersionUID = 1L;

	public MeteorMauler() {
		super(20);
		super.setType("Meteor-Mauler");
	}

	@Override
	public String getType() {
		return "Meteor-Mauler";
	}

}
