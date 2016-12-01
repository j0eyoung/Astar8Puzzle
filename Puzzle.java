public class Puzzle 
{
	
	/*Creating int arrays to hold the inputs for the puzzle and the expected end state.*/
	private int[] puzzle;
	private int[] end;
	
	/*Create an array of puzzles that will hold the children*/
	private Puzzle[] children = null;
	
	/*This is the manhattan distance to the goal state and the minimum number of moves it
	 will take to reach the expected goal state*/
	private int distance;
	
	private int previous = -1;
	private int zeroLocation;
	private int allMovements;
	
	/*This puzzle will holds the correct order for display*/
	private Puzzle parentPuzzle;
	
	
	
	public Puzzle(int[] puzzle, int[] end, int last, Puzzle parentPuzzle, int moves) 
	{
		/*Creates instance of the puzzle object*/
		this.parentPuzzle = parentPuzzle;
		this.previous = last;
		this.allMovements = moves;
		this.puzzle = new int[puzzle.length];
		this.end = new int[end.length];
		
		/*For loop supplies values to int arrays from inputs*/
		for (int i = 0; i < puzzle.length; i++) {
			this.puzzle[i] = puzzle[i];
			this.end[i] = end[i];
		}
		
		/*Manhattan is currently set to 0*/
		distance = 0;
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] != 0) {
				for (int j = 0; j < end.length; j++) {
					if (puzzle[i] == end[j]) {
						distance += Math.abs(i % 3 - j % 3) + Math.abs(i / 3 - j / 3);
						break;
					}
				}
			} else {
				zeroLocation = i;
			}
		}
	}

	
	
	public Puzzle(int[] puzzle, int[] end) 
	{
		this.allMovements = 0;
		this.puzzle = new int[puzzle.length];
		this.end = new int[end.length];
		for (int i = 0; i < puzzle.length; i++) 
		{
			this.puzzle[i] = puzzle[i];
			this.end[i] = end[i];
		}
		distance = 0;
		for (int i = 0; i < puzzle.length; i++) 
		{
			if (puzzle[i] != 0) {
				for (int j = 0; j < end.length; j++) 
				{
					if (puzzle[i] == end[j]) 
					{
						distance += Math.abs(i % 3 - j % 3) + Math.abs(i / 3 - j / 3);
						break;
					}
				}
			} else 
			{
				zeroLocation = i;
			}
		}
	}

	
	
	
	public int[] getNeighborPiece(int x) 
	{
		int[] a = null;
		
		/*Switch Cases are used to determine which pieces are used
		 *to determine the current neighbors while in the current state. We
		 *pass an integer value to which set of values to assign to a and return it.
		 */
		
		switch (x) 
		{
			case 0:
				a = new int[] { 1, 3 };
				return a;
			case 1:
				a = new int[] { 0, 2, 4 };
				return a;
			case 2:
				a = new int[] { 1, 5 };
				return a;
			case 3:
				a = new int[] { 0, 4, 6 };
				return a;
			case 4:
				a = new int[] { 1, 3, 5, 7 };
				return a;
			case 5:
				a = new int[] { 2, 4, 8 };
				return a;
			case 6:
				a = new int[] { 3, 7 };
				return a;
			case 7:
				a = new int[] { 4, 6, 8 };
				return a;
			case 8:
				a = new int[] { 5, 7 };
				return a;
		}
		return a;
	}
	
	
	
	

	public Puzzle[] showChildren() 
	{
		/*First check if there are any exisiting children and if there are none,
	 	*get the neighbor pieces from the getNeighborPiece function and assign it to neighbors int array. 
		*
	 	*If children exists then assign neighbors to a temporary array and check to see if any of the
	 	*neighbors have been previously visited. Add only the neighbors that have not been previously visited.
	 	*/
		
		if (!hasChildren()) 
		{
			int[] neighbors = null;
			for (int i = 0; i < puzzle.length; i++) 
			{
				if (puzzle[i] == 0) {
					if (previous == -1) 
					{
						neighbors = getNeighborPiece(i);
					} else {
						int[] temp = getNeighborPiece(i);
						int k = 0;
						neighbors = new int[temp.length - 1];
						for (int j = 0; j < temp.length; j++) 
						{
							if (temp[j] != previous) 
							{
								neighbors[k] = temp[j];
								k++;
							}
						}
					}
					break;
				}
			}
			
			children = new Puzzle[neighbors.length];
			PuzzleGroup puzzleGroup = PuzzleGroup.getOccurrence();
			
			/*
			* The first loop iterates the index of the values in the
			* neighbors array while the second loop interates the index of the new
			* array. Then check if j is the same value of the the neighbor value held at index 
			* i. 
			* If it matches then set the newly created array's value at index j to 0. 
			*
			* If j is the zero location then set the array's value to the puzzle value at the
			* index of neighbors[i]'s value.
			*
			* If none of the above then, set the array at index j value to the current puzzle value
			* at index j.
			*/
			
			
			
			for (int i = 0; i < children.length; i++) 
			{
				int[] array = new int[puzzle.length];
				for (int j = 0; j < array.length; j++) 
				{
					if (j == neighbors[i]) 
					{
						array[j] = 0;
					} else if (j == zeroLocation) 
					{
						array[j] = puzzle[neighbors[i]];
					} else 
					{
						array[j] = puzzle[j];
					}
				}
				children[i] = new Puzzle(array, end, zeroLocation, this, allMovements + 1);
				puzzleGroup.addPuzzle(children[i]);

			}

		}
		return children;
	}
	
	/*Return methods*/
	
	public int getAllMovements() 
	{
		return allMovements;
	}

	public int getWeight() 
	{
		return distance;
	}

	public Puzzle getParentPuzzle() 
	{
		return parentPuzzle;
	}

	public String getPuzzle() 
	{
		String puzzleString = "" + puzzle[0] + " " + puzzle[1] + " " + puzzle[2] + "\n" + puzzle[3] + " " + puzzle[4] + " "
				+ puzzle[5] + "\n" + puzzle[6] + " " + puzzle[7] + " " + puzzle[8] + "\n";
		return puzzleString;
	}
	
	
	/*Checks to the puzzle to see for values in puzzle array
	 * that do not equal the expected goal state values and returns
	 *true or false.
	*/
	public boolean checkPuzzle() 
	{
		for (int i = 0; i < puzzle.length; i++) 
		{
			if (puzzle[i] != end[i]) 
			{
				return false;
			}
		}
		return true;
	}

	
	
	/*Checks to see if there exists any children and returns a boolean value*/
	public boolean hasChildren() 
	{
		if (children == null) 
		{
			return false;
		} else 
		{
			return true;
		}
	}
	
	
	/*****/
	public static boolean isSolution(int[] puzzle, int[] goal) 
	{
		int puzzleInversions = 0, goalInversions = 0;

		for (int i = 0; i < puzzle.length; i++) {
			for (int j = i + 1; j < goal.length; j++) 
			{
				if (puzzle[j] > puzzle[i]) 
				{
					puzzleInversions++;
				}
				if (goal[j] > goal[i]) 
				{
					goalInversions++;
				}
			}
		}
		if (puzzleInversions % 2 == 1 && goalInversions % 2 == 1 || puzzleInversions % 2 == 0 && goalInversions % 2 == 0) 
		{
			return true;
		} else 
		{
			System.out.println("no solution");
			return false;
		}
	}
}
