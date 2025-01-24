package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    
    public final int maxWorldCol = 50;
    public final int maxWorldrow = 50;
    public final int worldWidth = tileSize *maxWorldCol;
    public final int worldHeight = tileSize * maxWorldrow;
    //Default position

    int FPS = 60;




    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // For better rendering perfomance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread  = new Thread (this);
        gameThread.start();


    }

    public void run() {

        // Divids the 1 billion nano seconds by 60 seconds
        double drawInterval = 1000000000/ FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            // Current frames per nano second 
            // Need to check the time so it updates only 60 frames/ sepm
             currentTime = System.nanoTime();
             delta += (currentTime - lastTime) / drawInterval;
             lastTime = currentTime;

             if (delta >= 1){
                 //Update the player postion
                 update();
                 //Update the screen with charachter new Postion
                 repaint();
                delta --;
             }
            

        }
    
    }

    public void update(){

        player.update();
    }

    public void paintComponent( Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        tileM.draw(g2);
        
        player.draw(g2);
        
        g2.dispose();
    }



}
