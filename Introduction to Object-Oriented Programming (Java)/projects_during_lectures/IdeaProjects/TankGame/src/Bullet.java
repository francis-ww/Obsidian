import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bullet extends Sprite{
    public Bullet(int ax, int ay,Direction adir) {
        super(ax,ay,adir);
        v = 6;
    }

    public void draw(Graphics g) {
        int x1,y1,x2,y2;
        int x3,y3,x4,y4;
        x1 = x- Tank.SIZE/2;	y1 = y- Tank.SIZE/2;
        x2 = x1 + Tank.SIZE;	y2 = y1 + Tank.SIZE;

        x3 = Tank.SIZE*5;     	y3 = Tank.SIZE*6;
        x4 = x3 + Tank.SIZE;  	y4 = y3+Tank.SIZE;

        g.drawImage(image, x1, y1, x2, y2, x3, y3, x4, y4, null);
    }
    public AnimationBE explosive() {
        return  new AnimationBE(x,y);
    }

}
