import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class puzzleSolver 
{
	public static void puzzleSolver(String[] args){
		double startTime = System.currentTimeMillis();
		int[] initialState = new int[9];
		int[] goalState = new int[9];
		int temp = 0;
		File file = null;
		while (temp < args.length) 
		{
			file = new File(args[temp]);
			temp++;
			BufferedReader buffered = null;
			try 
			{
				String line;
				buffered = new BufferedReader(new FileReader(file));
				line = buffered.readLine();
				int j = 0;
				for(Integer i = 0; i < initialState.length; i++)
				{
					initialState[i] = Integer.parseInt(line.substring(j, (j+1)));
					j += 2;
				}
				j = 0;
				line = buffered.readLine();
				for(int i = 0; i < goalState.length; i++)
				{
					goalState[i] = Integer.parseInt(line.substring(j, (j+1)));
					j += 2;
				}
			}
			catch (IOException exp) {}
			finally 
			{
				try 
				{
					if (buffered != null)
					{
						buffered.close();
					}	
				} catch (IOException exp) {}
			}
			puzzleFamily firstPuzzle = puzzleFamily.getOccurrence();
			int[] startTemp = new int[initialState.length - 1];
			int[] goal = new int[goalState.length - 1];
			int temp2 = 0;
			int temp3 = 0;
			for (int i = 0; i < initialState.length; i++) 
			{
				if (initialState[i] == 0) 
				{
					temp2 = 1;
				} else 
				{
					startTemp[i - temp2] = initialState[i];
				}
				if (goalState[i] == 0) 
				{
					temp3 = 1;
				} else 
				{
					goal[i - temp3] = goalState[i];
				}
			}

			if (puzzle.isSolution(startTemp, goal)) 
			{
				puzzle startPuzzle = new puzzle(initialState, goalState);
				firstPuzzle.setBeginning(startPuzzle.getWeight());
				firstPuzzle.addPuzzle(startPuzzle);
				puzzle afterPuzzle = firstPuzzle.next();
				boolean isComplete = false;
				while (isComplete == false) 
				{
					if (afterPuzzle.checkPuzzle()) 
					{
						isComplete = true;
						break;
					}
					afterPuzzle = firstPuzzle.next();
				}
				if (isComplete) 
				{
					int numberOfSteps = afterPuzzle.getAllMovements();
					String[] output = new String[numberOfSteps + 1];
					output[0] = afterPuzzle.getPrintablePuzzle();
					int temp4 = 1;
					int temp5 = 0;
					while (afterPuzzle.getParentPuzzle() != null) 
					{
						afterPuzzle = afterPuzzle.getParentPuzzle();
						output[temp4] = afterPuzzle.getPrintablePuzzle();
						temp4++;
					}
					System.out.println();
					for (int j = output.length - 1; j >= 0; j--) 
					{
						System.out.println("The puzzle after move " + temp5 + " is:");
						System.out.println();
						System.out.println(output[j]);
						System.out.println();
						if(output[j] != null)
						{
							temp5++;
						}
					}
					System.out.println("***************************************************");
					System.out.println("The total number of steps is: " + numberOfSteps);
				}
			}
		}
		double endTime = System.currentTimeMillis();
		double totalRuntime = endTime - startTime;
		System.out.println("***************************************************");
		System.out.println("The puzzle was solved in: " + totalRuntime + " Milliseconds.");
		System.out.println("***************************************************");
		System.out.println();
	
	}
	//Changed all this - Edwin
	
}
