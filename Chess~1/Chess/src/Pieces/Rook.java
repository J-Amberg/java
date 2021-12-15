package Pieces;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author jordan.amberg
 */
public class Rook extends Piece{

    public Rook(Tile tile, Color color) {
        super(tile, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getXPos() + 15, getYPos() + 80, 70, 10);
        g.fillRect(getXPos() + 25, getYPos() + 74, 50, 5);
        g.fillRect(getXPos() + 35, getYPos() + 33, 30, 40);
        g.fillRect(getXPos() + 25, getYPos() + 21, 50, 15);
        g.fillRect(getXPos() + 25, getYPos() + 10, 10, 12);
        g.fillRect(getXPos() + 45, getYPos() + 10, 10, 12);
        g.fillRect(getXPos() + 65, getYPos() + 10, 10, 12);
    }
}
