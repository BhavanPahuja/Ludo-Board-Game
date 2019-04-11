package Ludo;
import static org.junit.Assert.*;
import org.junit.Test;



public class BoardTest 
{
	
	board b = new board();
	Token t = new Token(2, "Red");
	User u = new User("Red");
	
	@Test
	public void test_takeTokenOut_false()
	{		
		t.setTakenOut(true);
		assertEquals("Set token taken out to true" , false, b.takeTokenOut(t));
	}
	
	
	@Test
	public void test_takeTokenOut_true()
	{
		t.setTakenOut(false);
		assertEquals("Set token taken out to false" , true, b.takeTokenOut(t));
	}
	
	@Test
	public void test_Movetoken_tokenNotTakenOut()
	{
		t.setTakenOut(false);
		
		assertEquals("Token is not outside the home circle" , false, b.moveToken(t, 4));
	}
	
	@Test
	public void test_Movetoken_InvalidNumberOfmoves()
	{	
		assertEquals("Number of moves is 8" , false, b.moveToken(t, 8));
	}
	
	@Test
	public void test_MovesArePossible_InvalidNumberRolled()
	{
		assertEquals("Number rolled is 8" , false, b.movesArePossible(u, 8));
	}
	
	@Test
	public void test_canTakeTokenOut_false()
	{		
		t.setTakenOut(true);
		assertEquals("Set token taken out to true" , false, b.takeTokenOut(t));
	}
	
	
	

}
