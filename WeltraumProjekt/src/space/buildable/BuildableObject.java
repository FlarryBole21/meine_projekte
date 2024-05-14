package space.buildable;

import java.io.Serializable;

import space.Typeable;
import space.Upgradable;

public abstract class BuildableObject implements Buildable, Demolitionable, Upgradable, Typeable, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String type;
	
	@Override
	public String getType() {
		return type;
	}
	
	
	@Override
	public void setType(String type) {
		this.type=type;
		
	}

}
