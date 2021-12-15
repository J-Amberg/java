package Pieces;

import Interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics;

/**
 * @author jordan.amberg
 */
public class Tile implements Drawable{
    
    private boolean isOccupied;
    private Color color;
    private int rank;
    private int file;
    
    public Tile(boolean isOccupied, Color color, int rank, int file){
        this.isOccupied = isOccupied;
        this.color = color;
        this.rank = rank;
        this.file = file;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(rank * 100, file * 100, 100, 100);
    }
    
    
    public boolean getOccupied(){
        return isOccupied;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getRank(){
        return rank;
    }
    
    public int getFile(){
        return file;
    }
    
    public void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public void setRank(int rank){
        this.rank = rank;
    }
    
    public void setFile(int file){
        this.file = file;
    }
}
