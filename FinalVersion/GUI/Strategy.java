package LudoGUI;
public interface Strategy {

	/**
	 * Chooses a move for the Player to make.
	 * 
	 * @param player
	 *            The Player the strategy belongs to. For determining the Pawns
	 *            and associated Fields to decide a move.
	 * @param dieRoll
	 *            The die roll, so the strategy can determine the appropriate
	 *            move.
	 */
	public void chooseMove(final Player player, final int dieRoll);

	/**
	 * Send the move to a Player.
	 * 
	 * @param player
	 *            The Player the strategy belongs to. For sending a Player the
	 *            Move to take.
	 * @param move
	 *            The Move for the Player to take.
	 */
	public void sendMoveToPlayer(final Player player, final Move move);
}
