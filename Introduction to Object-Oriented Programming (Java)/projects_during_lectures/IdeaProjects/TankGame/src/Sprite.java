import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Sprite {
    public static final int SIZE = 34;
    protected int x;
    protected int y;
    protected int v = 2;  // 坦克的速度
    protected Direction dir=Direction.UP;
    protected static Image image=null;
    static {
        File f = new File("insect_sprite.png");

        try{
            image = ImageIO.read(f);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public int getx() {return x;}
    public int gety() {return y;}
    public void setx(int x) {this.x = x;}
    public void sety(int y) {this.y = y;}
    public Sprite(int x,int y,Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public void move() {
        switch(dir){
            case LEFT:	x -= v;break;
            case RIGHT:	x += v;break;
            case UP:	y -= v;break;
            case DOWN:	y += v;break;
        }
    }
    public abstract void draw(Graphics g);
    public boolean isClollision(Sprite s) {
        int dist1 = (x - s.x) * (x-s.x);
        int dist2 = (y - s.y) * (y-s.y);
        if(Math.sqrt(dist1 + dist2) < SIZE) return true;
        else return false;
    }
}
