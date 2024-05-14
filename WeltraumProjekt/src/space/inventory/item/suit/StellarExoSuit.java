package space.inventory.item.suit;

import space.lifeform.LifeformObject;

public class StellarExoSuit extends Suit{

	private static final long serialVersionUID = 1L;

	public StellarExoSuit() {
		super(30);
		super.setType("Steller-Exo-Anzug");
	}

	@Override
	public void wear(LifeformObject lifeform) {
		int armorDefense = super.getDefense();
		int lifeformDefense = lifeform.getDefense();
		lifeform.setDefense(armorDefense + lifeformDefense);
	}

	@Override
	public String getType() {
		return "Astral-Exo-Anzug";
	}

}
