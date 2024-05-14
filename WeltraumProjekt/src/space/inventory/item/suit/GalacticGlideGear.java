package space.inventory.item.suit;

import space.lifeform.LifeformObject;

public class GalacticGlideGear extends Suit{

	private static final long serialVersionUID = 1L;

	public GalacticGlideGear() {
		super(20);
		super.setType("Galaktische Gleiterrüstung");
	}

	@Override
	public void wear(LifeformObject lifeform) {
		int armorDefense = super.getDefense();
		int lifeformDefense = lifeform.getDefense();
		lifeform.setDefense(armorDefense + lifeformDefense);
	}


	@Override
	public String getType() {
		return "Galaktische Gleiter-Rüstung";
	}

}
