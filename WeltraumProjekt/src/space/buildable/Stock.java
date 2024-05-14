package space.buildable;

public class Stock extends Building{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int stockSize;
	private int level;
	
	public Stock(String name, int stockSize) {
		super.setType("Lager");
		this.name = name;
		this.stockSize = stockSize;
		this.level=1;
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
