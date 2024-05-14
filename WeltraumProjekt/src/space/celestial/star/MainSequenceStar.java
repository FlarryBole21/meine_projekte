package space.celestial.star;

import java.util.LinkedList;

import space.celestial.Planet;

public class MainSequenceStar extends Sun{
	
	private static final long serialVersionUID = 1L;
	private LinkedList<Planet> planets;

	public MainSequenceStar(String name, double mass, double radius, double temperature, double luminosity) {
		super(name, mass, radius, temperature, luminosity);
		super.setType("Hauptsequenzstern");
		planets = new LinkedList<>();
	}
	
	public MainSequenceStar() {
		super();
		super.setType("Hauptsequenzstern");
		planets = new LinkedList<>();
	}

	public void addPlanet(Planet planet) {
		planets.add(planet);
	}
	
	public LinkedList<Planet> getPlanets(){
		return planets;
	}
	
	public LinkedList<String> getPlanetNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getPlanets().size(); i++) {
			names.add(getPlanets().get(i).getName());
		}
		return names;
	}

}
