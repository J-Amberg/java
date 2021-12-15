package Pieces;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author jordan.amberg
 */
public class Knight extends Piece{

    public Knight(Tile tile, Color color) {
        super(tile, color);
    }

    @Override
    public void draw(Graphics g) {
        int[] xBodyCoords = {24, 78, 74, 66, 52, 40, 35, 29, 18, 20, 12, 12, 2, 2, 10, 12, 17, 22, 39, 39, 35, 24, 24};
        int[] yBodyCoords = {78, 78, 41, 22, 11, 11, 2, 8, 2, 12, 20, 30, 45, 54, 57, 60, 57, 50, 39, 49, 57, 68, 78};
        int[] xEyeCoords = {16, 17, 20, 23, 23, 20, 16};
        int[] yEyeCoords = {28, 26, 21, 21, 23, 26, 28};
        for(int i = 0; i < xBodyCoords.length; i++){
            xBodyCoords[i] += getXPos() + 8;
            yBodyCoords[i] += getYPos() + 10;
        }
        for(int i = 0; i < xEyeCoords.length; i++){
            xEyeCoords[i] += getXPos() + 8;
            yEyeCoords[i] += getYPos() + 10;
        }
        g.setColor(getColor());
        g.fillPolygon(xBodyCoords, yBodyCoords, 22);
        setEyeColor(g);
        g.fillPolygon(xEyeCoords, yEyeCoords, 7);
    }
    
    public void setEyeColor(Graphics g){
        if(getColor() == Color.BLACK){
            g.setColor(Color.WHITE);
        }else
            g.setColor(Color.BLACK);
    }
}
