import java.util.Arrays;

public class SudokuValidator implements Runnable{
	// Variable for the puzzle board 9x9
	private int[][] sudokuPuzzle = new int[9][9] ;
	
	// Constructor
	public SudokuValidator() {
		
	}
	
	// Constructor with puzzle parameter
	public SudokuValidator(int[][] puzzle) {
	// Copying given 2D array to the private 2d array
		for(int i = 0; i < puzzle.length; i++) {
			for(int j = 0; j < puzzle[i].length; j++){
	// Checking if any number inside the given array are outside the range 1-9
				if(puzzle[i][j]<=0||puzzle[i][j]>9) {
				System.out.println("Index ["+i+"]["+j+"] is out of range.\nMust be between 1 and 9\n");
				}
				this.sudokuPuzzle[i][j] = puzzle[i][j];
			}
		}
	
	}
	
	// Checking if the 3x3 grids are valid
	public boolean gridCheck() {
		boolean[] tempArr = new boolean[10];
	// Switching through each grid segment
			for(int segRow = 0; segRow < 9; segRow += 3) {
				for(int segCol = 0; segCol < 9; segCol += 3) {
					Arrays.fill(tempArr, false);
	// Checking each number of each grid
					for(int gridRow = 0; gridRow < 3; gridRow++) {
						for(int gridCol = 0; gridCol < 3; gridCol++) {
	// Grab number from the grid segment within the puzzle
							int num = this.sudokuPuzzle[segRow+gridRow][segCol+gridCol];
	// If the selected number shows up again return false 
							if(tempArr[num]) {
								return false;
							}
							tempArr[num] = true;
						}	
					}
				}	
			}
		return true;
	}
	
	// Checking if the 9 rows are valid
	public boolean rowCheck() {
	//check entire board	
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
	// grab number from board
				int temp = this.sudokuPuzzle[row][col];
				int occur = 0;
	// check if number repeats within its row
				for(int i = 0; i <9; i++) {
					if(temp == this.sudokuPuzzle[row][i]  ) {
						occur++;
	// number should only appear once, if its twice we have a duplicate
							if(occur > 1) {	
								return false;
							}
					}
				}
			}
		}
		return true;
	}
	
	// Checking if the 9 columns are valid
	public boolean columnCheck() {
	//check entire board	
		for(int col = 0; col < 9; col++) {
			for(int row = 0; row < 9; row++) {
	// grab number from board
				int temp = this.sudokuPuzzle[row][col];
				int occur = 0;
	// check if number repeats within its row
				for(int i = 0; i <9; i++) {
					if(temp == this.sudokuPuzzle[i][col]  ) {
						occur++;
	// number should only appear once, if its twice we have a duplicate
							if(occur > 1) {	
								return false;
							}
					}
				}
			}
		}
		return true;
	}
	
	// run method for threads
	public void run() {
		String threadName = Thread.currentThread().getName();
		
			if(threadName.equals("grid")) {
				System.out.println("Grid results: " + this.gridCheck());
			} else if(threadName.equals("row")) {
				System.out.println("Row results: " + this.rowCheck());
			} else if(threadName.equals("column")) {
				System.out.println("Column results: " + this.columnCheck());
			}	
	}
	
	public static void main(String[] args) {
	// Sudoku board as a 2d array 
	 		int[][] puzzleInput = {{6, 2, 4, 5, 3, 9, 1, 8, 7},
						      {5, 1, 9, 7, 2, 8, 6, 3, 4},
						      {8, 3, 7, 6, 1, 4, 2, 9, 5},
						      {1, 4, 3, 8, 6, 5, 7, 2, 9},
						      {9, 5, 8, 2, 4, 7, 3, 6, 1},
						      {7, 6, 2, 3, 9, 1, 4, 5, 8},
						      {3, 7, 1, 9, 5, 6, 8, 4, 2},
						      {4, 9, 6, 1, 8, 2, 5, 7, 3},
						      {2, 8, 5, 4, 7, 3, 9, 1, 6}};
	/*
	//row test case
	
		int[][] puzzleInput = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9},
			      {1, 2, 3, 4, 5, 6, 7, 8, 9}};
			      
	//column test case
	 
		int[][] puzzleInput = {{9, 9, 9, 9, 9, 9, 9, 9, 9},
			      {8, 8, 8, 8, 8, 8, 8, 8, 8},
			      {7, 7, 7, 7, 7, 7, 7, 7, 7},
			      {6, 6, 6, 6, 6, 6, 6, 6, 6},
			      {5, 5, 5, 5, 5, 5, 5, 5, 5},
			      {4, 4, 4, 4, 4, 4, 4, 4, 4},
			      {3, 3, 3, 3, 3, 3, 3, 3, 3},
			      {2, 2, 2, 2, 2, 2, 2, 2, 2},
			      {1, 1, 1, 1, 1, 1, 1, 1, 1}};
			         
			      
	   //grid test case
	 int[][] puzzleInput = {{1, 2, 3, 1, 2, 3, 1, 2, 3},
						      {4, 5, 6, 4, 5, 6, 4, 5, 6},
						      {7, 8, 9, 7, 8, 9, 7, 8, 9},
						      {1, 2, 3, 1, 2, 3, 1, 2, 3},
						      {4, 5, 6, 4, 5, 6, 4, 5, 6},
						      {7, 8, 9, 7, 8, 9, 7, 8, 9},
						      {1, 2, 3, 1, 2, 3, 1, 2, 3},
						      {4, 5, 6, 4, 5, 6, 4, 5, 6},
						      {7, 8, 9, 7, 8, 9, 7, 8, 9}};
			 */
		SudokuValidator sudokuValidator = new SudokuValidator(puzzleInput);
		
		Thread gridThread = new Thread(sudokuValidator,"grid");
		Thread rowThread = new Thread(sudokuValidator,"row");
		Thread columnThread = new Thread(sudokuValidator,"column");
		
		gridThread.start();
		rowThread.start();
		columnThread.start();
		
		}	
}
