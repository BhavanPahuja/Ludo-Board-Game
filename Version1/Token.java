
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
