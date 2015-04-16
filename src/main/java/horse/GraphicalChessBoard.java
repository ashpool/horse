package horse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class GraphicalChessBoard extends JFrame
{
  private static final int ROW_HEIGHT = 30;
  private JTable boardJTable;

  public GraphicalChessBoard()
  {
    this.setBounds(100, 100, ROW_HEIGHT * 9, ROW_HEIGHT * 9);
    boardJTable = new JTable();
    boardJTable.setRowHeight(ROW_HEIGHT);
    boardJTable.addMouseListener(new MouseListener()
    {
      public void mouseClicked(MouseEvent e)
      {
        setModel(ChessBoard.INSTANCE.getPosition(boardJTable.getSelectedRow(),
            boardJTable.getSelectedColumn()));
      }

      public void mouseEntered(MouseEvent e)
      {
        // TODO Auto-generated method stub
      }

      public void mouseExited(MouseEvent e)
      {
        // TODO Auto-generated method stub
      }

      public void mousePressed(MouseEvent e)
      {
        // TODO Auto-generated method stub
      }

      public void mouseReleased(MouseEvent e)
      {
        // TODO Auto-generated method stub
      }
    });
    setModel(-1);
    getContentPane().add(boardJTable);
  }

  private void setModel(int start)
  {
    ChessBoardTableModel model = null;
    if (start != -1)
    {
      ChessBoard.INSTANCE.init();
      Move.init();
      Move move = new Move(null, start);
      move.doMove();
      model = new ChessBoardTableModel(
          (Move) ChessBoard.INSTANCE.completedMoves.get(0));
    }
    else
    {
      model = new ChessBoardTableModel();
    }
    boardJTable.setModel(model);
    ChessCellRenderer chessCellRenderer = new ChessCellRenderer();
    for (int column = 0; column < boardJTable.getColumnCount(); column++)
    {
      TableColumn col = boardJTable
          .getColumn(boardJTable.getColumnName(column));
      col.setCellRenderer(new ChessCellRenderer());
    }
  }

  class ChessCellRenderer extends DefaultTableCellRenderer
  {
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column)
    {
      JLabel jlabel = (JLabel) super.getTableCellRendererComponent(table,
          value, isSelected, hasFocus, row, column);
      jlabel.setFont(new Font("dialog", Font.BOLD, 16));
      if (column % 2 == row % 2)
      {
        jlabel.setBackground(Color.BLACK);
        jlabel.setForeground(Color.WHITE);
      }
      else
      {
        jlabel.setBackground(Color.WHITE);
        jlabel.setForeground(Color.BLACK);
      }
      return jlabel;
    }
  }
}