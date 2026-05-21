import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class NPCTank extends Tank{

    public NPCTank(int x,int y,Direction dir) {
        super(x,y,dir);
    }

    public void draw(Graphics g) {
        int x1,y1,x2,y2;
        int x3,y3,x4,y4;
        x1 = x- Tank.SIZE/2;	y1 = y- Tank.SIZE/2;
        x2 = x1 + Tank.SIZE;	y2 = y1 + Tank.SIZE;

        x3 = Tank.SIZE*2*dir.ordinal()+flag*Tank.SIZE;     	y3 = 34;
        x4 = x3+Tank.SIZE;  	y4 = y3+Tank.SIZE;

        flag = (flag+1) % 2;

        g.drawImage(image, x1, y1, x2, y2, x3, y3, x4, y4, null);
    }
    public AnimationTE explosive() {
        return  new AnimationTE(x,y);
    }

}
