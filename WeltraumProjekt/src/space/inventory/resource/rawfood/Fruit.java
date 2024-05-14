package space.inventory.resource.rawfood;

public abstract class Fruit extends RawFood implements Harvestable{

	private static final long serialVersionUID = 1L;
	private int requiredLevel;
	
	public Fruit(int requiredLevel, int amount) {
		super(amount);
	}
	
	public int getRequiredLevel() {
		return requiredLevel;
	}


	@Override
	public void harvest() {
		
	}

}
