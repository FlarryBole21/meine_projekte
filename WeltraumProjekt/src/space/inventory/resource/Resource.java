package space.inventory.resource;

import space.inventory.InventoryObject;

public abstract class Resource extends InventoryObject{
	
	private static final long serialVersionUID = 1L;
	

	public Resource(int amount) {
		super.setAmount(amount);
	}
	

	@Override
	public void use() {
		
	}

}
