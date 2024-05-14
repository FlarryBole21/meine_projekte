package space.inventory;

import java.io.Serializable;

import space.Typeable;

public abstract class InventoryObject implements Droppable, Useable, Collectable, Typeable, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String type;
	private int amount;
	
	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addAmount(int amount) {
		this.amount += amount;
	}
	
	public void removeAmount(int amount) {
		this.amount -= amount;
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
		
	}
	
	
	@Override
	public void collect() {
		
	}

	@Override
	public void drop() {
		
	}
	

}
