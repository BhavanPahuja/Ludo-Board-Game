package LudoGUI;
import java.util.ArrayList;

/**
 * A strategy that attempts to avoid moving pawns into a position vulnerable to
 * being landed on.
 */
public class DefensiveStrategy implements Strategy {

	private HomeField theHome;
	private ArrayList<Pawn> thePawns;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Strategy#chooseMove()
	 */
	@Override
	public void chooseMove(final Player player, final int dieRoll) {
		theHome = player.getHomeField();
		thePawns = player.getPawns();

		Pawn frontMostValid = null;
		ArrayList<Pawn> basicFieldPawns = new ArrayList<Pawn>();

		// First iterate through fields to find own pawns.
		Field field = theHome.getNextField();
		do {
			if (field.hasPawn()) {
				if (thePawns.contains(field.getPawn())) {
					basicFieldPawns.add(0, field.getPawn());
				}
			}
			field = field.getNextField();
		} while (field != theHome.getNextField());

		// Now iterate through pawns on basic fields from most forward to least.
		// The idea here is to make sure we don't place ourselves "near" the
		// front of another pawn, or in another place's home field.
		ArrayList<Move> rejects = new ArrayList<Move>();
		Move chosen = null;
		for (Pawn p : basicFieldPawns) {
			// Can we move? Where would we move too?
			Field f = player.checkMovePawnBasic(p, (BasicField) p.getField(),
					dieRoll);
			// If we can move...
			if (f != null) {
				// Use this pawn.
				if (frontMostValid == null) {
					frontMostValid = p;
					if (player.checkMovePawnBasic(frontMostValid,
							(BasicField) frontMostValid.getField(), dieRoll)
							.getClass() == GoalField.class) {
						sendMoveToPlayer(player, new Move(frontMostValid, f));
						return;
					}
				}

				// For every field between the pawn and the target location,
				Field nextField = p.getField();
				for (int i = 0; i < dieRoll; i++) {
					// We've confirmed this is a safe move, so make it our
					// chosen move.
					if (nextField.getNextField().getPawn() == null
							&& nextField.getNextField().equals(f)) {
						chosen = new Move(p, f);
						break;
					}
					// If the field is empty, keep checking!
					else if (nextField.getNextField().getPawn() == null) {
						nextField = nextField.getNextField();
					}
					// If it's not empty, just continue to the next pawn. Adding
					// the reject to our list of rejects to fall back to.
					else {
						nextField = nextField.getNextField();
						rejects.add(new Move(p, f));
						continue;
					}
				}
				if (chosen != null) {
					sendMoveToPlayer(player, chosen);
					return;
				}

			}
		}

		// Iterates through the goal pawns if no valid or good move found yet.
		for (Pawn p : thePawns) {
			if (p.isAtGoal()) {
				Field f = player.checkMovePawnGoal(p, (GoalField) p.getField(),
						dieRoll);
				if (f != null) {
					sendMoveToPlayer(player, new Move(p, f));
					return;
				}
			}
		}

		// Finally, take a stupid move if one is possible.
		if (rejects.size() != 0) {
			sendMoveToPlayer(player, rejects.get(0));
			return;
		}

		// If the player has no moves, he passes.
		sendMoveToPlayer(player, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Strategy#sendMoveToPlayer()
	 */
	@Override
	public void sendMoveToPlayer(final Player player, final Move move) {
		player.takeMove(move);
	}

}
