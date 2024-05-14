package space.buildable;

public abstract class Factory extends Building{
	
	private static final long serialVersionUID = 1L;
	private int level;
	
	public Factory() {
		this.level = 1;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void demolition() {
		
	}

	@Override
	public void build() {
		
	}

	@Override
	public void upgrade(Runnable setLevel) {
		setLevel.run();
	}

}
