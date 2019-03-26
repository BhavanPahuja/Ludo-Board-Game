package Ludo;
import Ludo.Dice;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void test_DiceNumberBetween1And6()
	{
		boolean ret = false;
		if(Dice.roll()>=1 && Dice.roll()<=6)
			ret = true;
		else
			ret = false;
		assertEquals("Check if dice roll is between 1 and 6" , true, ret);
	}

}
