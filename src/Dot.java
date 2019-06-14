import javax.swing.*;
import java.awt.*;

public class Dot extends JPanel {
    private int x, y;

    public Dot (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
