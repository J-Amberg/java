package chess;

import Pieces.Tile;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * @author jordan.amberg
 */
public class ChessFrame extends JFrame {
    
    private ChessPanel chess;
    
    public ChessFrame(){
        super("Chess");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        chess = new ChessPanel();
        chess.setDoubleBuffered(true);
        this.getContentPane().add(chess); 
        this.pack();
        chess.start();
    }
    
    public static void main(String[] args) {
        new ChessFrame().setVisible(true);
 
    }
}
