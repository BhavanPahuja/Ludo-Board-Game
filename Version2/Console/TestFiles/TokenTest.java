package Ludo;
import Ludo.Token;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenTest 
{

	@Test
	public void test_setColor_Red() 
	{
		String color = "red";
		Token.setColor("red");
		assertEquals("Set color red" , color, Token.getColor());
	}
	
	@Test
	public void test_setColor_Green() 
	{
		String color = "green";
		Token.setColor("green");
		assertEquals("Set color green" , color, Token.getColor());
	}
	
	@Test
	public void test_setColor_Blue() 
	{
		String color = "blue";
		Token.setColor("blue");
		assertEquals("Set color blue" , color, Token.getColor());
	}
	
	@Test
	public void test_setColor_Yellow() 
	{
		String color = "yellow";
		Token.setColor("yellow");
		assertEquals("Set color yellow" , color, Token.getColor());
	}
	
	@Test
	public void test_setX_5()
	{
		int x = 5;
		Token.setX(5);
		
		assertEquals("Set x coordinate to 5" , x, Token.getX());
	}
	
	@Test
	public void test_setX_Negative2()
	{
		int x = -2;
		Token.setX(-2);
		
		assertEquals("Set x coordinate to -2" , x, Token.getX());
	}
	
	@Test
	public void test_setY_5()
	{
		int y = 19;
		Token.setY(19);
		
		assertEquals("Set x coordinate to 5" , y, Token.getY());
	}
	
	@Test
	public void test_setY_Negative7()
	{
		int y = -7;
		Token.setY(-7);
		
		assertEquals("Set x coordinate to -7" , y, Token.getY());
	}
	
	@Test
	public void test_setTakenOut_false()
	{
		boolean takenOut = false;
		Token.setTakenOut(false);		
		assertEquals("Set taken out to false" , takenOut, Token.isTakenOut());
	}
	
	@Test
	public void test_setCompleted_false()
	{
		boolean completed = false;
		Token.setCompleted(false);		
		assertEquals("Set completed" , completed, Token.isCompleted());
	}
}
