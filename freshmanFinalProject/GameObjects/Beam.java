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
public class Beam extends MovingGameObject{
   
    private int width;
    private int height;
    
    public Beam(int xPosition, int yPosition, Color color, int xVelocity, int yVelocity, int width, int height) {
        super(xPosition, yPosition, color, xVelocity, yVelocity);
        this.width = width;
        this.height = height;
    }

    @Override //draws the beam
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getXPosition(), getYPosition(), width, height);
    }

    @Override
    public Rectangle getBounds() { //returns the rectangle of bounds
        Rectangle rect = new Rectangle(getXPosition(), getYPosition()+10, width, height);
        return rect;
    }
    
}
