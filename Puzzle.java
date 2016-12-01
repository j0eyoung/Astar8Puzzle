public class Puzzle 
{
	private int[] puzzle;
	private int[] end;
	private Puzzle[] children = null;
	private int distance;// manhattan distance to goal // how many moves at a
						// minimum it takes to reach the goal.
	private int previous = -1;
	private int zeroLocation;
	private int allMovements;
	private Puzzle parentPuzzle;// needed for the output of the correct order

	public Puzzle(int[] puzzle, int[] end, int last, Puzzle parentPuzzle, int moves) 
	{
		this.parentPuzzle = parentPuzzle;
		this.previous = last;
		this.allMovements = moves;
		this.puzzle = new int[puzzle.length];
		this.end = new int[end.length];
		for (int i = 0; i < puzzle.length; i++) {
			this.puzzle[i] = puzzle[i];
			this.end[i] = end[i];
		}
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
		if (!hasChildren()) 
		{
			int[] neighbors = null;// = getNextTo()
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
