import java.awt.*;
import javax.swing.ImageIcon;

public class Pipe {
    private int x, y, w, h;
    private Image topPipeImage, bottomPipeImage;
    private boolean passed = false;

    public Pipe(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        topPipeImage = new ImageIcon(getClass().getResource("/Assets/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("/Assets/bottompipe.png")).getImage();
    }


    public void draw(Graphics g) {
        g.drawImage(topPipeImage, x, y - h - 100, w, h, null);
        g.drawImage(bottomPipeImage, x, y + 100, w, h, null);
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void update() {
        x -= 2;
    }

    public boolean checkCollision(Bird bird) {
        return bird.getX() + bird.getWidth() > x && bird.getX() < x + w &&
                (bird.getY() < y - 100 || bird.getY() + bird.getHeight() > y + 100);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }
}
