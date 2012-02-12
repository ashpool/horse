package horse;

import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.TestCase;

public class MoveTest extends TestCase
{
  //24, 27, 49
  public void testMove()
  {
    int pathNumber = 1;
    for (int i = 0; i < ChessBoard.SIDE_LENGHT * ChessBoard.SIDE_LENGHT; i++)
    {
      System.out.println("Starting at position: " + i);
      ChessBoard.INSTANCE.init();
      ChessBoard.INSTANCE.completedMoves = new ArrayList();
      Move move = new Move(null, i);
      move.doMove();
      assertTrue(Move.isCompleted);
      if (Move.isCompleted)
      {
        for (Iterator iterator = ChessBoard.INSTANCE.completedMoves.iterator(); iterator
            .hasNext();)
        {
          Move successful = (Move) iterator.next();
          System.out.println("Path: " + pathNumber++);
          successful.printPath();
          System.out.println("");
        }
      }
    }
  }
}