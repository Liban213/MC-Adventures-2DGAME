package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, main.KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        screenX = gp.screenWidth /2 - (gp.tileSize /2);
        screenY = gp.screenHeight /2;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

    }
    public void setDefaultValues() {

        worldX = gp.tileSize *23;
        WorldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    


    }

    public void update () {
        boolean isMoving = false;
        if(keyH.upPressed == true){
            direction = "up";
            isMoving = true;
            
        }
        if(keyH.downPressed == true){
            direction = "down";
            isMoving = true;
            
            
        }
        if(keyH.rightPressed == true){
            direction = "right";
            isMoving = true;
           
        }
        if(keyH.leftPressed == true){
            direction = "left";
            isMoving = true;
            
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        if(!collisionOn && isMoving){
            switch(direction){
                case "up":
                    WorldY -= speed;
                    break;
                case "down":
                    WorldY += speed;
                    break;
                case "right":
                     worldX += speed;
                    break;
                
                case "left":
                    worldX -= speed;
                    break;            
                            
                }
        }

        // Update the sprite Counter and once it hits 10 rotate the animations
        spriteCounter ++;

        if (spriteCounter > 10){

            if(spriteNum == 1){
                spriteNum = 2;
            }

            if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

 
    }


    public void draw(Graphics2D g2){

        BufferedImage image =  null;
        
        switch(direction){
            case "up":
                if(spriteNum == 1)    
                    image = up1;
                if(spriteNum == 2)    
                    image = up2;
                
                break;
            case "down":
                if(spriteNum == 1)    
                    image = down1;
                if(spriteNum == 2)    
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1)    
                    image = left1;
                if(spriteNum == 2)    
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1)    
                    image = right1;
                if(spriteNum == 2)    
                    image = right2;
                break;

        }

        g2.drawImage(image, screenX,screenY, gp.tileSize, gp.tileSize, null);

    }

    public void getPlayerImage(){

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/hero_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/hero_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/hero_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/hero_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/hero_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/hero_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/hero_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/res/player/hero_left_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
