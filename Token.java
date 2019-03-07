
// The class Token handles the token's properties including the color, number and co-ordinates.
//Since the class Token doesn't have a lot methods (going to move moveToken methods in Token in the future),
//it only has getter and setter methods as of now.
//https://github.com/DigiAshish/Java-Ludo-Game/blob/master/LudoGameHandler.java (line 1507-1563)

public class Token 
{
	private int tokenNumber;
	private int xCoord;
	private int yCoord;
	private String color;
	private boolean takenOut;
	private boolean completed;
	
	Token(int tokenNumber, String color) 
	{
		this.tokenNumber = tokenNumber+1;
		this.color = color;
	}
	
	String getColor() 
	{
		return color;
	}
		
	int getX() 
	{
		return xCoord;
	}
	
	int getY() 
	{
		return yCoord;
	}
	
	void setX(int x) 
	{
		xCoord = x;
	}
	
	void setY(int y) 
	{
		yCoord = y;
	}
	
	void setTakenOut(boolean mode) 
	{
		takenOut = mode;
	}
	
	boolean isTakenOut() 
	{
		return takenOut;
	}
	
	void setCompleted(boolean mode) 
	{
		completed = mode;
	}
	
	boolean isCompleted() 
	{
		return completed;
	}

	public int getTokenNumber() {
		return tokenNumber;
	}

}
