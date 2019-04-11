package LudoGUI;
import java.awt.Point;

/**
 * The Field of a game board. Handles one way connections, holding Pawns, and
 * having a point for GUI purposes.
 */
public class Field {

	protected Field nextField;
	protected Pawn occupyingPawn;
	protected Point thePoint;

	/**
	 * Constructs the Field with a particular point.
	 * 
	 * @param point
	 *            the point to associate this Field with
	 */
	public Field(final Point point) {
		setNextField(null);
		setPawn(null);
		setPoint(point);
	}

	/**
	 * Checks if this Field links to another field.
	 * 
	 * @return True if there is a link to another Field
	 */
	public boolean hasNextField() {
		return (getNextField() != null ? true : false);
	}

	/**
	 * Gets the Field that this Field links to.
	 * 
	 * @return returns the Field this one links to, or null if there is none
	 */
	public Field getNextField() {
		return nextField;
	}

	/**
	 * Sets the Field that this Field links to.
	 * 
	 * @param nextField
	 *            the Field to link to
	 */
	protected void setNextField(final Field nextField) {
		this.nextField = nextField;
	}

	/**
	 * Checks if there is a Pawn on this Field.
	 * 
	 * @return True if a Pawn is on this Field
	 */
	public boolean hasPawn() {
		return (getPawn() != null ? true : false);
	}

	/**
	 * Gets the Pawn that is on this Field.
	 * 
	 * @return the Pawn that is on this Field, or null if there is none
	 */
	public Pawn getPawn() {
		return occupyingPawn;
	}

	/**
	 * Sets the Pawn that is on this Field.
	 * 
	 * @param pawn
	 *            the Pawn to associate with this Field
	 */
	protected void setPawn(final Pawn pawn) {
		this.occupyingPawn = pawn;
	}

	/**
	 * Gets the Point associated with this Field.
	 * 
	 * @return the Point
	 */
	public Point getPoint() {
		return thePoint;
	}

	/**
	 * Sets the Point associated with this Field
	 * 
	 * @param point
	 *            the Point to associate with this Field
	 */
	protected void setPoint(final Point point) {
		this.thePoint = point;
	}
}
