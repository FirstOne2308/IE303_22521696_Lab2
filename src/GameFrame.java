import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("22521696_lab02");
        setSize(360, 640);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new GamePanel());
        setVisible(true);
    }
}
