import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Animation {
    protected int x, y;
    protected int frame = 0;
    protected int frameCount;
    protected IAFinish listener;
    protected static Image image = null;

    // 静态代码块加载资源，子类共用
    static {
        File f = new File("insect_sprite.png");
        try {
            image = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Animation(int x, int y, int frameCount) {
        this.x = x;
        this.y = y;
        this.frameCount = frameCount;
    }

    public void addListener(IAFinish listener) {
        this.listener = listener;
    }

    public boolean isFinish() {
        if (frame >= frameCount) {
            if (listener != null) {
                listener.finishPerformed();
            }
            return true;
        }
        return false;
    }

    // 抽象方法，强制子类实现各自的绘制逻辑
    public abstract void draw(Graphics g);
}