package space.inventory.resource.rawfood;

public class StrawBerry extends Berry{

	private static final long serialVersionUID = 1L;

	public StrawBerry(int requiredLevel, int amount) {
		super(requiredLevel, amount);
		super.setType("Erdbeere");
	}
	
	

	@Override
	public void eat() {
		
	}
	
	

}
