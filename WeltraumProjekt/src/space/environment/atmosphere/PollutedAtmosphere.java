package space.environment.atmosphere;

import space.lifeform.role.Player;

public class PollutedAtmosphere extends Atmosphere {

	private static final long serialVersionUID = 1L;

	public PollutedAtmosphere() {
		super.setType("Verschmutzt");
	}
	
	@Override
	public void inhale(boolean check, Player player) {
		
		if(check) {
			System.out.println("Die Luft ist " + getType());
			System.err.println("Der Spieler hat einen " + player.getCurrentSuit().getType());
			System.err.println("Er ist NICHT gewappnet gegen die Luft");
			System.err.println("Dies hat Auswirkungen auf den Spieler!");
			System.err.println("Spieler verliert ein Lebenspunkt!");
			player.setHealth(player.getHealth()-1);
			
		}else {
			basicMessage(player);
		}
		
	}


}
