package sudoku_project;

public abstract class CustomMath {

	public static boolean isPerfectSquare(int squareNumber, int rootNumber) {
		if (squareNumber < 0 || rootNumber > squareNumber || rootNumber < 0) {
			return false;
		}
		return rootNumber * rootNumber == squareNumber;
	}

	public static boolean isPerfectSquare(int squareNumber) {
		if (squareNumber < 0) {
			return false;
		}

		double sqrt = Math.sqrt(squareNumber);
		return sqrt == Math.floor(sqrt);
	}

	public static int subtractRemainder(int number, int mod) {
		return number - number % mod;
	}
	
	
	public static boolean isDivIntOfSqrtAWholeNumber(int baseNumber) {
		return baseNumber % ((int) Math.sqrt(baseNumber)) == 0 && baseNumber % (baseNumber / (int) Math.sqrt(baseNumber)) == 0;
	}
	
	
	public static int[] checkDividerBelowHalf (int baseNumber) {
		for(int i=(int)(baseNumber/2)-1; i>=0; i--) {
			if(baseNumber % i == 0) {
				int[] arr = {i, (int) baseNumber / i};
				return arr;
			}	
		}
		int[] arr = new int[0];
		return arr;
	}
	
	public static String convertToNumberFormat(String format) {
		if (format.equals("dez")) {
			return "02d";
		} else if (format.equals("hex")) {
			return "X";
		} else {
			return "";
		}
	}

}
