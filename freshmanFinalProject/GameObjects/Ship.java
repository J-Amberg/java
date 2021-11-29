/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Controller.KeyboardController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class Ship extends ControlledGameObject {

    public Ship(int xPosition, int yPosition, Color color, KeyboardController control) {
        super(xPosition, yPosition, color, control);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle rect = new Rectangle(getXPosition()-2,getYPosition()-3, 36, 53);
        return rect;
    }

    @Override
    public void draw(Graphics g) { //draws the player
        g.setColor(getColor());
        g.fillOval(getXPosition(),getYPosition(),30,50);
        g.setColor(Color.GRAY);
        g.fillOval(getXPosition()+5,getYPosition()+15,20,20);
        g.setColor(Color.orange);
        g.fillRect(getXPosition()+5,getYPosition()-3,5,10);
        g.fillRect(getXPosition()+20,getYPosition()-3,5,10);
    }

    @Override
    public void move() { //moves based on if the user is pressing the left or right key
        if(getKeyboardController().getRightKeyStatus()){
            setXPosition(getXPosition()+2);
        }
        if(getKeyboardController().getLeftKeyStatus()){
            setXPosition(getXPosition()-2);
        }
        if(getXPosition() > 600){
            setXPosition(0);
        }
        if(getXPosition() < 0){
            setXPosition(600);
        }
    } 
}
