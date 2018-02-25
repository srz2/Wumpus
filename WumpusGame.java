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

	Room currentRoom = null;

	boolean IsGameOver = false;

	private RoomItem getRoomItem(int index)
	{
		RoomItem item = RoomItem.None;

		if(i_Wumpus % NUMROOMS == index)
		{
			item = RoomItem.Wumpus;
		}

		if(i_Bats % NUMROOMS == index)
		{
			item = RoomItem.Bats;
		}

		if(i_Pit % NUMROOMS == index)
		{
			item = RoomItem.Pit;
		}

		if(i_Exit % NUMROOMS == index)
		{
			item = RoomItem.Exit;
		}

		return item;
	}

	public WumpusGame()
	{
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
				System.out.println("Creating outter at " + c);
				outter.add(new Room(c, item));
				displayRoom(outter.get(c));
			}
			else
			{
				System.out.println("Creating inner at " + c);
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
		// if(currentRoom != null)
		// 	displayRoom(currentRoom);
		// else
		// 	System.out.println("NULL");
		if(index < 10)
		{
			currentRoom = outter.get(index);
		}
		else
		{
			currentRoom = inner.get(index - 10);
		}
		// System.out.print("New Room - " + index + "\n\t");
		// displayRoom(currentRoom);
	}

	private void displayRoom(Room room)
	{
		System.out.println("You are currently in: " + room.getHallway() + " " + room.getIndex() + " is has " + room.getRoomItem().toString());
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
			displayRoom(currentRoom);
			System.out.println("What would you like to do?");
			System.out.print("Change Hallway(C); Go Left(L); Go Right(R):");
			input = scan.next().toLowerCase();

			switch(input)
			{
				case "c":
					int nextIndex = currentRoom.getIsInnerHallway() ? 0 : 10;
					nextIndex += currentRoom.getIndex();
					System.out.println("Changing to " + nextIndex);
					assignNewRoom(nextIndex);
					break;
				case "l":
					System.out.println("Lefting...");
					break;
				case "r":
					System.out.println("Righting...");
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