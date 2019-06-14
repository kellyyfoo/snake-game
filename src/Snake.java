import javax.swing.*;
import java.awt.*;

public class Snake extends JPanel{
    private int x, y;

    public Snake (int x, int y) {
        this.x = x;
        this.y = y;
    }

        public int getX () {
            return x;
        }

        public int getY () {
            return y;
        }


        public void setX (int x) {
            this.x = x;
        }

        public void setY (int y) {
            this.y = y;
        }



    }


