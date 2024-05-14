package space.buildable.ship;

import java.util.LinkedList;

import space.inventory.item.weapon.PointDefenseSystem;
import space.inventory.item.weapon.RangeWeapon;
import space.inventory.item.weapon.Weapon;

public class BattleShip extends Ship{

	
	private static final long serialVersionUID = 1L;
	private int strength;
	private int baseStrength;
	private int baseHealth;
	private LinkedList<RangeWeapon> equippedWeapons;

	public BattleShip() {
		super(50, 100);
		super.setType("Kampfschiff");
		this.strength = 10;
		this.baseStrength=strength;
		this.baseHealth=getHealth();
		this.equippedWeapons = new LinkedList<>();
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength() {
		int localStrength = baseStrength;
		int localHealth = baseHealth;
		for(Weapon equippedWeapon: equippedWeapons) {
			localStrength +=equippedWeapon.getStrength();
			
			if(equippedWeapon instanceof PointDefenseSystem) {
				PointDefenseSystem system = (PointDefenseSystem) equippedWeapon;
				localHealth +=system.getDefense();
			}
		}
		this.strength = localStrength;
		setHealth(localHealth);
	}
	
	@Override
	public void build() {
		
	}

	@Override
	public void demolition() {
		
	}
	
	public LinkedList<RangeWeapon> getEquippedWeapons(){
		return this.equippedWeapons;
	}
	
	
	public LinkedList<String> getEquippedWeaponsNames() {
		LinkedList<String> names = new LinkedList<>();
		for (int i = 0; i < getEquippedWeapons().size(); i++) {
			names.add(getEquippedWeapons().get(i).getType());
		}
		return names;
	}

	
	public void addWeapon(RangeWeapon weapon) {
		this.equippedWeapons.add(weapon);
		setStrength();
	}
	
	@Override
	public LinkedList<String>  getInformation() {
		LinkedList<String> information = new LinkedList<>();
		information.add("Angriff <" + strength+">");
		information.add("Lebenspunkte <" + getHealth()+">");
		information.add("Kapazität <" + getCapacity()+">");
		information.add("Level <" + getLevel()+">");
		if(getEquippedWeaponsNames().size() != 0) {
			information.add("Ausgerüstete Waffen <" + getEquippedWeaponsNames() +">");
		}else {
			information.add("Ausgerüstete Waffen <Keine Waffen ausgerüstet!>");
		}
		
		return information;
	}
	


}
