package space.inventory.item.weapon;

public class QuantumTorpedo extends Missile{

	private static final long serialVersionUID = 1L;

	public QuantumTorpedo() {
		super(20);
		super.setType("Quantumtorpedo");
	}

	@Override
	public String getType() {
		return "Quantum-Torpedo";
	}

}
