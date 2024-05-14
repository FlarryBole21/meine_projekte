package space.celestial.star;

public class RedGiant extends Sun{

	private static final long serialVersionUID = 1L;

	public RedGiant(String name, double mass, double radius, double temperature, double luminosity) {
		super(name, mass, radius, temperature, luminosity);
		super.setType("Roter Riese");
		
	}

	public RedGiant() {
		super.setType("Roter Riese");
	}
	


}
