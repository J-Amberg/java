/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Interfaces.Drawable;
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public abstract class GameObject implements Drawable{
    private int xPosition;
    private int yPosition;
    private Color color;
    
    public GameObject(int xPosition, int yPosition, Color color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }
    public abstract Rectangle getBounds();
    //accessor methods
    
    public int getXPosition(){
        return xPosition;
    }
    public int getYPosition(){
        return yPosition;
    }
    public Color getColor(){
        return color;
    }
    //mutator methods
    public void setXPosition(int xPosition){
        this.xPosition = xPosition;
    }
    public void setYPosition(int yPosition){
        this.yPosition = yPosition;
    }
    public void setColor(Color color){
        this.color = color;
    }
    
    public boolean isColliding(GameObject other){ //checks if the bounds of two objects intersects
        return getBounds().intersects(other.getBounds());
    }
    
    
}
