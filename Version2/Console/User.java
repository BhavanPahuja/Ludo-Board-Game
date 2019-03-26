package Ludo;
//User controls the properties of the user/player itself like color of player, the tokens and the number rolled.
//https://github.com/DigiAshish/Java-Ludo-Game/blob/master/LudoGameHandler.java (Line 1441-1505)
// Again user doesn't have a lot of methods except for getter and setter methods and a method that checks if they rolled a six.
// Code has been modified to work with multiple classes.
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
}
