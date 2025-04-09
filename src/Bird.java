import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bird {
    private int x, y;
    private int w;
    private int h;
    private Image image;

    private double velocity  = 0;
    private final double gravity = 1;
    private final double jumpStrength = -12;

    public Bird(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.image = new ImageIcon(getClass().getResource("/Assets/flappybird.png")).getImage();
    }


    public void draw(Graphics g) {
        g.drawImage(image, x, y, w, h, null);
    }

    public void update(){
        velocity += gravity;
        y += velocity;
    }

    public void jump() {
        velocity = jumpStrength;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }
}
