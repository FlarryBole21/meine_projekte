package sudoku_project;

public class SudokuSolver {

	public static void main(String[] args) {

		int[][] sudokuGrid_3 = { { 2, 0, 3 }, { 1, 0, 0 }, { 0, 0, 1 } };

		int[][] sudokuGrid_4 = { { 0, 0, 0, 3 }, { 0, 4, 0, 0 }, { 0, 0, 3, 2 }, { 0, 0, 0, 0 } };

		int[][] sudokuGrid_6 = { { 6, 2, 0, 5, 0, 3 }, { 0, 0, 0, 0, 0, 0 }, { 5, 0, 0, 0, 3, 0 }, { 0, 6, 0, 0, 2, 0 },
				{ 0, 0, 0, 3, 4, 6 }, { 3, 0, 6, 0, 0, 0 } };

		int[][] sudokuGrid_8 = { { 1, 8, 6, 0, 0, 0, 2, 7 }, { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 7, 0, 0, 3, 8 },
				{ 4, 0, 0, 3, 0, 0, 0, 0 }, { 6, 0, 0, 0, 0, 0, 1, 3 }, { 2, 0, 0, 5, 0, 0, 6, 0 },
				{ 0, 5, 0, 0, 2, 0, 7, 0 }, { 0, 0, 3, 0, 1, 4, 0, 0 } };

		int[][] sudokuGrid_9 = { { 0, 0, 0, 0, 0, 0, 4, 0, 2 }, { 6, 0, 0, 7, 0, 0, 0, 1, 0 },
				{ 5, 0, 0, 0, 2, 0, 7, 0, 0 }, { 0, 3, 0, 0, 1, 0, 0, 4, 8 }, { 0, 4, 0, 2, 0, 0, 0, 0, 0 },
				{ 0, 8, 0, 9, 0, 0, 0, 5, 0 }, { 0, 5, 0, 0, 0, 3, 0, 7, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 9, 0, 0, 1, 0, 0, 0, 8, 0 } };

		int[][] sudokuGrid_12 = { { 0, 0, 1, 0, 6, 0, 2, 0, 10, 7, 0, 0 }, { 6, 2, 0, 5, 11, 0, 0, 0, 1, 0, 0, 0 },
				{ 0, 8, 0, 0, 0, 0, 12, 3, 11, 0, 6, 2 }, { 10, 9, 7, 0, 2, 0, 6, 0, 0, 11, 5, 0 },
				{ 0, 5, 0, 0, 0, 0, 0, 0, 0, 3, 7, 12 }, { 3, 11, 0, 0, 0, 0, 0, 7, 0, 0, 0, 9 },
				{ 5, 7, 11, 0, 4, 0, 0, 0, 0, 0, 0, 1 }, { 8, 0, 0, 0, 0, 0, 0, 10, 0, 9, 0, 0 },
				{ 0, 4, 0, 0, 9, 7, 0, 0, 0, 6, 0, 0 }, { 0, 1, 12, 0, 0, 0, 0, 0, 0, 0, 0, 11 },
				{ 4, 0, 0, 0, 0, 0, 0, 2, 0, 12, 0, 0 }, { 0, 0, 0, 0, 5, 12, 0, 0, 7, 1, 2, 6 } };

		int[][] sudokuGrid_16 = { { 0, 0, 0, 0, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 0, 0, 0 },
				{ 0, 6, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0, 0, 10, 11, 12 },
				{ 0, 0, 0, 0, 0, 10, 11, 12, 0, 0, 7, 8, 1, 2, 3, 4 },
				{ 3, 0, 0, 0, 7, 8, 5, 6, 11, 12, 9, 0, 15, 16, 13, 14 },
				{ 0, 16, 0, 0, 0, 0, 0, 10, 0, 4, 1, 0, 7, 0, 0, 6 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 15, 16, 0, 0, 0, 0, 9, 10 },
				{ 11, 12, 0, 0, 0, 16, 13, 14, 7, 0, 0, 6, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 0, 8, 7, 10, 0, 12, 0, 14, 0, 0, 0 },
				{ 0, 0, 12, 11, 0, 0, 16, 0, 0, 0, 4, 0, 0, 0, 0, 0 },
				{ 0, 5, 8, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 15, 16 },
				{ 0, 0, 0, 0, 10, 0, 12, 11, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 0, 0, 0, 6, 5, 0, 0, 0, 0, 0, 0, 2, 1 },
				{ 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0 },
				{ 0, 0, 0, 0, 4, 0, 2, 1, 0, 0, 0, 0, 0, 13, 14, 0 },
				{ 0, 15, 0, 0, 0, 0, 0, 9, 0, 0, 0, 5, 0, 0, 0, 0 } };

		int[][] sudokuGrid_18 = { { 10, 5, 11, 2, 0, 0, 8, 0, 3, 0, 7, 12, 18, 4, 9, 6, 15, 0 },
				{ 0, 9, 0, 0, 14, 18, 0, 0, 0, 0, 13, 0, 0, 0, 8, 17, 0, 16 },
				{ 0, 0, 0, 3, 0, 0, 9, 0, 15, 14, 0, 0, 13, 10, 0, 11, 0, 1 },
				{ 8, 0, 0, 0, 0, 12, 0, 0, 14, 18, 4, 0, 0, 0, 0, 0, 1, 13 },
				{ 0, 6, 15, 14, 0, 4, 0, 2, 0, 0, 10, 5, 0, 0, 17, 0, 16, 0 },
				{ 5, 11, 0, 1, 0, 10, 0, 0, 16, 7, 12, 0, 4, 0, 6, 15, 14, 18 },
				{ 0, 0, 9, 0, 0, 0, 10, 0, 0, 2, 1, 0, 16, 7, 0, 0, 0, 0 },
				{ 0, 0, 5, 0, 2, 0, 12, 0, 17, 3, 0, 7, 14, 0, 0, 0, 6, 0 },
				{ 0, 12, 8, 0, 0, 16, 4, 9, 0, 0, 0, 0, 1, 13, 10, 0, 11, 2 },
				{ 0, 7, 0, 0, 17, 0, 0, 0, 0, 6, 0, 0, 2, 0, 0, 0, 5, 11 },
				{ 0, 0, 4, 0, 0, 0, 13, 10, 5, 0, 2, 1, 3, 16, 7, 12, 0, 17 },
				{ 0, 13, 10, 5, 11, 2, 0, 0, 0, 0, 0, 16, 15, 0, 0, 0, 9, 6 },
				{ 15, 0, 0, 0, 0, 6, 0, 13, 10, 5, 11, 0, 17, 3, 16, 7, 0, 8 },
				{ 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 17, 0, 6, 15, 14, 18, 0, 9 },
				{ 3, 16, 7, 12, 8, 0, 14, 18, 0, 0, 6, 15, 0, 2, 0, 13, 0, 0 },
				{ 0, 15, 0, 18, 0, 9, 2, 1, 13, 10, 0, 11, 0, 0, 3, 16, 7, 0 },
				{ 0, 0, 0, 13, 10, 5, 0, 16, 7, 12, 0, 0, 0, 6, 15, 14, 0, 0 },
				{ 0, 3, 16, 7, 0, 0, 0, 0, 0, 0, 9, 6, 5, 0, 0, 0, 13, 0 } };

		Sudoku cs1 = new Sudoku(sudokuGrid_16);

		cs1.printGrid();
		cs1.solve();
		cs1.printGrid();

	}

}