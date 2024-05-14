package space;

import java.io.Serializable;
import java.util.LinkedList;

public class Galaxy implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private LinkedList<SolarSystem> solarSystems;
	private Universe universe;
	
	public Galaxy(String name) {
		this.name = name;
		this.solarSystems = new LinkedList<>();
	}
	
	public SolarSystem addSolarSystem(String name) {
		SolarSystem solarSystem = new SolarSystem(name, this);
		solarSystems.add(solarSystem);
		return solarSystem;
		
	}
	
	public Universe getUniverse() {
		return this.universe;
	}
	
	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	public String getName() {
		return name;
	}

	public LinkedList<SolarSystem> getSolarSystems() {
		return solarSystems;
	}
	
	public LinkedList<String> getSolarSystemsNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getSolarSystems().size(); i++) {
			names.add(getSolarSystems().get(i).getName());
		}
		return names;
	}
	
	public void getInformation() {
		System.out.println("Universum <"+ getUniverse().getName() +">");
		System.out.println("Galaxie <"+ getName() +">");
		for(SolarSystem solarsystem : getSolarSystems()) {
			System.out.println("Sonnensystem <"+ solarsystem.getName() +">");
		}
	}

	
	

}
