package space.inventory.item.weapon;

public abstract class PointDefenseSystem extends RangeWeapon{

	private static final long serialVersionUID = 1L;

	private int defense;
	public PointDefenseSystem(int strength, int defense) {
		super(strength);
		this.defense = defense;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}

}
