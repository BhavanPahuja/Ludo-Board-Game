package LudoGUI;
import java.awt.Point;

/**
 * A GoalField. Players occupy this with Pawns in order to win the game.
 */
public class GoalField extends Field {

	/**
	 * Constructs the GoalField, giving it a specified location.
	 * 
	 * @param point
	 *            the location to associate this Field with
	 */
	public GoalField(final Point point) {
		super(point);
	}

	/**
	 * Sets the next GoalField that this GoalField connects to.
	 * 
	 * @param goalField
	 *            the GoalField to connect to
	 */
	public final void setNextGoalField(final GoalField goalField) {
		setNextField(goalField);
	}
}
