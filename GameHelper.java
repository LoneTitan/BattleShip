import java.util.*;
public class GameHelper
/*
This class contains methods that will assist in making out game
Methods invoving are :- 
ArrayList<String> playerShips() : Enters player ships coordinates onto map
ArrayList<String> computerShips() : ENters computer ships  using random function
int[] bombing(ArrayList<String> placementshuman,ArrayList<String>  placementscomputer, String coord) : Bombs the coordinates entered by the player  ; Arguments required placement of human ships, placement of computer ships, coordinates entered by human
int[] computerbombing(ArrayList<String> placamentshuman, ArrayList<String> placementscomputer ) : Using random function bombs on a map which is not reflected on map dur to  logistic issues at time of world war 2
*/
{
	int ships = 5;	//Number of ships each player has.
	int gridlength = 10;
	public ArrayList<String> playerShips()	//Player ships coordinates enrty
	{
		ArrayList<String> list = new ArrayList<String>();
		int numberofships = ships;
		while(numberofships>0)
		{
			System.out.println(numberofships);
			System.out.println("Enter coordinates of Your ship placement in format (a-j)(0-9)");
			Scanner input = new Scanner(System.in);
			String position = input.next();
			if( list.indexOf(position) < 0 && position.length() == 2 && (int)position.charAt(1) < (int)'0' + 10 && (int)position.charAt(1) >= (int)'0' &&  position.charAt(0) < 'k' && (int)position.charAt(0) >= 'a')
			{
				list.add(position);
				numberofships--;	
			}
			else
			{
				System.out.println("These inputs alreasy exists or are wrong.");
			}	//End IF to check validity of data if present in previously in the array
	
		}	//End of while
		return list;
	}


	public ArrayList<String> computerShips(ArrayList<String> playerlocation)
	{
		ArrayList<String> list = new ArrayList<String>();
		int numberofships = ships;
		while(numberofships>0)
		{			
				int x = (int)((Math.random())*10 + 1);
				int y = (int)((Math.random())*10);
				//System.out.println(y);	//End of while loop of entring data
			char x1 = (char)(x + 96);
			String finalloc = "";
			finalloc = (x1) + Integer.toString(y);
			if (list.indexOf(finalloc) == -1 && playerlocation.indexOf(finalloc ) == -1)
			{
				list.add(finalloc);
				numberofships--;
			}	//End of If loop
		}	// End of while loop
		return list;
	}



	public int[] bombing(ArrayList<String> placementshuman,ArrayList<String>  placementscomputer, String coord)	//Return 1 if bombed, else 
	{
		int check = 0;
		int rm = -1;
		int[] ret = new int[2];
		int validity =  0;
		if ( coord.length() == 2 && (int)coord.charAt(1) < (int)'0' + 10 && (int)coord.charAt(1) >= (int)'0' &&  coord.charAt(0) < 'k' && (int)coord.charAt(0) >= 'a')
		{
			validity = 1;
		}
		if (validity == 0)
		{
			ret[0] = -2;
			ret[1] = -2;
		}
		else
		{
			if(placementscomputer.indexOf(coord) >= 0)	//Hit Confirmed
			{	
			rm = placementscomputer.indexOf(coord);
			System.out.println("Bombing enemy.");
			check = 1;
			ret[0] = check;
			ret[1] = rm;
			}
		else if(placementshuman.indexOf(coord) >= 0)	//Hit Confirmed self
		{
			rm = placementshuman.indexOf(coord);
			//status = "self hit";
			check = -1;
			ret[0] = check;
			ret[1] = rm;
		}
		else{
			ret[0] = 0;
			ret[1] = -1;
		}
		}
		return ret;	//Ret[0] 1 = computer hit -1 = self hit 0 = miss ret[1] = coordinats to remove from initial pile
	}


	public int[] computerbombing(ArrayList<String> placamentshuman, ArrayList<String> placementscomputer )
	{

		int x = 2;	//Initilaise x = 1 so as to enter while loop
		String finalloc = "";
		int y = 0;
		x = (int)((Math.random())*10) + 1 ;
		y = (int)((Math.random())*10);
		char x1 = (char)(x + 96);
		finalloc = "";
		finalloc = (x1) + Integer.toString(y);
		System.out.println(finalloc);
		//End of while loop of entring data
		
		return bombing(placementscomputer, placamentshuman, finalloc);


	}

}