import java.util.LinkedList;
import java.util.ArrayList;



public class PuzzleGroup 
{
	/*Start location = 0*/
	private int start = 0;

	/*Sets start location to passed Manhattan Distance value*/
	public void setBeginning(int manDistance) 
	{
		this.start = manDistance;
	}

	private int current = 0;
	private static PuzzleGroup firstP = null;
	private ArrayList<LinkedList<Puzzle>> puzzles = new ArrayList<LinkedList<Puzzle>>(300);

	
	private PuzzleGroup() {}
	
	
	/*Loops until it finds the next availble puzzle then it ....*/
	public Puzzle next() 
	{
		while (puzzles.get(current).isEmpty()) 
		{
			current++;
		}
		puzzles.get(current).getFirst().showChildren();
		return puzzles.get(current).removeFirst();
	}

	
	/*Add the puzzle to the list*/
	public void addPuzzle(Puzzle puzzle) 
	{
		int f = puzzle.getWeight() + puzzle.getAllMovements();
		while (puzzles.size() <= f - start) 
		{
			puzzles.add(new LinkedList<Puzzle>());
		}

		puzzles.get(f - start).add(puzzle);

	}

	
	
	
	/*Checks if the firstP already exists.If it doesn't, it intializes it.*/
	public static PuzzleGroup getOccurrence() 
	{
		if (firstP == null) 
		{
			firstP = new PuzzleGroup();
		}
		return firstP;
	}

}
