package Ludo;

import Ludo.Token;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenTest 
{
	Token t = new Token(2, "Red");

	@Test
	public void test_setColor_Red() 
	{
		String color = "red";
		t.setColor("red");
		assertEquals("Set color red" , color, t.getColor());
	}
	
	@Test
	public void test_setColor_Green() 
	{
		String color = "green";
		t.setColor("green");
		assertEquals("Set color green" , color, t.getColor());
	}
	
	@Test
	public void test_setColor_Blue() 
	{
		String color = "blue";
		t.setColor("blue");
		assertEquals("Set color blue" , color, t.getColor());
	}
	
	@Test
	public void test_setColor_Yellow() 
	{
		String color = "yellow";
		t.setColor("yellow");
		assertEquals("Set color yellow" , color, t.getColor());
	}
	
	@Test
	public void test_setX_5()
	{
		int x = 5;
		t.setX(5);
		
		assertEquals("Set x coordinate to 5" , x, t.getX());
	}
	
	@Test
	public void test_setX_Negative2()
	{
		int x = -2;
		t.setX(-2);
		
		assertEquals("Set x coordinate to -2" , x, t.getX());
	}
	
	@Test
	public void test_setY_5()
	{
		int y = 19;
		t.setY(19);
		
		assertEquals("Set x coordinate to 5" , y, t.getY());
	}
	
	@Test
	public void test_setY_Negative7()
	{
		int y = -7;
		t.setY(-7);
		
		assertEquals("Set x coordinate to -7" , y, t.getY());
	}
	
	@Test
	public void test_setTakenOut_false()
	{
		boolean takenOut = false;
		t.setTakenOut(false);		
		assertEquals("Set taken out to false" , takenOut, t.isTakenOut());
	}
	
	@Test
	public void test_setCompleted_false()
	{
		boolean completed = false;
		t.setCompleted(false);		
		assertEquals("Set completed" , completed, t.isCompleted());
	}
}
