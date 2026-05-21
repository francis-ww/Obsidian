import java.awt.Graphics;

public class AnimationTE extends Animation {
    public AnimationTE(int x, int y) {
        super(x, y, 3);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x - 17, y - 17, x + 17, y + 17,
                (20 + frame) * 34, 4 * 34,
                (21 + frame) * 34, 5 * 34, null);
        ++frame;
    }
}