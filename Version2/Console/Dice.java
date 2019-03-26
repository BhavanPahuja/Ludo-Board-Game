package Ludo;
//Dice class just uses random to generate a random number between 1 and 6.

import java.util.Random;
public class Dice 
{
	static int roll() 
	{
		return new Random().nextInt(6) + 1;
	}
}
