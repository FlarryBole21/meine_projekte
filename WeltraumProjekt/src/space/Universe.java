package space;

import java.io.Serializable;
import java.util.LinkedList;

import space.celestial.star.Sun;
import space.environment.atmosphere.Atmosphere;
import space.environment.atmosphere.NormalAtmosphere;
import space.environment.atmosphere.PollutedAtmosphere;
import space.environment.atmosphere.ToxicAtmosphere;
import space.environment.terrain.Canyon;
import space.environment.terrain.Crater;
import space.environment.terrain.Desert;
import space.environment.terrain.Grassland;
import space.environment.terrain.Jungle;
import space.environment.terrain.MoonCrater;
import space.environment.terrain.MoonIcyPlains;
import space.environment.terrain.MoonTerrain;
import space.environment.terrain.MoonVolcanicPlains;
import space.environment.terrain.Mountains;
import space.environment.terrain.NormalForest;
import space.environment.terrain.PlanetTerrain;
import space.environment.terrain.Savannah;
import space.environment.terrain.Swamp;
import space.environment.terrain.Terrain;
import space.environment.terrain.Tundra;
import space.environment.terrain.Volcano;
import space.celestial.star.MainSequenceStar;
import space.celestial.star.BlueGiant;
import space.celestial.star.RedDwarf;
import space.celestial.star.RedGiant;
import space.celestial.star.WhiteDwarf;
import space.celestial.star.SuperGiant;

public class Universe implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private LinkedList<Galaxy> galaxies;
	private LinkedList<Atmosphere> atmospheres;
	private LinkedList<PlanetTerrain> planetTerrains;
	private LinkedList<MoonTerrain> moonTerrains;
	private LinkedList<Sun> suns;
	private LinkedList<String> planetdata;
	private LinkedList<String> sundata;
	private LinkedList<String> moondata;
	
	public Universe(String name) {
		this.name = name;
		this.galaxies = new LinkedList<>();
		this.atmospheres = new LinkedList<>();
		this.suns = new LinkedList<>();
		this.planetTerrains = new LinkedList<>();
		this.moonTerrains = new LinkedList<>();
		addAtmospheres();
		addPlanetTerrains();
		addMoonTerrains();
		addSuns();
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public LinkedList<String> getPlanetdata() {
		return planetdata;
	}

	public LinkedList<String> getSundata() {
		return sundata;
	}
	
	public LinkedList<String> getMoondata() {
		return moondata;
	}

	public void setPlanetdata(LinkedList<String> planetdata) {
		LinkedList<String> cleanedList = new LinkedList<>();
	    for (String str : planetdata) {
	    	String cleanedString = str.replaceAll("\\s+", " "); 
	    	cleanedList.add(cleanedString);
	    }
	    //System.out.println(cleanedList.get(0).split(" ").length);
	    this.planetdata = cleanedList;
	}

	public void setSundata(LinkedList<String> sundata) {
	    LinkedList<String> cleanedList = new LinkedList<>();
	    for (String str : sundata) {
	    	String cleanedString = str.replaceAll("\\s+", " "); 
	        cleanedList.add(cleanedString);
	    }
	    this.sundata = cleanedList;
	}
	
	
	public void setMoondata(LinkedList<String> moondata) {
	    LinkedList<String> cleanedList = new LinkedList<>();
	    for (String str : moondata) {
	    	String cleanedString = str.replaceAll("\\s+", " "); 
	        cleanedList.add(cleanedString);
	    }
	    this.moondata = cleanedList;
	}

	
	public Galaxy addGalaxy(String name) {
		Galaxy galaxy = new Galaxy(name);
		galaxies.add(galaxy);
		return galaxy;
		
	}
	
	private void addAtmospheres() {
		atmospheres.add(new NormalAtmosphere());
		atmospheres.add(new PollutedAtmosphere());
		atmospheres.add(new ToxicAtmosphere());
		
	}

	public LinkedList<Atmosphere> getAtmospheres() {
		return atmospheres;
	}
	
	public LinkedList<PlanetTerrain> getPlanetTerrains() {
		return planetTerrains;
	}
	
	public LinkedList<MoonTerrain> getMoonTerrains() {
		return moonTerrains;
	}
	
	private void addPlanetTerrains() {
		planetTerrains.add(new Canyon());
		planetTerrains.add(new Crater());
		planetTerrains.add(new Desert());
		planetTerrains.add(new Grassland());
		planetTerrains.add(new Jungle());
		planetTerrains.add(new Mountains());
		planetTerrains.add(new NormalForest());
		planetTerrains.add(new Savannah());
		planetTerrains.add(new Swamp());
		planetTerrains.add(new Tundra());
		planetTerrains.add(new Volcano());
		
	}
	
	private void addMoonTerrains() {
		moonTerrains.add(new MoonCrater());
		moonTerrains.add(new MoonIcyPlains());
		moonTerrains.add(new MoonVolcanicPlains());
		
		
	}
	
	private void addSuns() {
		suns.add(new MainSequenceStar());
		suns.add(new BlueGiant());
		suns.add(new RedDwarf());
		suns.add(new RedGiant());
		suns.add(new WhiteDwarf());
		suns.add(new SuperGiant());
		
	}
	
	
	public LinkedList<Sun> getSuns() {
		return suns;
	}


}
