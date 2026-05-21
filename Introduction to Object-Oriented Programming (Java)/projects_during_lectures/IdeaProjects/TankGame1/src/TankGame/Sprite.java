package TankGame;
import TankGame.Direction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {
    public static final int SIZE = 34;
    protected int x;
    protected int y;
    protected int v = 2;  // 坦克的速度
    protected Direction dir=Direction.UP;
    protected Image image=null;

    public Sprite(int x,int y,Direction dir) {
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

    }

    public Bullet fire() {
        return new Bullet(x,y,dir);
    }
}