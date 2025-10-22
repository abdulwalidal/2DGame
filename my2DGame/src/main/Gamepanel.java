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
    double drawInterval = 1000000000 / FPS; // time per frame (for 60 FPS)
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    while (gameThread != null) {
        currentTime = System.nanoTime();

        // How much time passed since the last frame
        delta += (currentTime - lastTime) / drawInterval;
        timer += (currentTime - lastTime);
        lastTime = currentTime;

        // Update and draw when enough time has passed
        if (delta >= 1) {
            update();
            repaint();
            delta--;
            drawCount++;
        }

    //  show FPS every second (for testing)
        if (timer >= 1000000000) {
            System.out.println("FPS: " + drawCount);
            drawCount = 0;
            timer = 0;
        }
    }
    }


    // @Override
    // public void run() {

    //     double drawInternal = 1000000000 / FPS;  // 0.01666
    //     double nextDrawTime = System.nanoTime() + drawInternal;

       
    //     while (gameThread != null) {

    //         update();

    //         repaint();

             
    //          try {

    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;  
    //             if(remainingTime < 0) {
    //                 remainingTime = 0;
    //             }
    //               Thread.sleep((long) remainingTime);  
    //               nextDrawTime += drawInternal;  
    //          } catch (Exception e) {
    //             e.printStackTrace();
    //          }
    //     }   
    // }
     


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

