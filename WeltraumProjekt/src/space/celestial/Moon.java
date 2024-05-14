package space.celestial;

import java.io.Serializable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import space.environment.atmosphere.Atmosphere;
import space.environment.terrain.Grassland;
import space.environment.terrain.MoonCrater;
import space.environment.terrain.MoonTerrain;
import space.environment.terrain.Terrain;
import space.inventory.resource.Resource;
import space.lifeform.LifeformObject;

public class Moon extends EnterableCelestial implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private double size;
	private double mass;
	private double radius;
	private double gravity;
	private Set<Resource> resources;
	private MoonTerrain moonTerrain;
	private LinkedList<LifeformObject> lifeforms;
	private Atmosphere atmosphere;
	private Planet planet;
	
	public Moon(String name, double size, double mass, double gravity, Atmosphere atmosphere,MoonTerrain moonTerrain) {
		super.setType("Mond");
		this.name=name;
		this.size=size;
		this.mass=mass;
		this.radius=size/2;
		this.gravity=gravity;
		this.atmosphere=atmosphere;
		this.resources= new HashSet<>(); 
		this.moonTerrain = moonTerrain;
		this.lifeforms= new LinkedList<>();
	}

	
	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public Terrain getMoonTerrain() {
		return moonTerrain;
	}
	
	public Set<Resource> getResource() {
		return this.resources;
	}
	
	public void addResource(Resource resource) {
		this.resources.add(resource);
		
	}
	
	public LinkedList<String> getResourceNames() {
	    LinkedList<String> names = new LinkedList<>();
	    for (Resource element : getResource()) {
	        String type = element.getType();
	        names.add(type + " <" + element.getAmount() + ">");
	    }
	 
	    return names;
	}
	
	@Override
	public LinkedList<String> getInformation() {
		LinkedList<String> information = new LinkedList<>();
		information.add("Name <" + name+">");
		information.add("Größe <" + size+">");
		information.add("Masse <" + mass+">");
		information.add("Radius <" + radius+">");
		information.add("Gravitation <" + gravity+">");
		information.add("Atmosphäre <" + atmosphere.getType()+">");
		information.add("Terrain <" + moonTerrain.getType()+">");
		if(getResource().size() != 0) {
			information.add("Ressourcen <" + getResourceNames() +">");
		}else {
			information.add("Ressourcen <Keine Ressourcen vorhanden!>");
		}
		information.add("Zugehöriger Planet <" + planet.getName()+">");
		
		return information;
		
	}

	@Override
	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

}
