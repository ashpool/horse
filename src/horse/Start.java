package horse;

import javax.swing.JFrame;

public class Start
{
  public static void main(String[] args)
  {
    GraphicalChessBoard board = new GraphicalChessBoard();
    board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    board.setVisible(true);
  }
}