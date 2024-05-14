package sudoku_project;

public abstract class Grid {

	private int[][] originalArr;
	private int[][] arr;
	private int size;

	public Grid(int[][] arr) {
		this.size = arr.length;
		this.arr = new int[getSize()][getSize()];
		this.originalArr = new int[getSize()][getSize()];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.arr[i][j] = arr[i][j];
				this.originalArr[i][j] = arr[i][j];
			}
		}
		
	}

	public int[][] getArr() {
		return arr;
	}

	public void setArr(int[][] arr) {
		this.arr = arr;
		setSize(arr.length);
	}

	public int getSize() {
		return size;
	}

	private void setSize(int size) {
		this.size = size;
	}

	public int[][] getOriginalArr() {
		return originalArr;
	}

	public void setOriginalArr(int[][] originalArr) {
		this.originalArr = originalArr;
	}

}
