package space;

import java.io.Serializable;
import java.util.LinkedList;

import space.celestial.Moon;
import space.celestial.Planet;
import space.celestial.star.BlueGiant;
import space.celestial.star.MainSequenceStar;
import space.celestial.star.RedDwarf;
import space.celestial.star.RedGiant;
import space.celestial.star.Sun;
import space.celestial.star.SuperGiant;
import space.celestial.star.WhiteDwarf;
import space.environment.atmosphere.Atmosphere;
import space.environment.terrain.PlanetTerrain;
import space.environment.terrain.Terrain;

public class SolarSystem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private LinkedList<Sun> suns;
	private LinkedList<Planet> planets;
	private MainSequenceStar mainstar;
	private Galaxy galaxy;

	public SolarSystem(String name, Galaxy galaxy) {
		this.name = name;
		this.suns = new LinkedList<>();
		this.planets = new LinkedList<>();
		this.galaxy=galaxy;
	}
	
	public MainSequenceStar getMainstar() {
		return mainstar;
	}

	public void setMainstar(MainSequenceStar mainstar) {
		this.mainstar = mainstar;
	}

	
	public String getName() {
		return this.name;
	}
	
	
	public Galaxy getGalaxy() {
		return galaxy;
	}

	public void setGalaxy(Galaxy galaxy) {
		this.galaxy = galaxy;
	}

	public Sun addSun(String name, double mass, double radius, double temperature, double luminosity, Sun suntype) {
		Sun sun = null;
		if(suntype instanceof MainSequenceStar) {
			sun = new MainSequenceStar(name, mass, radius, temperature, luminosity);
		}else if(suntype instanceof BlueGiant) {
			sun = new BlueGiant(name, mass, radius, temperature, luminosity);
		}else if(suntype instanceof WhiteDwarf) {
			sun = new WhiteDwarf(name, mass, radius, temperature, luminosity);
		}else if(suntype instanceof RedDwarf) {
			sun = new RedDwarf(name, mass, radius, temperature, luminosity);
		}else if(suntype instanceof RedGiant) {
			sun = new RedGiant(name, mass, radius, temperature, luminosity);
		}else if(suntype instanceof SuperGiant) {
			sun = new SuperGiant(name, mass, radius, temperature, luminosity);
		}
		suns.add(sun);
		return sun;
		
	}
	
	
	public Planet addPlanet(String name, double size, double mass,
			double gravity, Atmosphere atmosphere, PlanetTerrain terrain) {
		
		Planet planet = new Planet(name, size, mass, gravity, atmosphere, terrain);
		planets.add(planet);
		planet.setSolarsystem(this);
		return planet;
		
	}
	
	
	public LinkedList<Planet> getPlanets() {
		return planets;
	}
	
	public LinkedList<String> getPlanetNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getPlanets().size(); i++) {
			names.add(getPlanets().get(i).getName());
		}
		return names;
	}
	
	
	
	public LinkedList<Sun> getSuns() {
		return suns;
	}
	
	public LinkedList<String> getSunNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getSuns().size(); i++) {
			names.add(getSuns().get(i).getName());
		}
		return names;
	}
	
	public void getBasicInformation() {
			System.out.println("-- Sonnensystem <" + getName() + "> --");
			System.out.println("Planeten-Namen <" + getPlanetNames() + ">");
			System.out.println("Sonnen-Namen <" + getSunNames() + ">");
			System.out.println();
		
	}

}
