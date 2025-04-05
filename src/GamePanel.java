import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Image background;
    private Bird bird;
    private Timer timer;
    private ArrayList<Pipe> pipes;
    private boolean gameOver = false;
    private int score = 0;

    public GamePanel(){
        this.setFocusable(true);
        this.addKeyListener(this);
        background = new ImageIcon(getClass().getResource("/Assets/flappybirdbg.png")).getImage();

        bird = new Bird(100, 250, 34, 24);

        pipes = new ArrayList<>();
        pipes.add(new Pipe(600, (int) (Math.random() * 360) + 100, 50, 400));

        timer = new Timer(20, this);
        timer.start();
    }

    private void restartGame() {
        bird = new Bird(100, 250, 34, 24);
        pipes.clear();
        pipes.add(new Pipe(600, (int) (Math.random() * 300) + 100, 50, 300));
        score = 0;
        gameOver = false;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        bird.draw(g);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", getWidth() / 2 - 130, getHeight() / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", getWidth() / 2 - 90, getHeight() / 2 + 40);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.update();

        for (Pipe pipe : pipes) {
            pipe.update();
        }

        for (Pipe pipe : pipes) {
            if (pipe.checkCollision(bird)) {
                gameOver = true;
                break;
            }
            if (!pipe.isPassed() && pipe.getX() + pipe.getWidth() < bird.getX()) {
                score++;
                pipe.setPassed(true);
            }
        }

        if (pipes.get(pipes.size() - 1).getX() < getWidth() - 200) {
            pipes.add(new Pipe(getWidth(), (int) (Math.random() * 360) + 100, 50, 400));
        }

        if (pipes.get(0).getX() + pipes.get(0).getWidth() < 0) {
            pipes.remove(0);
        }

        if (bird.getY() + bird.getHeight() > getHeight() || bird.getY() < 0) {
            gameOver = true;
        }

        if (gameOver) {
            timer.stop();
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            bird.jump();
        }
        else if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
