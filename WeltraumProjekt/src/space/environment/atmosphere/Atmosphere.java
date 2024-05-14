package space.environment.atmosphere;

import space.environment.EnvironmentObject;
import space.lifeform.role.Player;

public abstract class Atmosphere extends EnvironmentObject implements Inhalable{

	private static final long serialVersionUID = 1L;
	
	
	public void basicMessage(Player player) {
		System.out.println("Die Luft ist " + getType());
		System.out.println("Der Spieler hat einen " + player.getCurrentSuit().getType());
		System.out.println("Er ist gewappnet gegen die Luft");
		System.out.println("Dies hat keinerlei Auswirkungen auf den Spieler!");
	}
	
	
}
