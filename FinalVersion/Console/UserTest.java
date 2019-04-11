package Ludo;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest 
{
	User u = new User("Blue");

	@Test
	public void test_setColor_Red() 
	{
		String color = "red";
		u.setColor("red");
		assertEquals("Set color red" , color, u.getColor());
	}
	
	@Test
	public void test_setColor_Green() 
	{
		String color = "green";
		u.setColor("green");
		assertEquals("Set color green" , color, u.getColor());
	}
	
	@Test
	public void test_setColor_Blue() 
	{
		String color = "blue";
		u.setColor("blue");
		assertEquals("Set color blue" , color, u.getColor());
	}
	
	@Test
	public void test_setColor_Yellow() 
	{
		String color = "yellow";
		u.setColor("yellow");
		assertEquals("Set color yellow" , color, u.getColor());
	}
	


}