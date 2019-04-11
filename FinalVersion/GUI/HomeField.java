package LudoGUI;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A HomeField. Pawns start here, or are sent back here if landed on. This can
 * hold 4 Pawns.
 */
public class HomeField extends Field {

	private final ArrayList<Pawn> homePawns = new ArrayList<Pawn>();
	private final ArrayList<Point> thePoints = new ArrayList<Point>();

	/**
	 * Constructs the HomeField, giving it a specified set of locations..
	 * 
	 * @param points
	 *            the locations for this HomeField
	 */
	public HomeField(final ArrayList<Point> points) {
		super(points.get(0));
		for (Point p : points) {
			thePoints.add(p);
		}
	}

	/**
	 * Overrides getPawn() to intelligently pop the Pawn out of the HomeField
	 * when it's called.
	 */
	@Override
	public final Pawn getPawn() {
		Pawn p = null;
		if (hasPawn()) {
			p = homePawns.remove(0);
		}
		return p;
	}

	/**
	 * Peeks at what Pawn the HomeField would next release.
	 * 
	 * @return the Pawn that would be released
	 */
	public final Pawn peekAtPawn() {
		return homePawns.get(0);
	}

	@Override
	public final boolean hasPawn() {
		return (homePawns.size() > 0);
	}

	/**
	 * Gets the number of Pawns in this HomeField.
	 * 
	 * @return the number of Pawns
	 */
	public final int getPawnCount() {
		return homePawns.size();
	}

	/**
	 * Checks if the HomeField is completely full of Pawns.
	 * 
	 * @return true if the HomeField is full
	 */
	public final boolean isFull() {
		return (homePawns.size() == 4);
	}

	/**
	 * Sets the Pawns that are in this HomeField.
	 * 
	 * @param pawns
	 *            the Pawns to set
	 */
	public final void setPawns(final ArrayList<Pawn> pawns) {
		for (Pawn p : pawns) {
			setPawn(p);
		}
	}

	@Override
	public final void setPawn(final Pawn pawn) {
		if (pawn != null) {
			homePawns.add(0, pawn);
		}
	}

	@Override
	public Point getPoint() {
		return getPoints().get(homePawns.size());
	}

	/**
	 * Gets the Points associated with this HomeField.
	 * 
	 * @return the Points
	 */
	public ArrayList<Point> getPoints() {
		return thePoints;
	}
}
