package TankGame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {

    private int x;
    private int y;
    private int v;
    private Direction dir=Direction.UP;
    private Image image;

    public Bullet(int ax, int ay,Direction adir) {
        x = ax;
        y = ay;
        dir = adir;
        v = 20;
        File f = new File("C:\\Users\\wongh\\Documents\\Obsidian Vault\\Introduction to Object-Oriented Programming (Java)\\projects_during_lectures\\IdeaProjects\\TankGame\\src\\TankGame\\insect_sprite.png");
        try{
            image = ImageIO.read(f);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void move() {

        switch(dir){
            case LEFT:    x -= v;break;

            case RIGHT:    x += v;break;
            case UP:    y -= v;break;
            case DOWN:    y += v;break;
        }
    }
    public void draw(Graphics g) {
        int x1,y1,x2,y2;
        int x3,y3,x4,y4;
        x1 = x- Tank.SIZE/2;    y1 = y- Tank.SIZE/2;
        x2 = x1 + Tank.SIZE;    y2 = y1 + Tank.SIZE;

        x3 = Tank.SIZE*5;         y3 = Tank.SIZE*6;
        x4 = x3 + Tank.SIZE;      y4 = y3+Tank.SIZE;

        g.drawImage(image, x1, y1, x2, y2, x3, y3, x4, y4, null);
    }

}