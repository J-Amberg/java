/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Controller.KeyboardController;
import Interfaces.Moveable;
import java.awt.Color;

/**
 *
 * @author admin
 */
public abstract class ControlledGameObject extends GameObject implements Moveable{
    
    private KeyboardController control;
    
    public ControlledGameObject(int xPosition, int yPosition, Color color, KeyboardController control) {
        super(xPosition, yPosition, color);
        this.control = control; 
    }
    
    public KeyboardController getKeyboardController(){
        return control;
    }
}
