package LudoGUI;
import java.awt.Point;

import javax.swing.JButton;

/**
 * The Pawn that a Player has. Moves across the Fields of a board.
 */
public class Pawn {
	private final JButton imgSrc;
	private Field location;
	private HomeField homeLoc;
	private int pos = 0;

	/**
	 * Constructs the Pawn.
	 * 
	 * @param source
	 *            the GUI object for the Pawn
	 * @param pos
	 *            the position on the screen
	 */
	public Pawn(final JButton source, final HomeField loc) {
		this.imgSrc = source;
		this.homeLoc = loc;
		moveToField(loc);
	}

	/**
	 * Gets the GUI object for the Pawn
	 * 
	 * @return the GUI object
	 */
	protected JButton getImgSrc() {
		return imgSrc;
	}

	/**
	 * Sets the position of the Pawn's image on the screen.
	 * 
	 * @param pos
	 *            the position on the screen
	 */
	private void setPosition(final Point pos) {
		imgSrc.setLocation(pos);
	}

	/**
	 * Gets the Field that the Pawn is currently on.
	 * 
	 * @return the Field the Pawn is on
	 */
	public final Field getField() {
		return location;
	}

	/**
	 * Checks if the Pawn is on a HomeField.
	 * 
	 * @return true if the Pawn is on a HomeField
	 */
	public final boolean isAtHome() {
		return (pos == 0 ? true : false);
	}

	/**
	 * Checks if the Pawn is on a BasicField.
	 * 
	 * @return true if the Pawn is on a BasicField
	 */
	public final boolean isAtBasic() {
		return (pos == 1 ? true : false);
	}

	/**
	 * Checks if the Pawn is on a GoalField.
	 * 
	 * @return true if the Pawn is on a GoalField
	 */
	public final boolean isAtGoal() {
		return (pos == 2 ? true : false);
	}

	/**
	 * Moves the Pawn to its associated HomeField.
	 */
	public final void moveToHome() {
		moveToField(homeLoc);
	}

	/**
	 * Moves the Pawn to the specified Field.
	 * 
	 * @param field
	 *            the Field to move to
	 */
	public final void moveToField(final Field field) {
		if (field != homeLoc) {
			location.setPawn(null);
			if (field.hasPawn()) {
				field.getPawn().moveToHome();
			}
		}
		this.location = field;
		setPosition(location.getPoint());
		location.setPawn(this);
		if (field.getClass() == BasicField.class) {
			this.pos = 1;
		} else if (field.getClass() == GoalField.class) {
			this.pos = 2;
		} else if (field.getClass() == HomeField.class) {
			this.pos = 0;
		} else {
			System.err.println("Pawn unable to identify current field type");
		}
	}
}