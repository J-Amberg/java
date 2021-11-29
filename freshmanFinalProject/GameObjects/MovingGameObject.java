/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Interfaces.Moveable;
import java.awt.Color;

/**
 *
 * @author admin
 */
public abstract class MovingGameObject extends GameObject implements Moveable{
    
    private int xVelocity;
    private int yVelocity;
    
    public MovingGameObject(int xPosition, int yPosition, Color color, int xVelocity, int yVelocity) {
        super(xPosition, yPosition, color);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }
    
    public int getXVelocity(){
        return xVelocity;
    }
    public int getYVeloicty(){
        return yVelocity;
    }
    public void setXVelocity(int xVelocity){
        this.xVelocity = xVelocity;
    }
    public void setYVelocity(int yVelocity){
        this.yVelocity = yVelocity;
    }
    @Override
    public void move(){
        setXPosition((getXPosition()+xVelocity) % 600); //adjusts the x position based on the velocity
        setYPosition(getYPosition()+yVelocity); //adjusts the y position based on the velocity
    }
}
