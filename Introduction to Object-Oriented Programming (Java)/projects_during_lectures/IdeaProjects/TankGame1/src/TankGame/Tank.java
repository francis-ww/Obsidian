package TankGame;
import TankGame.Direction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank {
    public static final int SIZE = 34;
    protected int x;
    protected int y;
    protected int v = 2;  // 坦克的速度
    protected Direction dir=Direction.UP;
    protected Image image=null;
    protected int flag=0;

    public Tank(int x,int y,Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;

        File f = new File("C:\\Users\\wongh\\Documents\\Obsidian Vault\\Introduction to Object-Oriented Programming (Java)\\projects_during_lectures\\IdeaProjects\\TankGame\\src\\TankGame\\insect_sprite.png");

        try{
            image = ImageIO.read(f);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Tank() {
    }

    public void setDir(Direction dir) { this.dir = dir;}
    public void move() {
        switch(dir){
            case LEFT:    x -= v*2;break;
            case RIGHT:    x += v*2;break;
            case UP:    y -= v*2;break;
            case DOWN:    y += v*2;break;
        }
    }
    public void draw(Graphics g) {
        int x1,y1,x2,y2;
        int x3,y3,x4,y4;
        x1 = x- Tank.SIZE/2;    y1 = y- Tank.SIZE/2;
        x2 = x1 + Tank.SIZE;    y2 = y1 + Tank.SIZE;

        x3 = Tank.SIZE*2*dir.ordinal()+flag*Tank.SIZE;         y3 = 0;
        x4 = x3+Tank.SIZE;      y4 = y3+Tank.SIZE;

        flag = (flag+1) % 2;

        g.drawImage(image, x1, y1, x2, y2, x3, y3, x4, y4, null);
    }

    public Bullet fire() {
        return new Bullet(x,y,dir);
    }
}