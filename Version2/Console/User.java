package Ludo;

public class User 
{
	private String color;
	private int numberRolled;
	private boolean hasRolledSix;
	private Token[] token;
	
	User(String color) 
	{
		this.color = color;
		token = new Token[4];
		for(int i=0; i<4; i++)
			token[i] = new Token(i, color);
	}
	
	void rollDice() 
	{
		numberRolled = new Dice().roll();
		if(numberRolled==6)
			hasRolledSix = true;
		else 
			hasRolledSix = false;
	}
	
	Token getToken(int tokenNumber) 
	{
		return token[tokenNumber];
	}
	
	String getColor() 
	{
		return color;
	}
	
	int getNumberRolled() 
	{
		return numberRolled;
	}
	
	boolean hasRolledSix() 
	{
		return hasRolledSix;
	}

	// Override---------
	public String toString() 
	{
		return "User " + color;
	}
	
	boolean hasWon() 
	{
		for(int i=0; i<4; i++)
			if(!token[i].isCompleted())
				return false;
		return true;	
	}
	
	public void setColor(String color) 
	{
		this.color = color;
	}
}