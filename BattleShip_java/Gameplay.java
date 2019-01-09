import java.util.*;
/*
This class plays games 

*/
class Gameplay
{
	GameHelper help = new GameHelper();	//Object of class Gamehelper
	int ships = help.ships;	//Total number of ships both players have
	int gridlength = help.gridlength;	//Grid length and breadth
	ArrayList<String> placementshumanship = new ArrayList<String>();
	ArrayList<String> placementscomputership = new ArrayList<String>();
	ArrayList<String> tried = new ArrayList<String>();
	public void startGame()	//Begining message
	{
		System.out.println("Welcom to World War 3. A new evil has arised :Computer AI. During war all our logistics, communication , radars, sonars and satellites have been dewstroyed. We and the last of AI has  " + help.ships + " in a 10 X 10 grid ocean. It is now totally up to you to defeat the AI for once and for all. The task should you choose to accept it is to sink the AI in least possible guesses.\nAll of our hits will be visible but due to lack of communiction all of the AI bombing sites are unknown.");
		System.out.println("Let the WAR begin...");
	}
	public int[] conversionToPlot (String given)//Function to convert entries to make them plottable
	{
		int[] a = new int[2];
			a[0] = (int)given.charAt(0) - 97;
			a[1] = (int)given.charAt(1) - 48;
		return a;
	}


	void showGrid()	//Show Grid
	{
		String[][] grid = new String[gridlength][gridlength];
		for(int i = 0; i<gridlength; i++)
		{
			for (int j = 0; j<gridlength; j++)
			{
				for (int k = 0; k < placementshumanship.size(); k++)
				{
					int[] temp = {i , j};
					if( Arrays.equals(temp,conversionToPlot(placementshumanship.get(k))))
					{
						grid[i][j] = "  #  ";
					}// End IF for human
				}
				for (int k = 0; k<tried.size(); k++)
				{
					int[] temp = {i , j};
					if( Arrays.equals(temp,conversionToPlot(tried.get(k))))
					{
						grid[i][j] = "  @  ";
					}// End IF for tried
				}
				try {
					if (grid[i][j].equals(null))
					{
						System.out.print(" . ");
					}
				}// End try
				catch(Exception e)
				{
					grid[i][j] = "  .  ";
				}	//End Catch 
				System.out.print(grid[i][j]);

								
			}
			System.out.println("");
		}
		System.out.println("Ships left with computer : " + placementscomputership.size());
		System.out.println("Ships left with human : " + placementshumanship.size());
	}//Implemented


	void playGame( )	//Sets up game for bombing
	{
		GameHelper help = new GameHelper();
		System.out.print("Enter coordinates to be bombed : ");
		Scanner inpt = new Scanner(System.in);
		String place = inpt.next();
		int[] state = help.bombing(placementshumanship, placementscomputership, place);
		if(state[0] == -1)
		{
			System.out.println("Hit Self");
			tried.add(place);
			placementshumanship.remove(state[1]);
		}
		else if (state[0] == 0)
		{
			System.out.println("Miss.");
			tried.add(place);
		}
		else if (state[0] == 1)
		{
			System.out.println("Hit Confirmed.");
			tried.add(place);
			placementscomputership.remove(state[1]);
		}
		else
		{
			System.out.println("Wrong input of coordinates.");
		}
		state = help.computerbombing(placementshumanship, placementscomputership);
		if(state[0] == -1)
		{
			System.out.println("AI hit Self.");
			//computerhassunk.add(state[1]);
			placementscomputership.remove(state[1]);

		}
		else if (state[0] == 0)
		{
			System.out.println("Miss.");
		}
		else
		{
			System.out.println("Hit Confirmed.");
			placementshumanship.remove(state[1]);
		}
	}
	void endGame()	//Decides who is  the winner by seeing  number of  ships left; the  one with zero ship looses
	{
		if(placementscomputership.size()== 0)
		{
			System.out.println("Computer has been defeted.");

		}	//Number of computer ships are 0 i.e. computer has been defeted; Comgratulations
		else
		{
			System.out.println("Computer Win. Humans have been destroyed.");
		}	//Humanity LOST
	}

	 public static void main(String[] args)
	 {
	 	Gameplay game = new Gameplay();
	 	GameHelper help = new GameHelper();
	 	int playOn = 1;
	 	game.placementshumanship = help.playerShips();	//PLACEMENTS  OF human ships
	 	game.placementscomputership = help.computerShips(game.placementshumanship);	//Placements of computer
	 	 	game.startGame();
	 	while(game.placementscomputership.size() > 0 && game.placementshumanship.size() > 0)
	 	{
	 		System.out.println(game.placementscomputership);
	 		game.showGrid();
	 		game.playGame();
	 	}
	 	game.endGame();
	 }
}