package horse;

import java.util.ArrayList;
import java.util.Arrays;

public class Move implements Comparable
{
  public static boolean isCompleted = false;
  public int position;
  public Move previousMove;
  protected ArrayList movesFromHere = new ArrayList();
  public int sequenceNumber;

  public static void init()
  {
    isCompleted = false;
    ChessBoard.INSTANCE.completedMoves = new ArrayList();
  }

  public Move(Move previousMove, int position)
  {
    if (previousMove != null)
    {
      this.previousMove = previousMove;
      this.previousMove.movesFromHere.remove(this);
      this.previousMove.movesFromHere.add(this);
    }
    this.position = position;
    if (previousMove != null)
    {
      this.sequenceNumber = previousMove.sequenceNumber + 1;
    }
    if (sequenceNumber >= (ChessBoard.SIDE_LENGHT * ChessBoard.SIDE_LENGHT) - 1)
    {
      isCompleted = true;
      ChessBoard.INSTANCE.completedMoves.add(this);
      printPath();
    }
  }

  public void doMove()
  {
    ChessBoard.INSTANCE.visitedPositions[position] = true;
    Move[] moves = compact(getMovementsFromHere());
    Arrays.sort(moves);
    boolean couldMoveFromHere = false;
    for (int i = 0; i < moves.length; i++)
    {
      Move move = moves[i];
      if (move != null)
      {
        move.doMove();
        couldMoveFromHere = true;
      }
    }
    if (!couldMoveFromHere && previousMove != null)
    {
      ChessBoard.INSTANCE.visitedPositions[position] = false;
      previousMove.movesFromHere.remove(this);
    }
  }

  public void printPath()
  {
    System.out.print(position + ", ");
    if (previousMove != null)
    {
      previousMove.printPath();
    }
    else
    {
      System.out.print("null");
    }
  }

  public Move[] getMovementsFromHere()
  {
    Move[] moves = new Move[8];
    moves[0] = get2Down1Left();
    moves[1] = get2Down1Right();
    moves[2] = get1Down2Left();
    moves[3] = get1Down2Right();
    moves[4] = get1Up2Left();
    moves[5] = get1Up2Right();
    moves[6] = get2Up1Left();
    moves[7] = get2Up1Right();
    return moves;
  }

  public Move[] compact(Move[] moves)
  {
    Move[] compact = new Move[getNumberOfMovesFromHere()];
    int y = 0;
    for (int x = 0; x < moves.length; x++)
    {
      if (moves[x] != null)
        compact[y++] = moves[x];
    }
    return compact;
  }

  public int getNumberOfMovesFromHere()
  {
    int count = 0;
    Move[] moves = getMovementsFromHere();
    for (int i = 0; i < moves.length; i++)
    {
      if (moves[i] != null)
        count++;
    }
    return count;
  }

  private Move getMove(int row, int column)
  {
    if (ChessBoard.INSTANCE.canMoveTo(row, column))
      return new Move(this, ChessBoard.INSTANCE.getPosition(row, column));
    return null;
  }

  private Move get2Up1Right()
  {
    int row = ChessBoard.INSTANCE.getRow(position) + 2;
    int column = ChessBoard.INSTANCE.getColumn(position) + 1;
    return getMove(row, column);
  }

  private Move get2Up1Left()
  {
    int row = ChessBoard.INSTANCE.getRow(position) + 2;
    int column = ChessBoard.INSTANCE.getColumn(position) - 1;
    return getMove(row, column);
  }

  private Move get1Up2Right()
  {
    int row = ChessBoard.INSTANCE.getRow(position) + 1;
    int column = ChessBoard.INSTANCE.getColumn(position) + 2;
    return getMove(row, column);
  }

  private Move get1Up2Left()
  {
    int row = ChessBoard.INSTANCE.getRow(position) + 1;
    int column = ChessBoard.INSTANCE.getColumn(position) - 2;
    return getMove(row, column);
  }

  private Move get1Down2Right()
  {
    int row = ChessBoard.INSTANCE.getRow(position) - 1;
    int column = ChessBoard.INSTANCE.getColumn(position) + 2;
    return getMove(row, column);
  }

  private Move get1Down2Left()
  {
    int row = ChessBoard.INSTANCE.getRow(position) - 1;
    int column = ChessBoard.INSTANCE.getColumn(position) - 2;
    return getMove(row, column);
  }

  private Move get2Down1Right()
  {
    int row = ChessBoard.INSTANCE.getRow(position) - 2;
    int column = ChessBoard.INSTANCE.getColumn(position) + 1;
    return getMove(row, column);
  }

  private Move get2Down1Left()
  {
    int row = ChessBoard.INSTANCE.getRow(position) - 2;
    int column = ChessBoard.INSTANCE.getColumn(position) - 1;
    return getMove(row, column);
  }

  public int compareTo(Object o)
  {
    if (o instanceof Move)
    {
      Move otherMove = (Move) o;
      if (getNumberOfMovesFromHere() > otherMove.getNumberOfMovesFromHere())
      {
        return 1;
      }
      if (getNumberOfMovesFromHere() < otherMove.getNumberOfMovesFromHere())
      {
        return -1;
      }
      if (getNumberOfMovesFromHere() == otherMove.getNumberOfMovesFromHere())
      {
        return 0;
      }
    }
    return 0;
  }
}