package space.inventory.resource.miningresource;

import space.inventory.resource.Resource;

public abstract class MiningResource extends Resource implements Degradable{

	private static final long serialVersionUID = 1L;
	private int requiredLevel;

	public MiningResource(int requiredLevel, int amount) {
		super(amount);
		this.requiredLevel = requiredLevel;
	}
	

	public int getRequiredLevel() {
		return requiredLevel;
	}


	@Override
	public void degrade() {
		
	}

}
