package Ludo;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest 
{

	@Test
	public void test_setColor_Red() 
	{
		String color = "red";
		User.setColor("red");
		assertEquals("Set color red" , color, Token.getColor());
	}
	
	@Test
	public void test_setColor_Green() 
	{
		String color = "green";
		User.setColor("green");
		assertEquals("Set color green" , color, Token.getColor());
	}
	
	@Test
	public void test_setColor_Blue() 
	{
		String color = "blue";
		User.setColor("blue");
		assertEquals("Set color blue" , color, Token.getColor());
	}

}
