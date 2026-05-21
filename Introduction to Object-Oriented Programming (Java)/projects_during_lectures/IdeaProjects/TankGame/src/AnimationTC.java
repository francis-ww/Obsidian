import java.awt.Graphics;

public class AnimationTC extends Animation {
    public AnimationTC(int x, int y) {
        super(x, y, 15);
    }

    @Override
    public void draw(Graphics g) {
        int tmp = frame % 3;
        g.drawImage(image, x - 17, y - 17, x + 17, y + 17,
                (12 + tmp) * 34, 7 * 34,
                (13 + tmp) * 34, 8 * 34, null);
        ++frame;
    }
}