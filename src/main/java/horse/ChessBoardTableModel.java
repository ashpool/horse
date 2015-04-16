package horse;

import javax.swing.table.DefaultTableModel;

public class ChessBoardTableModel extends DefaultTableModel {
	public ChessBoardTableModel() {
		super();
		for (int column = 0; column < ChessBoard.SIDE_LENGHT; column++) {
			addColumn("" + column);
		}
		for (int row = 0; row < ChessBoard.SIDE_LENGHT; row++) {
			addRow(new Object[8]);
		}
	}

	public ChessBoardTableModel(Move move) {
		this();
		displayMove(move);
	}

	private void displayMove(Move move) {
		if (move == null) {
			return;
		}
		setValueAt(new Integer(move.sequenceNumber), ChessBoard.INSTANCE
				.getRow(move.position), ChessBoard.INSTANCE.getColumn(move.position));
		displayMove(move.previousMove);
	}

	public void setValueAt(int row, int column) {
		fireTableCellUpdated(row, column);
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
