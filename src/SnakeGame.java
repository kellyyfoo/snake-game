import javax.swing.*;
        import java.awt.*;
        import java.awt.event.KeyEvent;
        import java.awt.event.KeyListener;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.Random;
        import java.util.ArrayList;
        import java.util.List;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private Timer t = new Timer (60, this);
    private List<Snake> snakes = new ArrayList<Snake>();
    private String snakeDir = "";
    private int round = 0;
    private Dot newDot;
    private int score = 0;
    private JLabel scoreLabel;

    public SnakeGame () {
        setFocusable(true);
        setLayout(null);
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setBounds(20, 20, 70, 30);

        add(scoreLabel);
        addKeyListener(this);

        t.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (round == 0) {
            //Draw dot
            g.setColor(Color.PINK);
            int xpos = (int) (Math.random() * 700 + 20);
            int ypos = (int) (Math.random() * 700 + 20);
            newDot = new Dot(xpos, ypos);
            g.fillRect(xpos, ypos, 10, 10);

            //Draw snake
            Snake newSnake = new Snake(700, 20);
            snakes.add(newSnake);
            snakes.add(new Snake(newSnake.getX() - 10, newSnake.getY()));
            snakes.add(new Snake(newSnake.getX() - 20, newSnake.getY()));
            round++;
        }

        //draw dot
        g.setColor(Color.PINK);
        g.fillRect(newDot.getX(), newDot.getY(), 10, 10);

        //draw snake
        g.setColor(Color.GREEN);
        for (int i = 0; i < snakes.size(); i++) {
            g.fillRect(snakes.get(i).getX(), snakes.get(i).getY(), 10, 10);
        }




    }

    public void actionPerformed (ActionEvent e) {
        if (round > 0) {

            if (snakeDir.equals("r")) {
                for (int i = snakes.size() - 1; i >= 0; i--) {
                    if (i == 0) {
                        snakes.get(0).setX(snakes.get(0).getX() + 10);
                    }
                    else {
                        snakes.get(i).setX(snakes.get(i - 1).getX());
                        snakes.get(i).setY(snakes.get(i - 1).getY());
                    }
                }
                repaint();
            }

            if (snakeDir.equals("l")) {
                for (int i = snakes.size() - 1; i >= 0; i--) {
                    if (i == 0) {
                        snakes.get(0).setX(snakes.get(0).getX() - 10);
                    }
                    else {
                        snakes.get(i).setX(snakes.get(i - 1).getX());
                        snakes.get(i).setY(snakes.get(i - 1).getY());
                    }
                }
                repaint();
            }
            if (snakeDir.equals("u")) {
                for (int i = snakes.size() - 1; i >= 0; i--) {
                    if (i == 0) {
                        snakes.get(0).setY(snakes.get(0).getY() - 10);
                    }
                    else {
                        snakes.get(i).setX(snakes.get(i - 1).getX());
                        snakes.get(i).setY(snakes.get(i - 1).getY());
                    }
                }
                repaint();
            }
            if (snakeDir.equals("d")) {
                for (int i = snakes.size() - 1; i >= 0; i--) {
                    if (i == 0) {
                        snakes.get(0).setY(snakes.get(0).getY() + 10);
                    }
                    else {
                        snakes.get(i).setX(snakes.get(i - 1).getX());
                        snakes.get(i).setY(snakes.get(i - 1).getY());
                    }
                }
                repaint();
            }

            //Checks if snake hits square
            if ((snakes.get(0).getX() - newDot.getX()) >= -9 && (snakes.get(0).getX() - newDot.getX()) <= 9 &&
                    (snakes.get(0).getY() - newDot.getY()) >= -9 && (snakes.get(0).getY() - newDot.getY()) <= 9) {
                drawNewDot();
                score++;
                scoreLabel.setText("Score: " + score);
                if (snakeDir.equals("r")) {
                    snakes.add(new Snake(snakes.get(snakes.size() - 1).getX() - 10, snakes.get(snakes.size() - 1).getY()));
                }
                if (snakeDir.equals("l")) {
                    snakes.add(new Snake(snakes.get(snakes.size() - 1).getX() + 10, snakes.get(snakes.size() - 1).getY()));
                }
                if (snakeDir.equals("u")) {
                    snakes.add(new Snake(snakes.get(snakes.size() - 1).getX(), snakes.get(snakes.size() - 1).getY() - 10));
                }
                if (snakeDir.equals("d")) {
                    snakes.add(new Snake(snakes.get(snakes.size() - 1).getX(), snakes.get(snakes.size() - 1).getY() + 10));
                }
            }

            //Checks if snake hits border
            if (snakes.get(0).getX() > 750 || snakes.get(0).getX() < 0 || snakes.get(0).getY() > 750 || snakes.get(0).getY() < 0) {
                gameOver();
            }

            //Check if snake collides with itself
            for (int i = 1; i < snakes.size() - 1; i++) {
                if ((snakes.get(0).getX() - snakes.get(i).getX()) >= -9 && (snakes.get(0).getX() - snakes.get(i).getX()) <= 9 &&
                        (snakes.get(0).getY() - snakes.get(i).getY()) >= -9 && (snakes.get(0).getY() - snakes.get(i).getY()) <= 9) {
                    gameOver();
                }
            }
        }
    }

    public void keyPressed (KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT)
        {
            if (!snakeDir.equals("l"))
                snakeDir = "r";
        }
        if (key == KeyEvent.VK_LEFT)
        {
            if (!snakeDir.equals("r"))
                snakeDir = "l";
        }
        if (key == KeyEvent.VK_UP)
        {
            if (!snakeDir.equals("d"))
                snakeDir = "u";
        }
        if (key == KeyEvent.VK_DOWN)
        {
            if (!snakeDir.equals("u"))
                snakeDir = "d";
        }
    }

    public void keyTyped (KeyEvent e)
    {
    }

    public void keyReleased (KeyEvent e)
    {
    }

    public void drawNewDot () {
        int xpos = (int) (Math.random() * 700 + 20);
        int ypos = (int) (Math.random() * 700 + 20);
        newDot = new Dot(xpos, ypos);
    }

    public void gameOver() {
        t.stop();
    }
}
