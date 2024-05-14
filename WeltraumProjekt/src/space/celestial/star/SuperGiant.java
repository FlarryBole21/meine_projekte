package space.celestial.star;

public class SuperGiant extends Sun{

	private static final long serialVersionUID = 1L;

	public SuperGiant(String name, double mass, double radius, double temperature, double luminosity) {
		super(name, mass, radius, temperature, luminosity);
		super.setType("Superriese");
	}
	
	public SuperGiant() {
		super.setType("Superriese");
	}


}
