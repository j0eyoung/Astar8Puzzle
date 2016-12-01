import java.util.LinkedList;
import java.util.ArrayList;

public class puzzleFamily 
{
	private int start = 0;
	private int current = 0;
	private static puzzleFamily firstP = null;	
	private ArrayList<LinkedList<puzzle>> puzzles = new ArrayList<LinkedList<puzzle>>(300);
	
	private puzzleFamily() {}
	
	/*Loops until it finds the next availble puzzle*/
	
	public puzzle next() 
	{
		while (puzzles.get(current).isEmpty()) 
		{
			current++;
		}
		puzzles.get(current).getFirst().showChildren();
		return puzzles.get(current).removeFirst();
	}
	

	/*Checks if the firstP already exists.If it doesn't, it intializes it.*/
	
	public static puzzleFamily getOccurrence() 
	{
		if (firstP == null) 
		{
			firstP = new puzzleFamily();
		}
		return firstP;
	}

	/*Add the puzzle to the list*/
	
	public void addPuzzle(puzzle puzzle) 
	{
		int f = puzzle.getWeight() + puzzle.getAllMovements();
		while (puzzles.size() <= f - start) 
		{
			puzzles.add(new LinkedList<puzzle>());
		}

		puzzles.get(f - start).add(puzzle);

	}
	
	/*Sets start location to passed Manhattan Distance value*/
	public void setBeginning(int manDistance) 
	{
		this.start = manDistance;
	}

}
