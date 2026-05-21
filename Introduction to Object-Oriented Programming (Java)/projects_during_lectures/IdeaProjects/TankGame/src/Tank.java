import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Tank  extends Sprite{
    protected int flag=0;
    public Tank(int x,int y,Direction dir) {
        super(x,y,dir);
    }
    public void setDir(Direction dir) { this.dir = dir;}
    public Bullet fire() {
        return new Bullet(x,y,dir);
    }
}
