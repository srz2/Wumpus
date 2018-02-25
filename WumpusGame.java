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
	int i_Wumpus = rand.nextInt(NUMROOMS * 2);
	int i_Bats = rand.nextInt(NUMROOMS * 2);
	int i_Pit = rand.nextInt(NUMROOMS * 2);
	int i_Exit = rand.nextInt(NUMROOMS * 2);

	Room r_Current = null;
	Room r_Wumpus = null;
	Room r_Bats = null;
	Room r_Pit = null;
	Room r_Exit = null;

	boolean IsGameOver = false;

	private RoomItem getRoomItem(int index)
	{
		RoomItem item = RoomItem.None;

		if(i_Wumpus % (NUMROOMS * 2 ) == index)
		{
			item = RoomItem.Wumpus;
		}

		if(i_Bats % (NUMROOMS * 2 ) == index)
		{
			item = RoomItem.Bats;
		}

		if(i_Pit % (NUMROOMS * 2 ) == index)
		{
			item = RoomItem.Pit;
		}

		if(i_Exit % (NUMROOMS * 2 ) == index)
		{
			item = RoomItem.Exit;
		}

		return item;
	}

	public WumpusGame()
	{
		System.out.println(i_Wumpus);
		System.out.println(i_Bats);
		System.out.println(i_Pit);
		System.out.println(i_Exit);

		// Makes sure the exit and pit aren't in the same room
		while(i_Exit == i_Pit)
		{
			i_Exit = rand.nextInt(NUMROOMS * 2);
		}

		// Create rooms and place items inside
		for(int c = 0; c < NUMROOMS * 2; c++)
		{
			RoomItem item = getRoomItem(c);
			
			// Index 0 - 9 is outter room, 10-19 is inner room
			if(c < 10)
			{
				// System.out.println("Creating outter at " + c);
				outter.add(new Room(c, item));
				displayRoom(outter.get(c));
			}
			else
			{
				// System.out.println("Creating inner at " + c);
				inner.add(new Room(c, item));
				displayRoom(inner.get(c -  10));
			}
		}

		// Randomly get a starting room
		int value = rand.nextInt(NUMROOMS * 2);
		// System.out.println("Randomly selected room: " + value);
		assignNewRoom(value);
	}

	private void assignNewRoom(int index)
	{
		// System.out.print("Current Room\n\t");
		// if(r_Current != null)
		// 	displayRoom(r_Current);
		// else
		// 	System.out.println("NULL");
		if(index < 10)
		{
			r_Current = outter.get(index);
		}
		else
		{
			r_Current = inner.get(index - 10);
		}
		// System.out.print("New Room - " + index + "\n\t");
		// displayRoom(r_Current);
	}

	private void displayRoom(Room room)
	{
		System.out.println("You are currently in: " + room.getHallway() + " " + (room.getIndex() + 1) + " is has " + room.getRoomItem().toString());
	}

	private void Splash()
	{
		System.out.println("");
		System.out.println("          Welcome to Wump the Wumpus");
		System.out.println("          ---Do you feel...lucky?---");
		System.out.println("");
		System.out.println("You have been placed inside a cavern. You must find your way to the exit!");
		System.out.println("Do it quickly, before something...finds you...");
	}

	public void play()
	{
		Splash();

		String input = "";
		while(IsGameOver == false)
		{
			displayRoom(r_Current);
			System.out.println("What would you like to do?");
			System.out.print("Change Hallway(C); Go Left(L); Go Right(R):");
			input = scan.next().toLowerCase();

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
				default:
					System.out.println("Unknown");
					break;
			}
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		WumpusGame game = new WumpusGame();	
		game.play();
	}
}