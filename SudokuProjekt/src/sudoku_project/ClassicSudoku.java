package sudoku_project;

public class ClassicSudoku extends SquareSudoku {

	public ClassicSudoku(int[][] arr) {
		super(arr);
		setBoxSize();
	}

	@Override
	public void setBoxSize() {
		if (getSize() == 9) {
			int number = (int) Math.sqrt(getSize());
			super.setBoxSize(number, number);
		} else {
			super.setBoxSize(0, 0);
		}
	}

	@Override
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
						"Sudoku ist nicht eindeutig lösbar (zu wenig Hinweise) oder ist in einem ungültigen Format,"
								+ "nur klassisches Sudoku erlaubt (9x9)");
			}
		} catch (RuntimeException e) {

			System.err.println("Fehler: " + e.getMessage() + "\n");
		}

		return;

	}
}
