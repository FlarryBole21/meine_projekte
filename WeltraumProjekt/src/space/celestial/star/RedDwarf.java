package space.celestial.star;

public class RedDwarf extends Sun{


	private static final long serialVersionUID = 1L;

	public RedDwarf(String name, double mass, double radius, double temperature, double luminosity) {
		super(name, mass, radius, temperature, luminosity);
		super.setType("Roter Zwerg");
	}
	
	public RedDwarf() {
		super.setType("Roter Zwerg");
	}



}
