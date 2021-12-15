package chess;

import Pieces.Board;
import Pieces.Pawn;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author jordan.amberg
 */
public class ChessPanel extends JPanel{
    
    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 800;
    Board chessBoard = new Board();
    
    public ChessPanel(){
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    @Override
    public void paint(Graphics g){
        chessBoard.draw(g);
    }
    
    public void start()
    {
        repaint();
    }
}
