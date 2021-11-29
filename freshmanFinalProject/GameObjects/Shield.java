/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class Shield extends GameObject{
    
    private int width;
    private int height;
    
    public Shield(int xPosition, int yPosition, Color color, int width, int height) {
        super(xPosition, yPosition, color);
        this.width = width;
        this.height = height;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    

    @Override
    public Rectangle getBounds() {
        Rectangle rect = new Rectangle(getXPosition(),getYPosition(),width,height);
        return rect;
    }

    @Override
    public void draw(Graphics g) { //draws the shield
        g.setColor(getColor());
        g.fillRect(getXPosition(),getYPosition(),width,height);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getXPosition()+3, getYPosition()+3, width-6, height-6);
        g.setColor(Color.BLACK);
        g.fillRect(getXPosition()+7,getYPosition()+7,width-14,height-14);
    }
    
}
