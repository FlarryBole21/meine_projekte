package space.inventory.resource.wood;

import space.inventory.resource.Resource;

public abstract class Wood extends Resource implements Choppable{
	
	private static final long serialVersionUID = 1L;
	private int requiredLevel;

	public Wood(int requiredLevel, int amount) {
		super(amount);
		this.requiredLevel = requiredLevel;
	}
	

	public int getRequiredLevel() {
		return requiredLevel;
	}


	@Override
	public void chopp() {
		
	}

}
