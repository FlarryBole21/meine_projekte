package immaterial.oracle;

public class MathOracle extends Oracle {

	private static final long serialVersionUID = 1L;
	
	public MathOracle() {
		super.setType("Orakel der Mathematik");
	}
	
	
	public int calcPercent(double number) {
		double decimalPoints = number - (int)number;
		int percent = (int)(decimalPoints * 100);
		return percent;
	}
	

}
