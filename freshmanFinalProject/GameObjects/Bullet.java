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
public class Bullet extends MovingGameObject{
    
    private int diameter;
    
    public Bullet(int xPosition, int yPosition, Color color, int xVelocity, int yVelocity, int diameter) {
        super(xPosition, yPosition, color, xVelocity, yVelocity);
        this.diameter = diameter;
    }

    @Override //returns the bounds of the bullet
    public Rectangle getBounds() {
        Rectangle rect = new Rectangle(getXPosition(),getYPosition(),diameter, diameter);
        return rect;
    }

    @Override //draws the bullet
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getXPosition(),getYPosition(), diameter, diameter);
    }
    
}
