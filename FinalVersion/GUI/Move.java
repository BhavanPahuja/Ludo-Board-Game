package LudoGUI;
public class Move {

	private final Pawn thePawn;
	private final Field theField;

	public Move(final Pawn pawn, final Field field) {
		this.thePawn = pawn;
		this.theField = field;
	}

	public final Pawn getPawn() {
		return thePawn;
	}

	public final Field getField() {
		return theField;
	}
}
