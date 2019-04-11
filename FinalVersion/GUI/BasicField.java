package LudoGUI;
import java.awt.Point;

/**
 * A BasicField. Players move around this Field to get to the Goal.
 */
public class BasicField extends Field {

	private GoalField goalLink;

	/**
	 * Constructs the BasicField, giving it a specific location, and sets up a
	 * potential Goal.
	 * 
	 * @param point
	 *            the location to associate the Field with
	 */
	public BasicField(final Point point) {
		super(point);
		setGoalField(null);
	}

	/**
	 * Removes a Pawn from this BasicField.
	 * 
	 * @return the Pawn removed
	 */
	public Pawn removePawn() {
		Pawn p = getPawn();
		setPawn(null);
		return p;
	}

	/**
	 * Checks if this BasicField has a GoalField it links to.
	 * 
	 * @return true if it links to a GoalField
	 */
	public final boolean hasGoalField() {
		return (getGoalField() != null ? true : false);
	}

	/**
	 * Gets the GoalField that this BasicField links to.
	 * 
	 * @return the GoalField that this links to, or null if it doesn't
	 */
	public final GoalField getGoalField() {
		return goalLink;
	}

	/**
	 * Sets the GoalField that this BasicField links to.
	 * 
	 * @param goal
	 *            the GoalField to link to
	 */
	public final void setGoalField(final GoalField goal) {
		this.goalLink = goal;
	}
}
