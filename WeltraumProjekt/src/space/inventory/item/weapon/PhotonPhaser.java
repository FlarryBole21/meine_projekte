package space.inventory.item.weapon;

public class PhotonPhaser extends EnergyRangeWeapon{

	private static final long serialVersionUID = 1L;

	public PhotonPhaser() {
		super(35);
		super.setType("Photon-Phaser");
	}

	@Override
	public String getType() {
		return "Photonen-Phaser";
	}

}
