import java.util.LinkedList;
import java.util.ArrayList;

public class PuzzleGroup 
{
	private int start = 0;
	private int current = 0;
	private static PuzzleGroup firstP = null;	
	private ArrayList<LinkedList<Puzzle>> puzzles = new ArrayList<LinkedList<Puzzle>>(300);
	
	private PuzzleGroup() {}
	
	public Puzzle next() 
	{
		while (puzzles.get(current).isEmpty()) 
		{
			current++;
		}
		puzzles.get(current).getFirst().showChildren();
		return puzzles.get(current).removeFirst();
	}
	

	public static PuzzleGroup getOccurrence() 
	{
		if (firstP == null) 
		{
			firstP = new PuzzleGroup();
		}
		return firstP;
	}

	public void addPuzzle(Puzzle puzzle) 
	{
		int f = puzzle.getWeight() + puzzle.getAllMovements();
		while (puzzles.size() <= f - start) 
		{
			puzzles.add(new LinkedList<Puzzle>());
		}

		puzzles.get(f - start).add(puzzle);

	}
	
	public void setBeginning(int manDistance) 
	{
		this.start = manDistance;
	}

}
