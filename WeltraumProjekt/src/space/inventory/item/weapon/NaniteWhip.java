package space.inventory.item.weapon;

public class NaniteWhip extends MeleeWeapon{

	private static final long serialVersionUID = 1L;

	public NaniteWhip() {
		super(50);
		super.setType("Nanite-Peitsche");
	}

	@Override
	public String getType() {
		return "Nanite-Peitsche";
	}
	
	

}
