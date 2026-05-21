import java.awt.Graphics;

public class AnimationBE extends Animation {
    public AnimationBE(int x, int y) {
        super(x, y, 4); // 调用父类构造器，设置 frameCount 为 4
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x - 17, y - 17, x + 17, y + 17,
                (16 + frame) * 34, 4 * 34,
                (17 + frame) * 34, 5 * 34, null);
        ++frame;
    }
}