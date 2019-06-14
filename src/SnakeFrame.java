import java.awt.*;
import javax.swing.*;

public class SnakeFrame {
    public static void main (String [] args) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame ("Snake");
        SnakeGame panel = new SnakeGame ();
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.pack();
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);


    }
}
