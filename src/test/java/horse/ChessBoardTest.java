package horse;

import junit.framework.TestCase;


public class ChessBoardTest extends TestCase {
	public void testGetPosition() {
		assertEquals(0, ChessBoard.INSTANCE.getPosition(0, 0));
		assertEquals(1, ChessBoard.INSTANCE.getPosition(0, 1));
		assertEquals(8, ChessBoard.INSTANCE.getPosition(1, 0));
		assertEquals(47, ChessBoard.INSTANCE.getPosition(5, 7));
		assertEquals(63, ChessBoard.INSTANCE.getPosition(7, 7));
	}

	public void testGetRow() {
		assertEquals(0, ChessBoard.INSTANCE.getRow(0));
		assertEquals(0, ChessBoard.INSTANCE.getRow(7));
		assertEquals(1, ChessBoard.INSTANCE.getRow(8));
		assertEquals(3, ChessBoard.INSTANCE.getRow(27));
		assertEquals(3, ChessBoard.INSTANCE.getRow(28));
		assertEquals(7, ChessBoard.INSTANCE.getRow(63));
	}

	public void testGetColumn() {
		assertEquals(0, ChessBoard.INSTANCE.getColumn(0));
		assertEquals(7, ChessBoard.INSTANCE.getColumn(7));
		assertEquals(0, ChessBoard.INSTANCE.getColumn(8));
		assertEquals(3, ChessBoard.INSTANCE.getColumn(27));
		assertEquals(4, ChessBoard.INSTANCE.getColumn(28));
		assertEquals(7, ChessBoard.INSTANCE.getColumn(63));
	}

	public void testCanMoveTo() {
		assertFalse(ChessBoard.INSTANCE.canMoveTo(-1, 0));
		assertFalse(ChessBoard.INSTANCE.canMoveTo(0, -1));
		assertFalse(ChessBoard.INSTANCE.canMoveTo(8, 0));
		assertFalse(ChessBoard.INSTANCE.canMoveTo(0, 8));
		assertTrue(ChessBoard.INSTANCE.canMoveTo(0, 0));
		assertTrue(ChessBoard.INSTANCE.canMoveTo(7, 7));

		assertTrue(ChessBoard.INSTANCE.canMoveTo(0, 0));
		ChessBoard.INSTANCE.visitedPositions[0] = true;
		assertFalse(ChessBoard.INSTANCE.canMoveTo(0, 0));
	}
}