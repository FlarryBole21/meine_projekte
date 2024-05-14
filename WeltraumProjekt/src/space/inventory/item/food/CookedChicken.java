package space.inventory.item.food;

public class CookedChicken extends CookedFood  {
	
	private static final long serialVersionUID = 1L;

	public CookedChicken() {
		super.setType("Gekochtes Hähnchen");
	}

	@Override
	public void eat() {
		
	}

	@Override
	public String getType() {
		return "Gekochtes Hähnchen";
	}

}
