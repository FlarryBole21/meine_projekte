package space.environment.atmosphere;
import space.lifeform.role.Player;

public class ToxicAtmosphere extends Atmosphere {
	
	private static final long serialVersionUID = 1L;

	public ToxicAtmosphere() {
		super.setType("Giftig");
	}

	@Override
	public void inhale(boolean normalSuit, Player player) {
		
		if(normalSuit) {
			System.out.println("Die Luft ist " + getType());
			System.err.println("Der Spieler hat einen " + player.getCurrentSuit().getType());
			System.err.println("Er ist NICHT gewappnet gegen die Luft");
			System.err.println("Dies hat Auswirkungen auf den Spieler!");
			System.err.println("Spieler verliert zwei Lebenspunkte!");
			player.setHealth(player.getHealth()-2);
			
		}else {
			basicMessage(player);
		}
		
	}

}
