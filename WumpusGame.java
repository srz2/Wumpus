import java.util.*;
import java.util.Scanner;

public class WumpusGame
{
	public int NUMROOMS = 10;

	Scanner scan = new Scanner(System.in);
	
	ArrayList<Room> outter = new ArrayList<Room>();
	ArrayList<Room> inner = new ArrayList<Room>();

	// The index for where each item is
	Random rand = new Random();
	int i_Wumpus = -1;
	int i_Bats = -1;
	int i_Pit = -1;
	int i_Exit = -1;

	// Reference to each room
	Room r_Current = null;
	Room r_Previous = null;
	Room r_Wumpus = null;
	Room r_Bats = null;
	Room r_Pit = null;
	Room r_Exit = null;

	boolean IsGameOver = false;

	public boolean DisplayHelp = false;

	/// Setup the game
	public WumpusGame()
	{
		// Generate random and unique indexes for special rooms
		ArrayList<Integer> itemIndexes = new ArrayList<Integer>();
		for(int c = 0; c < 4; c++)
		{
			int value = -1;
			do
			{
				value = rand.nextInt(NUMROOMS * 2);
			}while(itemIndexes.contains(value));
			itemIndexes.add(value);
		}

		i_Wumpus = itemIndexes.get(0);
		i_Exit = itemIndexes.get(1);
		i_Bats = itemIndexes.get(2);
		i_Pit = itemIndexes.get(3);

		// Create rooms and place items inside
		for(int c = 0; c < NUMROOMS * 2; c++)
		{
			Room newRoom = null;
			if(c == i_Wumpus)
			{
				newRoom = new Room(c, RoomItem.Wumpus);
				r_Wumpus = newRoom;
			}
			else if(c == i_Exit)
			{
				newRoom = new Room(c, RoomItem.Exit);
				r_Exit = newRoom;
			}
			else if(c == i_Pit)
			{
				newRoom = new Room(c, RoomItem.Pit);
				r_Pit = newRoom;
			}
			else if(c == i_Bats)
			{
				newRoom = new Room(c, RoomItem.Bats);
				r_Bats = newRoom;
			}
			else
			{
				newRoom = new Room(c, RoomItem.None);
			}

			{
				outter.add(newRoom);
				// System.out.println("Creating outter at " + c);				
				// displayRoom(outter.get(c));
			}
			else
			{
				inner.add(newRoom);
				// System.out.println("Creating inner at " + c);
				// displayRoom(inner.get(c -  10));
			}
		}

		// Randomly get an EMPTY starting room
		int value = - 1;
		do
		{
			value = rand.nextInt(NUMROOMS * 2);
		}while(value == r_Wumpus.getIndex() || value == r_Exit.getIndex() || value == r_Pit.getIndex() || value == r_Bats.getIndex());
		assignNewRoom(value);
	}

	/// Assign a new room based on the index 0-9 is outter hallways, 10-19 is inner hallways
	private void assignNewRoom(int index)
	{
		{
			r_Current = outter.get(index);
		}
		else
		{
			r_Current = inner.get(index - 10);
		}
	}

	// Display the room you are in and the item inside
	private void displayRoom(Room room)
	{
		if(room.getRoomItem() != RoomItem.None)
		{
			System.out.println("You are currently in: " + room.getHallway() + " " + (room.getIndex() + 1) + " is has " + room.getRoomItem().toString());
		}
		else
		{
			System.out.println("You are currently in: " + room.getHallway() + " " + (room.getIndex() + 1));
		}
	}

	/// Show the Wumpus with a gameover message
	private void showWumpus()
	{
		System.out.println("  ,/         \\.");
		System.out.println(" ((           ))");
		System.out.println("  \\`.       ,'/");
		System.out.println("   )')     (`(");
		System.out.println(" ,'`/       \\,`.");
		System.out.println("(`-(         )-')");
		System.out.println(" \\-'\\,-'\"`-./`-/");
		System.out.println("  \\-')     (`-/\"");
		System.out.println("  /`'       `'\"");
		System.out.println(" (  _       _  )");
		System.out.println(" | ( \\     / ) |");
		System.out.println(" |  `.\\   /,'  |");
		System.out.println(" |    `\\ /'    |");
		System.out.println(" (             )");
		System.out.println("  \\           /");
		System.out.println("   \\         /");
		System.out.println("    `.     ,'");
		System.out.println("      `-.-'");
		System.out.println("****Oh no what is that?!****");
		System.out.println("You have been devoured by the Wumpus");
		System.out.println("Game Over.");
	}

	/// Print out the splash screen
	private void Splash()
	{
		System.out.println("");
		System.out.println("          Welcome to Wump the Wumpus");
		System.out.println("          ---Do you feel...lucky?---");
		System.out.println("");
		System.out.println("You have been placed inside a cavern. You must find your way to the exit!");
		System.out.println("Do it quickly, before something...finds you...");
		System.out.println("");
		System.out.println("");
	}

	public void play()
	{
		Splash();

		IsGameOver = analyizeRooms();

		String input = "";
		while(IsGameOver == false)
		{
			if(DisplayHelp)
			{
				displayHallways();
			}

			displayRoom(r_Current);
			r_Previous = r_Current;
			System.out.print("Change Hallway(C); Go Left(L); Go Right(R):");
			input = scan.next().toLowerCase();
			System.out.println("");

			int nextIndex = -1;
			switch(input)
			{
				case "c":
					nextIndex = r_Current.getIsInnerHallway() ? 0 : 10;
					nextIndex += r_Current.getIndex();
					// System.out.println("Changing to " + nextIndex);
					assignNewRoom(nextIndex);
					break;
				case "l":
					// System.out.println("Lefting...");
					nextIndex = r_Current.getIndex() - 1;
					if(nextIndex < 0)
						nextIndex = 9;
					if(r_Current.getIsInnerHallway())
						r_Current = inner.get(nextIndex);
					else
						r_Current = outter.get(nextIndex);
					break;
				case "r":
					// System.out.println("Righting...");
					nextIndex = r_Current.getIndex() + 1;
					if(nextIndex > 9)
						nextIndex = 0;
					if(r_Current.getIsInnerHallway())
						r_Current = inner.get(nextIndex);
					else
						r_Current = outter.get(nextIndex);
					break;
				case "h":
					DisplayHelp = !DisplayHelp;
				default:
					// System.out.println("Unknown");
					break;
			}
			IsGameOver = analyizeRooms();
		}
		System.out.println();
	}

	// Display all hallways (used for DisplayHelp attribute)
	private void displayHallways()
	{
		System.out.print("Outter Hallway: ");
		for(int c = 0; c < outter.size(); c++)
		{
			System.out.print("-" + outter.get(c).getRoomItem().toString());
		}
		System.out.println("");

		System.out.print("Inner Hallway:  ");
		for(int c = 0; c < inner.size(); c++)
		{
			System.out.print("-" + inner.get(c).getRoomItem().toString());
		}
		System.out.println("");
	}

	/// See if the current room is the same room as any of the special rooms
	private boolean analyizeRooms()
	{
		//Are we met with the wumpus
		if(r_Current.compareTo(r_Wumpus) == 0)
		{
			showWumpus();
			return true;
		}

		// Are we at the exit
		if(r_Current.compareTo(r_Exit) == 0)
		{
			// Gameover
			System.out.println("You made it out alive! Woohoo!");
			return true;
		}

		// Are we in a room with bats
		if(r_Current.compareTo(r_Bats) == 0)
		{
			//Go to previous remove
			System.out.println("Eeeeeek! There are bats here, go back to the previous room");
			r_Current = r_Previous;
		}

		// Are we falling into a pit?
		if(r_Current.compareTo(r_Pit) == 0)
		{
			System.out.println("****Ahhhhhhhhhh!****");
			System.out.println("You fell into a pit...");
			System.out.println("Game Over.");
			return true;
		}

		// Check surrounding areas
		checkSurroundings(r_Wumpus, "*sniff* *sniff*, I smell something funny...");
		checkSurroundings(r_Exit, "I feel...a breeze!!!");

		return false;
	}

	/// Check the surrounding rooms for a particular room. Display a message if adjacent
	private void checkSurroundings(Room r, String message)
	{
		if(r_Current.getIndex() == r.getIndex() &&  r_Current.getIsInnerHallway() != r.getIsInnerHallway())
		{
			// If we are in the same index but different hallways as wumpus
			System.out.println(message);
		}
		else if(r_Current.getIsInnerHallway() == r.getIsInnerHallway() && (r_Current.getIndex() + 1 == r.getIndex() || 
																				  r_Current.getIndex() - 1 == r.getIndex()))
		{
			// If we are in the same hallway and adjacent to the wumpus
			System.out.println(message);
		}
		else
		{
			// Do nothing
		}
	}

	/// Main program function
	public static void main(String[] args)
	{
		WumpusGame game = new WumpusGame();

		// Parse arguments
		if(args.length == 1)
		{
			if(args[0].toLowerCase().equals("help"))
			{
				game.DisplayHelp = true;
			}
		}

		game.play();
	}
}