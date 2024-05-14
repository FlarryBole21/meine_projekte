package space.inventory.item.suit;

import space.lifeform.LifeformObject;

public class AstroplateArmor extends Suit{

	private static final long serialVersionUID = 1L;

	public AstroplateArmor() {
		super(50);
		super.setType("Astroplatten-Rüstung");
	}

	@Override
	public void wear(LifeformObject lifeform) {
		int armorDefense = super.getDefense();
		int lifeformDefense = lifeform.getDefense();
		lifeform.setDefense(armorDefense + lifeformDefense);
	}
	
	@Override
	public String getType() {
		return "Astroplatten-Rüstung";
	}

}
