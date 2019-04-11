package LudoGUI;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * The Player object. Holds the relevant strategy, pawns, goal and home fields.
 * Has methods for analyzing the state of the Pawns and the track.
 */
public class Player {
	private final Strategy strategy;
	private final ArrayList<GoalField> goalField;
	private final HomeField homeField;
	private final JLabel playLabel;
	private final LudoGame parent;
	// Should be a set of 4.
	private final ArrayList<Pawn> pawns;

	/**
	 * Constructs the player.
	 * 
	 * @param strategy
	 *            The strategy used by the player.
	 * @param goalField
	 *            The player's goal field. This should be determined by the game
	 *            itself.
	 * @param homeField
	 *            The player's home field. This should be determined by the game
	 *            itself.
	 * @param playLabel
	 *            The player's label, for showing in the GUI whose turn it is.
	 * @param pawns
	 *            The set of the players pawns. These should be created by the
	 *            Ludo game initialization.
	 * @param parent
	 *            The LudoGame controller. Passed in to allow for thread calls.
	 */
	public Player(final Strategy strategy,
			final ArrayList<GoalField> goalField, final HomeField homeField,
			final JLabel playLabel, final ArrayList<Pawn> pawns,
			final LudoGame parent) {
		this.strategy = strategy;
		this.goalField = goalField;
		this.homeField = homeField;
		this.playLabel = playLabel;
		this.playLabel.setFont(new Font("Sans-Serif", Font.BOLD, 26));
		this.pawns = pawns;
		this.parent = parent;
	}

	/**
	 * Calls on the strategy to perform a move, unless a 6 is rolled and a pawn
	 * can make a valid move out of the HomeField. Then that is done instead.
	 */
	public final void doMove(final int dieRoll) {
		if (dieRoll >=9 && homeField.getPawnCount() > 0
				&& checkMovePawnHome() != null) {
			takeMove(new Move(homeField.getPawn(), homeField.getNextField()));
		} else {
			strategy.chooseMove(this, dieRoll);
		}
	}

	/**
	 * Performs a move, placing a player on a new field. Called by the strategy.
	 * 
	 * @param move
	 *            the move to make
	 */
	public void takeMove(final Move move) {
		if (move != null) {
			Pawn p = move.getPawn();
			Field f = move.getField();

			while (move.getField() != f) {
				if (f.getClass() == BasicField.class) {
					if (((BasicField) f).hasGoalField()) {
						if (((BasicField) f).getGoalField() == goalField.get(3)) {
							f = ((BasicField) f).getGoalField();
						} else {
							f = f.getNextField();
						}
					} else {
						f = f.getNextField();
					}
				} else {
					f = f.getNextField();
				}
			}
			p.moveToField(f);
		}
		SwingUtilities.invokeLater(parent.continueAfterThreadEnd);
	}

	/**
	 * Gets the player's HomeField.
	 * 
	 * @return The home field.
	 */
	public final HomeField getHomeField() {
		return homeField;
	}

	/**
	 * Gets the player's GoalField entry point.
	 * 
	 * @return The goal field.
	 */
	public final GoalField getEntryGoalField() {
		// The end of the GoalField array is where the entry point is.
		return goalField.get(goalField.size() - 1);
	}

	/**
	 * Gets the player's Pawns.
	 * 
	 * @return The Pawns.
	 */
	public final ArrayList<Pawn> getPawns() {
		return pawns;
	}

	/**
	 * Checks if a Pawn can be taken from the HomeField.
	 */
	public Field checkMovePawnHome() {
		if (checkValidMove(homeField.getNextField())) {
			return homeField.getNextField();
		} else {
			return null;
		}
	}

	/**
	 * Checks if a pawn could be moved along the normal fields, checking for
	 * matching goal fields. Once distance left to travel is zero, settles on
	 * field to move to.
	 * 
	 * @param pawn
	 *            the Pawn to check
	 * @param field
	 *            the Field to start with
	 * @param distance
	 *            the distance to move
	 * @return the Field to move to, or null if there is no valid move
	 */
	public Field checkMovePawnBasic(final Pawn pawn, final BasicField field, final int distance)
	{
		if (distance == 0 && checkValidMove(field)) 
		{
			return field;
		} 
		else if (distance == 0 && !checkValidMove(field)) 
		{
			return null;
		} 
		else 
		{
			if (field.hasGoalField()) 
			{
				if (field.getGoalField() == goalField.get(3)) 
				{
					return checkMovePawnGoal(pawn, goalField.get(3),
							distance - 1);
				} 
				else 
				{
					System.out.println("Noticed a goal field ... failed to be interested");
					return checkMovePawnBasic(pawn,	(BasicField) field.getNextField(), distance - 1);
				}
			} 
			else 
			{
				return checkMovePawnBasic(pawn,
						(BasicField) field.getNextField(), distance - 1);
			}
		}
	}

	/**
	 * Checks if a pawn could be moved along the goal fields.
	 * 
	 * @param pawn
	 *            the Pawn to check
	 * @param goal
	 *            the GoalField to start with
	 * @param distance
	 *            the distance to move
	 * @return the Field to move to, or null if there is no valid move
	 */
	public Field checkMovePawnGoal(final Pawn pawn, final GoalField goal, final int distance) 
	{
		if (distance == 0)
		{
			if (checkValidMove(goal)) 
			{
				return goal;
			} 
			else 
			{
				return null;
			}
		} 
		else if (!goal.hasNextField()) 
		{
			return null;
		} 
		else 
		{
			return checkMovePawnGoal(pawn, (GoalField) goal.getNextField(),
					distance - 1);
		}
	}

	/**
	 * Gets if the player's goal is full.
	 * 
	 * @return True if the player's goal fields are full.
	 */
	public boolean checkIfGoalFull() {
		boolean isFull = true;
		for (GoalField g : goalField) {
			isFull &= g.hasPawn();
		}
		return isFull;
	}

	/**
	 * Gets if the player's goal is occupied.
	 * 
	 * @return This returns true if one or more goal field has a pawn.
	 */
	public boolean checkIfGoalOccupied() {
		boolean hasPawn = false;
		for (GoalField g : goalField) {
			hasPawn |= g.hasPawn();
		}
		return hasPawn;
	}

	/**
	 * Gets the number of pawns occupying the player's goal.
	 * 
	 * @return The number of pawns occupying the goal.
	 */
	public int getGoalOccupiedCount() {
		int numPawns = 0;
		for (GoalField g : goalField) {
			if (g.hasPawn()) {
				numPawns++;
			}
		}
		return numPawns;
	}

	/**
	 * Updates the GUI to indicate it is not this player's turn.
	 */
	public void setLabelNotTurn() {
		playLabel.setForeground(new Color(0x000000));
	}

	/**
	 * Updates the GUI to indicate is is this player's turn.
	 */
	public void setLabelIsTurn() {
		playLabel.setForeground(new Color(0xff2222));
	}

	/**
	 * Checks if a particular Field is valid to have this player's pawn on.
	 * 
	 * @param field
	 *            The field that the player wishes to move the pawn to.
	 * @return True if the move is valid.
	 */
	public boolean checkValidMove(final Field field) 
	{
		if (field.hasPawn()) 
		{
			if (isOwnPawn(field.getPawn())) 
			{
				System.out.println("Invalid move considered, own pawn at field location");
				return false;
			}
			else 
			{
				return true;
			}
		} 
		else 
		{
			return true;
		}
	}

	/**
	 * Checks if a particular pawn belongs to this player.
	 * 
	 * @param foundPawn
	 *            The pawn the player wishes to check.
	 * @return True if the foundPawn is the player's own pawn.
	 */
	private boolean isOwnPawn(final Pawn foundPawn) {
		boolean ownPawn = false;
		for (Pawn p : pawns) {
			ownPawn |= (p == foundPawn);
		}
		return ownPawn;
	}
}
