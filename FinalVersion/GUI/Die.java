package LudoGUI;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This die, for rolling a random number from 1 to 6 inclusive.
 */
public class Die {
	/* This is a singleton! */
	private static Die instance = new Die();
	private int value = 0;
	private static JLabel imgSrc;

	/**
	 * Private constructor.
	 */
	private Die() {
	}

	/**
	 * Gets the instance of the die.
	 * 
	 * @return The singleton of the die.
	 */
	public static Die getInstance(final JLabel img) {
		if (instance == null) {
			synchronized (Die.class) {
				if (instance == null) {
					instance = new Die();
				}
			}
		}
		imgSrc = img;
		return instance;
	}

	/**
	 * Rolls the die.
	 * 
	 * @return A newly generated die roll.
	 */
	public int roll() {
		Random diceRoller = new Random();
		value = diceRoller.nextInt(6) + 1;// Roll first Dice
		int value2 = diceRoller.nextInt(6) + 1;// Roll second Dice


		value += value2;
		return value;
	}
	/**
	 * Gets the last roll of the die.
	 * 
	 * @return the die roll
	 */
	public int lastRoll() {
		return value;
	}

	/**
	 * Updates the image of the die.
	 * 
	 * @param img
	 *            the new image
	 */
	public void setImage(final ImageIcon img) {
		imgSrc.setIcon(img);
	}
}
