package space.inventory.item.weapon;

public class StarshockMissile extends Missile{

	private static final long serialVersionUID = 1L;

	public StarshockMissile() {
		super(20);
		super.setType("Sternschockrakete");
	}

	@Override
	public String getType() {
		return "Sternschock-Rakete";
	}

}
