package space.celestial.star;

import java.util.LinkedList;

import space.celestial.CelestialObject;

public abstract class Sun extends CelestialObject{

	private static final long serialVersionUID = 1L;
	private String name;
    private double mass; 
    private double radius; 
    private double size;
    private double temperature; 
    private double luminosity;


    public Sun(String name, double mass, double radius, double temperature, double luminosity ) {
    	this.name=name;
    	this.mass=mass;
    	this.radius=radius;
    	this.size=radius*2;
    	this.temperature=temperature;
    	this.luminosity=luminosity;
    }
    
    public Sun() {
    	this(null,0,0,0,0);
    	
    }
    
    
    public String getName() {
		return this.name;
	}
    
    
    public LinkedList<String> getInformation() {
		LinkedList<String> information = new LinkedList<>();
		information.add("Name <" + name + ">");
		information.add("Typ <" + getType()+">");
		information.add("Masse <" + mass+">");
		information.add("Radius <" + radius+">");
		information.add("Größe <" + size+">");
		information.add("Temperatur <" + temperature + ">");
		information.add("Helligkeit <" + luminosity+">");

		
		return information;
		
	}

}
