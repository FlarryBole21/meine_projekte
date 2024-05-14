package space.inventory.resource.rawfood;

import space.inventory.item.food.Eatable;
import space.inventory.resource.Resource;

public abstract class RawFood extends Resource implements Eatable{

	private static final long serialVersionUID = 1L;

	public RawFood(int amount) {
		super(amount);
	}
	



}
