package space.environment.atmosphere;

import space.lifeform.role.Player;

public class NormalAtmosphere extends Atmosphere{

	private static final long serialVersionUID = 1L;

	public NormalAtmosphere() {
		super.setType("Normal");
	}

	@Override
	public void inhale(boolean check, Player player) {
		
		System.out.println("Die Luft ist " + getType());
		System.out.println("Dies hat keinerlei Auswirkungen auf den Spieler!");
		
	}


}
