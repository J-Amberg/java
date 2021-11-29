/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Enemy extends MovingGameObject{

    public Enemy(int xPosition, int yPosition, Color color, int xVelocity, int yVelocity) {
        super(xPosition, yPosition, color, xVelocity, yVelocity);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle rect = new Rectangle(getXPosition(), getYPosition(), 50, 50);
        return rect;
    }

    @Override
    public void draw(Graphics g) { //draws the enemy
        g.setColor(getColor());
        g.fillOval(getXPosition(),getYPosition(), 50, 50);
        g.setColor(Color.BLACK);
        g.fillOval(getXPosition() + 12, getYPosition() + 17, 7, 7);
        g.fillOval(getXPosition()+ 32, getYPosition() + 17, 7, 7);
        g.fillOval(getXPosition()+ 19, getYPosition() + 32, 12, 12);
    }
    
}
