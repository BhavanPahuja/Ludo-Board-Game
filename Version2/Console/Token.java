package Ludo;

// The class Token handles the token's properties including the color, number and co-ordinates.
//Since the class Token doesn't have a lot methods (going to move moveToken methods in Token in the future),
//it only has getter and setter methods as of now.
//https://github.com/DigiAshish/Java-Ludo-Game/blob/master/LudoGameHandler.java (line 1507-1563)

public class Token 
{
	private int tokenNumber;
	private static int xCoord;
	private static int yCoord;
	private static String color;
	private static boolean takenOut;
	private static boolean completed;
	
	Token(int tokenNumber, String color) 
	{
		this.tokenNumber = tokenNumber+1;
		this.color = color;
	}
	
	static String getColor() 
	{
		return color;
	}
		
	static int getX() 
	{
		return xCoord;
	}
	
	static int getY() 
	{
		return yCoord;
	}
	
	static void setX(int x) 
	{
		xCoord = x;
	}
	
	static void setY(int y) 
	{
		yCoord = y;
	}
	
	static void setTakenOut(boolean mode) 
	{
		takenOut = mode;
	}
	
	static boolean isTakenOut() 
	{
		return takenOut;
	}
	
	static void setCompleted(boolean mode) 
	{
		completed = mode;
	}
	
	static boolean isCompleted() 
	{
		return completed;
	}

	public int getTokenNumber() {
		return tokenNumber;
	}

	public static void setColor(String string) {
		color = string;
		
	}

}
