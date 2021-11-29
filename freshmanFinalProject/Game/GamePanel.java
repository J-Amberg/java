/*
 * To change this license header, choose License Headers in Project Properties
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package Game;

import Controller.KeyboardController;
import GameObjects.Beam;
import GameObjects.Bullet;
import GameObjects.Enemy;
import GameObjects.Shield;
import GameObjects.Ship;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author 
 */
public class GamePanel extends JPanel
{
    // These are for handling the frame rate of the game and player controls
    // You should pass the controller to any objects you create that will
    //  be under keyboard control
    private Timer gameTimer; 
    private KeyboardController controller; 
    
    // Controls size of game window and framerate
    // You can adjust these if you want to use different values 
    private final int gameWidth = 600; 
    private final int gameHeight = 720; 
    private final int framesPerSecond = 60; 
    private int score;
    private int lives;
    private boolean newGame = false;
    private Ship player;
    private ArrayList<Beam> playerFire;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> enemyFire;
    private ArrayList<Shield> shields;
    
    //handles setting up the instance variables and restarting the game
    public final void setupGame(){
        newGame = true;
        player = new Ship(300,650,Color.RED,controller); 
        playerFire = new ArrayList<>();
        enemies = new ArrayList<>();
        for(int i = 100; i < 551; i+= 50){ //creates array of 11x6 enemies
            for(int j = 80; j < 370; j+= 55){
                Color color = Color.BLUE;
                Random rand = new Random();
                int temp = 0;
                temp = rand.nextInt(12);
                System.out.println(temp);
                switch (temp) { //randomly picks the color of the enemy
                    case 0:
                        color = Color.BLUE;
                        break;
                    case 1:
                        color = Color.CYAN;
                        break;
                    case 2:
                        color = Color.DARK_GRAY;
                        break;
                    case 3:
                        color = Color.GRAY;
                        break;
                    case 4:
                        color = Color.GREEN;
                        break;
                    case 5:
                        color = Color.LIGHT_GRAY;
                        break;
                    case 7:
                        color = Color.ORANGE;
                        break;
                    case 8:
                        color = Color.PINK;
                        break;
                    case 9:
                        color = Color.RED;
                        break;
                    case 10:
                        color = Color.WHITE;
                        break;
                    case 11:
                        color = Color.YELLOW;
                        break;
                }
                enemies.add(new Enemy(i,j,color,1,0));
            }
        }
        shields = new ArrayList<>();
        for(int i = 20; i < 600; i+= 100){ //creates 5 shields
            shields.add(new Shield(i, 600, Color.GRAY, 40, 20));
        }
        enemyFire = new ArrayList<>();
        lives = 3; //used to restart the game 
        score = 0;
    }
    //used to draw all of the objects and set the background to black
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        Rectangle screenBound = new Rectangle(0,0,600,720);
        g2.fill(screenBound);
        drawObjects(g);
        playerShooting(g2);
        enemyShoots();
        displayScoreboard(g2); //displays players score and lives
    }
    
    public void updateGameState(int frameNumber){  //calls functions necessary for keeping the game in order
        killEnemies(); 
        hitPlayer();
        moveObjects();
        hitShield();
        if(frameNumber % 100 == 0){ //every 100 frames, switches directions and bumps enemies down
            for(Enemy enemy: enemies){
                enemy.setXVelocity(-enemy.getXVelocity());
                enemy.setYPosition(enemy.getYPosition()+(frameNumber/30)); //speeds up as the game goes on
            }
        }
    }
    public void enemyShoots(){//determines if  the enemy will shoot at the player randomly
        for(Enemy enemy: enemies){ 
            if(enemy.getXPosition() == player.getXPosition()){
                Random rand = new Random();
                int temp = rand.nextInt(7);
                if(temp % 7 == 0){
                    enemyFire.add(new Bullet(enemy.getXPosition()+25, enemy.getYPosition()+35, Color.MAGENTA, 0, 15, 6));
                }
            }
        }
    }
    public void displayScoreboard(Graphics2D g2){ //displays score and lives, and checks for wins and losses
        Font font = new Font("Serif", Font.PLAIN, 16);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + String.valueOf(score), 50, 50);
        checkLose(g2, font); 
        checkWin(g2, font);
    }
    public void checkLose(Graphics2D g2, Font font){
        if(shields.isEmpty()){
            playAgain(g2, font);
        }
        if(lives > 2){ //sets color of lives to green, orange, or red based on how many are remaining
            g2.setColor(Color.GREEN);
        }else if(lives == 2){
            g2.setColor(Color.ORANGE);
        }else if(lives == 1){
            g2.setColor(Color.RED);
        }else if(lives == 0){ //ends the game if the player has no lives
            g2.setColor(Color.WHITE);
            Font font2 = new Font("Serif", Font.PLAIN, 80);
            g2.setFont(font2);
            g2.drawString("GAME OVER", 70, 400); 
            enemies.clear();
            enemyFire.clear();
            shields.clear();
            playAgain(g2, font);
        }
        g2.setFont(font);
        g2.drawString("Lives: " + String.valueOf(lives), 50, 70);  
    }
    public void checkWin(Graphics2D g2, Font font){ //checks for the win condition, and displays win text
        if(score == 60){
            g2.setColor(Color.WHITE);
            Font font2 = new Font("Serif", Font.PLAIN, 80);
            g2.setFont(font2);
            g2.drawString("YOU WIN", 100, 400);
            shields.clear();
            playAgain(g2, font);
        }
    }
    public void playAgain(Graphics2D g2, Font font){ //handles whether or not the player will play again
        g2.setFont(font);
        g2.drawString("Would you like to play again?", 50, 600);
        g2.drawString("Up key for yes, Down key for no", 50, 700);
        if(controller.getDownKeyStatus()){
            System.exit(0);
        }else if(controller.getUpKeyStatus()){
            setupGame();
        }
    }

    public void playerShooting(Graphics2D g2){ //controls the player's shooting abilities
        if(controller.getSpaceKeyStatus()){ //adds a beam to the arraylist if space is pressed
            if(playerFire.size() <= 5){
                playerFire.add(new Beam(player.getXPosition()+14,player.getYPosition(),Color.GREEN,0,-10,2,20));
            }else if(playerFire.size() >= 5){ //clears the array if more than 5 beams are active
                playerFire.clear();
                g2.drawString("Anti-Spam", 300, 500);
            }
            controller.resetController();
        }
    }
    public void drawObjects(Graphics g){ //draws beams, bullets, shields, player, and enemies
        for(Enemy enemy: enemies){
            enemy.draw(g);
        }for(Beam beam: playerFire){
            beam.draw(g);
        }for(Bullet bullet: enemyFire){
            bullet.draw(g);
        }for(Shield shield: shields){
            shield.draw(g);
        }
        player.draw(g);
    }
    public void moveObjects(){ //moves player, enemies, beams, bullets
        for(Beam beam: playerFire){ 
            beam.move();}
        for(Enemy enemy: enemies){ 
            enemy.move();}
        for(Bullet bullet: enemyFire){
            bullet.move();}
        player.move(); 
    }
    public void hitShield(){ 
        Iterator bulletItr = enemyFire.iterator();
        Bullet removeBullet = new Bullet(0, 0, Color.RED, 0, 0, 0);
        while(bulletItr.hasNext()){ //checks if the enemy fire hits the shield and clears it
            Bullet x = (Bullet)bulletItr.next();
            for(Shield shield: shields){
                if(shield.isColliding(x)){
                    removeBullet = x;
                }
            }        
        }
        for(Bullet bullet: enemyFire){
            if(bullet.equals(removeBullet)){
                enemyFire.remove(bullet);
                break;
            }   
        }
        Iterator beamItr = playerFire.iterator();
        Beam removeBeam = new Beam(0, 0, Color.RED, 0, 0, 0, 0);
        while(beamItr.hasNext()){ //checks if the players fire hits the shield and clears it
            Beam x = (Beam)beamItr.next();
            for(Shield shield: shields){
                if(shield.isColliding(x)){
                    removeBeam = x;
                }
            }        
        }
        for(Beam beam: playerFire){
            if(beam.equals(removeBeam)){
                playerFire.remove(beam);
                break;
            }   
        }
    }
    public void hitPlayer(){ //if player is hit, lives go down
        Iterator bulletItr = enemyFire.iterator();
        Bullet removeBullet = new Bullet(0, 0, Color.RED, 0, 0, 0);
        while(bulletItr.hasNext()){
            Bullet x = (Bullet)bulletItr.next();
            if(player.isColliding(x)){
                lives--;
                removeBullet = x;
            }
        }
        for(Bullet bullet: enemyFire){ //removes the bullet if it hits the player
            if(bullet.equals(removeBullet)){
                enemyFire.remove(bullet);
                break;
            }   
        }
    }
    public void killEnemies(){ //removes enemies and the beams that hit them
        Iterator enemyItr = enemies.iterator();
        Beam removeBeam = new Beam(0, 0, Color.RED, 0, 0, 0, 0);
        while(enemyItr.hasNext()){
            Enemy x = (Enemy)enemyItr.next();
            for(Beam beam: playerFire){
                if(beam.isColliding(x)){
                    score++;
                    enemyItr.remove();
                    removeBeam = beam;
                    break;
                }
            }
        }
        for(Beam beam: playerFire){
            if(beam.equals(removeBeam)){
                playerFire.remove(beam);
                break;
            }
        }
    } 
    
   
    /**
     * Constructor method for GamePanel class.
     * It is not necessary for you to modify this code at all
     */
    public GamePanel()
    {
        // Set the size of the Panel
        this.setSize(gameWidth, gameHeight);
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.BLACK);

        
        // Register KeyboardController as KeyListener
        controller = new KeyboardController(); 
        this.addKeyListener(controller); 
        
        // Call setupGame to initialize fields
        this.setupGame(); 
       
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    /**
     * Method to start the Timer that drives the animation for the game.
     * It is not necessary for you to modify this code unless you need to in 
     *  order to add some functionality. 
     */
    public void start()
    {
        // Set up a new Timer to repeat based on the set framesPerSecond
        gameTimer = new Timer(1000 / framesPerSecond, new ActionListener() {

            // Tracks the number of frames that have been produced.
            // May be useful for limiting action rates
            private int frameNumber = 0; 
                        
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Update the game's state and repaint the screen
                if(newGame == true){
                    frameNumber = 0;
                    newGame = false;
                }
                updateGameState(frameNumber++);
                repaint();  
            }
        });
        
        gameTimer.setRepeats(true);
        gameTimer.start();
    }
}
