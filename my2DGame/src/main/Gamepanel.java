package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable {
    final int originalTilesSize = 16;
    final int scale = 3;

    final int tileSize = originalTilesSize * scale; // 48x48
    final int maxScreencol = 16;
    final int maxScreenrow = 12;
    final int screenWidth = tileSize * maxScreencol; // 768
    final int screenHeight = tileSize * maxScreenrow; // 576

    // FPS  
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    // Set Default Player's Positions

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;



    public Gamepanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
    }


    public void startgameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInternal = 10000000/FPS; // 0.01666
        double nextDrawTime = System.nanoTime() + drawInternal;

       
        while (gameThread != null) {
    
        }   
    }
       public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if(keyH.leftPressed==true) {
            playerX -= playerSpeed;
        } else if(keyH.rightPressed==true) {
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }

    
}
