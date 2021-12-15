package Pieces;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author jordan.amberg
 */
public class Pawn extends Piece{
    
    public Pawn(Tile tile, Color color) {
        super(tile, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getXPos() + 20, getYPos() + 80, 60, 10);
        int[] xTrianglePos = {getXPos() + 30, getXPos() + 50, getXPos() + 70};
        int[] yTrianglePos = {getYPos() + 78, getYPos() + 38, getYPos() + 78};
        g.fillPolygon(xTrianglePos, yTrianglePos, 3);
        g.fillOval(getXPos() + 30, getYPos() + 30, 40, 30);
    }
    
}
