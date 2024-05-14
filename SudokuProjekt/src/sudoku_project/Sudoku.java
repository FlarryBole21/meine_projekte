package sudoku_project;

public class Sudoku extends Grid {

	private int boxSizeLength;
	private int boxSizeHeight;

	public Sudoku(int[][] arr) {
		super(arr);
		setBoxSize();
	}

	@Override
	public void setArr(int[][] arr) {
		super.setArr(arr);
		setBoxSize();
	}

	public int getBoxSizeLength() {
		return boxSizeLength;
	}

	public int getBoxSizeHeight() {
		return boxSizeHeight;
	}

	public void setBoxSize() {
		int num1 = 0;
		int num2 = 0;

		if (getSize() <= 3 & getSize() > 0) {
			num1 = getSize();
			num2 = 1;
			this.setBoxSize(num1, num2);
			return;
		}

		if (CustomMath.isPerfectSquare(getSize())) {
			num1 = (int) Math.sqrt(getSize());
			num2 = (int) Math.sqrt(getSize());
			this.setBoxSize(num1, num2);
			return;
		}

//		if(getSize() % 2 != 0) {
//			this.setBoxSize(num1, num2);
//			return;
//		}

		if (CustomMath.isDivIntOfSqrtAWholeNumber(getSize())) {
			num1 = (int) getSize() / (int) Math.sqrt(getSize());
			num2 = (int) getSize() / (getSize() / (int) Math.sqrt(getSize()));
			this.setBoxSize(num1, num2);
			return;
		}

		int[] myArr = CustomMath.checkDividerBelowHalf(getSize());
		if (myArr.length == 0) {
			num1 = (int) getSize() / 2;
			num2 = (int) getSize() / (getSize() / 2);
			this.setBoxSize(num1, num2);
			return;
		} else {
			num1 = myArr[0];
			num2 = myArr[1];
			this.setBoxSize(num1, num2);
		}
		
		this.setBoxSize(num1, num2);
	}

	public void setBoxSize(Integer sizeA, Integer sizeB) {
		this.boxSizeLength = sizeA;
		this.boxSizeHeight = sizeB;
	}

	public boolean checkBasicRequirements() {
		return checkValidGridSize() && checkValidNumberRange() && checkClueAmount();
	}

	public boolean checkValidNumberRange() {
		int[][] arr = getArr();
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (arr[i][j] < 0 || arr[i][j] > getSize()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkValidGridSize() {
		if (getBoxSizeLength() == 0 || getBoxSizeHeight() == 0) {
			return false;
		}

		int[][] arr = getArr();
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (arr[i].length != getSize()) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkClueAmount() {
		int count = 0;
		int[][] arr = getArr();
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (arr[i][j] != 0) {
					count++;
				}
			}
		}
		if (getMinimumClueAmount(getSize()) != 0 && count >= getMinimumClueAmount(getSize())) {
			return true;
		} else {
			return false;
		}
		
	}

	private int getMinimumClueAmount(int number) {
		switch (number) {
		case 1:
			return 0;
		case 2:
			return 1;
		case 3:
			return 3;
		case 4:
			return 4;
		case 6:
			return 8;
		case 8:
			return 14;
		case 9:
			return 17;
		case 12:
			return 36;
		case 16:
			return 55;
		default:
			return (int) (number * number) / 3;
		}
	}

	public boolean checkPlacingRequirement(int number, int row, int column) {
		if (getSize() <= 3) {
			return checkNumberInRow(number, row) && checkNumberInColumn(number, column);
		} else {
			return checkNumberInRow(number, row) && checkNumberInColumn(number, column)
					&& checkNumberInBox(number, row, column);
		}

	}

	public boolean checkNumberInRow(int number, int row) {
		if (number < 1 || number > getSize() || row < 0 || row > getSize()) {
			return false;
		}

		int[][] arr = getArr();
		for (int i = 0; i < getSize(); i++) {
			if (arr[row][i] == number) {
				return false;
			}
		}
		return true;
	}

	public boolean checkNumberInColumn(int number, int column) {
		if (number < 1 || number > getSize() || column < 0 || column > getSize()) {
			return false;
		}

		int[][] arr = getArr();
		for (int i = 0; i < getSize(); i++) {
			if (arr[i][column] == number) {
				return false;
			}
		}
		return true;
	}

	public boolean checkNumberInBox(int number, int row, int column) {
		if (number < 1 || number > getSize() || row < 0 || row > getSize() || column < 0 || column > getSize()) {
			return false;
		}
		int boxRow = CustomMath.subtractRemainder(row, getBoxSizeHeight());
		int boxColumn = CustomMath.subtractRemainder(column, getBoxSizeLength());
//		System.out.println(boxRow + "...."+boxColumn);
//		int boxRow = (int) (row / getBoxSizeHeight()) * getBoxSizeHeight();
//		int boxColumn = (int) (column / getBoxSizeLength()) * getBoxSizeLength();
		int[][] arr = getArr();
		for (int i = 0; i < getBoxSizeHeight(); i++) {
			for (int j = 0; j < getBoxSizeLength(); j++) {
				if (arr[boxRow + i][boxColumn + j] == number) {
					return false;
				}
			}
		}
		// System.out.println(boxRow + "" + boxColumn);
		return true;
	}

	public boolean solveRaw() {
		int[][] board = getArr();
		for (int row = 0; row < getSize(); row++) {
			for (int column = 0; column < getSize(); column++) {
				if (board[row][column] == 0) {
					for (int num = 1; num <= getSize(); num++) {

//						System.out.println(row + "," + column +"-->"+num);
						if (checkPlacingRequirement(num, row, column)) {
							board[row][column] = num;
							setArr(board);
							if (solveRaw()) {
								return true;
							}
							board[row][column] = 0;
							setArr(board);
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public void solve() {
		try {
			if (checkBasicRequirements()) {
				System.out.println("Warte auf Antwort....\n");
				try {
					if (solveRaw()) {
						return;
					} else {
						throw new RuntimeException("Sudoku ist nicht lösbar!");
					}
				} catch (RuntimeException e) {
					System.err.println("Fehler: " + e.getMessage() + "\n");
				}
			} else {
				throw new RuntimeException(
						"Sudoku ist nicht eindeutig lösbar (zu wenig Hinweise) oder ist in einem ungültigen Format!");
			}
		} catch (RuntimeException e) {
			System.err.println("Fehler: " + e.getMessage() + "\n");
		}

	}

	public void printGrid() {
		this.printGrid("new", "dez");
	}

	public void printGrid(String gridStatus) {
		this.printGrid(gridStatus, "dez");
	}

	public void printGrid(String gridStatus, String numberFormat) {
		int[][] printArr = new int[getSize()][getSize()];
		if (gridStatus.equals("new")) {
			printArr = getArr();
		} else if (gridStatus.equals("old")) {
			printArr = getOriginalArr();
		} else {
			System.out.println("Falsches Argument für printGrid --> nur \"new\" oder \"old\"");
			return;
		}

		String format = CustomMath.convertToNumberFormat(numberFormat);
		if (format.equals("")) {
			return;
		}

		for (int i = 0; i < getSize(); i++) {
			if (getBoxSizeHeight() != 0) {
				if (i != 0 && i % getBoxSizeHeight() == 0) {
					System.out.println();
					System.out.println();
				} else if (i != 0) {
					System.out.println();
				}
			} else if (i != 0) {
				System.out.println();
			}

			for (int j = 0; j < getSize(); j++) {
				if (getBoxSizeLength() != 0 && j != 0 && j % getBoxSizeLength() == 0) {
					System.out.print("| ");
				}
				String formattedNum = String.format("%" + format + "", printArr[i][j]);
				System.out.print(formattedNum + " ");
			}
		}
		System.out.println();
		System.out.println();
	}

}
