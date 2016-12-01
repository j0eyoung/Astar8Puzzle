import java.util.LinkedList;
import java.util.ArrayList;

public class puzzleFamily 
{
	private int start = 0;
	private int current = 0;
	private static puzzleFamily firstP = null;	
	private ArrayList<LinkedList<puzzle>> puzzles = new ArrayList<LinkedList<puzzle>>(300);
	
	private puzzleFamily() {}
	
	public puzzle next() 
	{
		while (puzzles.get(current).isEmpty()) 
		{
			current++;
		}
		puzzles.get(current).getFirst().showChildren();
		return puzzles.get(current).removeFirst();
	}
	

	public static puzzleFamily getOccurrence() 
	{
		if (firstP == null) 
		{
			firstP = new puzzleFamily();
		}
		return firstP;
	}

	public void addPuzzle(puzzle puzzle) 
	{
		int f = puzzle.getWeight() + puzzle.getAllMovements();
		while (puzzles.size() <= f - start) 
		{
			puzzles.add(new LinkedList<puzzle>());
		}

		puzzles.get(f - start).add(puzzle);

	}
	
	public void setBeginning(int manDistance) 
	{
		this.start = manDistance;
	}

}
