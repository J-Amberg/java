package Pieces;

import Interfaces.Drawable;
import java.awt.Color;

/**
 * @author jordan.amberg
 */
public abstract class Piece implements Drawable{
    
    private Tile tile;
    private Color color;
    private int xPos; 
    private int yPos;
    
    public Piece(Tile tile, Color color){
        this.tile = tile;
        this.color = color;
        xPos = getTile().getRank() * 100;
        yPos = getTile().getFile() * 100;
        tile.setOccupied(true);
    }
    
    public Tile getTile(){
        return tile;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getXPos(){
        return xPos;
    }
    
    public int getYPos(){
        return yPos;
    }
    
    public void setTile(Tile tile){
        this.tile = tile;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public void setXPos(int xPos){
        this.xPos = xPos;
    }
    
    public void setYPos(int yPos){
        this.yPos = yPos;
    }
}
