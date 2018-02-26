public class Room implements Comparable<Room>
{
	public boolean isInner = false;
	public int hallwayIndex = -1;
	public boolean isExit = false;
	public RoomItem item = RoomItem.None;

	public Room(int myIndex, RoomItem roomItem)
	{
		if(myIndex >= 10)
		{
			myIndex -= 10;
			isInner = true;
		}
		hallwayIndex = myIndex;
		item = roomItem;
	}

	public int getIndex()
	{
		return hallwayIndex;
	}

	public String getHallway()
	{
		return isInner ? "Inner" : "Outter";
	}

	public boolean getIsInnerHallway()
	{
		return isInner;
	}

	public RoomItem getRoomItem()
	{
		return item;
	}

	@Override
	public int compareTo(Room otherRoom)
	{
		if(this.isInner == otherRoom.isInner && this.hallwayIndex == otherRoom.hallwayIndex)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}