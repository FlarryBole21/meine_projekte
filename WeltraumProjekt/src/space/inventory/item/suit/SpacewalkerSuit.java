package space.inventory.item.suit;

import space.lifeform.LifeformObject;

public class SpacewalkerSuit extends Suit {

	private static final long serialVersionUID = 1L;

	public SpacewalkerSuit() {
		super(10);
		super.setType("Weltraumwandereranzug");
	}


	@Override
	public void craft() {
		
	}

	@Override
	public String getType() {
		return "Weltraumwanderer-Anzug";
	}

	@Override
	public void wear(LifeformObject lifeform) {
		int armorDefense = super.getDefense();
		int lifeformDefense = lifeform.getDefense();
		lifeform.setDefense(armorDefense + lifeformDefense);
	}

}
