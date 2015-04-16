package horse;

import java.util.ArrayList;

public class ChessBoard {
	public static final int SIDE_LENGHT = 8;
	public static final int NUMBER_OF_SQUARES = SIDE_LENGHT * SIDE_LENGHT;
	public static ChessBoard INSTANCE = new ChessBoard();
	public boolean[] visitedPositions = new boolean[NUMBER_OF_SQUARES];
	public ArrayList completedMoves = new ArrayList<Move>();

	private ChessBoard() {
	}

	public int getPosition(int row, int column) {
		return (row * SIDE_LENGHT) + (column % SIDE_LENGHT);
	}

	public int getRow(int position) {
		return position / SIDE_LENGHT;
	}

	public int getColumn(int position) {
		return position % SIDE_LENGHT;
	}

	public boolean canMoveTo(int row, int column) {
		if (row < 0 || row >= SIDE_LENGHT)
			return false;
		if (column < 0 || column >= SIDE_LENGHT)
			return false;
		if (visitedPositions[getPosition(row, column)])
			return false;
		return true;
	}

	public void init() {
		visitedPositions = new boolean[NUMBER_OF_SQUARES];
	}
}