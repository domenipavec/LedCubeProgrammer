import javax.swing.*;
import java.awt.*;

/**
 * Created by domen on 3/13/15.
 * View element for 2D led grid
 */
public class LedGrid extends JPanel {

    public enum LedView {
        VERTICAL0, VERTICAL1, VERTICAL2, VERTICAL3,
        HORIZONTAL0, HORIZONTAL1, HORIZONTAL2, HORIZONTAL3,
        FACE0, FACE1, FACE2, FACE3
    }

    private int number;
    private Led[][] leds;
    private Frame frame;
    private LedView ledView;

    private static final int padding = 100;

    public LedGrid(Frame f) {
        number =  f.getNumber();
        leds = new Led[number][number];

        setLayout(new GridLayout(number,number, padding, padding));

        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                leds[i][j] = new Led(this, i, j);
                add(leds[i][j]);
            }
        }

        setFrame(f);
        setLedView(LedView.VERTICAL0);
    }

    public void reload() {
        for (int i = 0; i < number; i ++) {
            for (int j = 0; j < number; j++) {
                leds[i][j].repaint();
            }
        }
    }

    private int[] calculatePosition(int i, int j) {
        int[] pos =  new int[3];
        switch (ledView) {
            case VERTICAL0:
                pos[0] = j;
                pos[1] = 0;
                pos[2] = i;
                break;
            case VERTICAL1:
                pos[0] = j;
                pos[1] = 1;
                pos[2] = i;
                break;
            case VERTICAL2:
                pos[0] = j;
                pos[1] = 2;
                pos[2] = i;
                break;
            case VERTICAL3:
                pos[0] = j;
                pos[1] = 3;
                pos[2] = i;
                break;
            case HORIZONTAL0:
                pos[0] = i;
                pos[1] = j;
                pos[2] = 0;
                break;
            case HORIZONTAL1:
                pos[0] = i;
                pos[1] = j;
                pos[2] = 1;
                break;
            case HORIZONTAL2:
                pos[0] = i;
                pos[1] = j;
                pos[2] = 2;
                break;
            case HORIZONTAL3:
                pos[0] = i;
                pos[1] = j;
                pos[2] = 3;
                break;
            case FACE0:
                pos[0] = 0;
                pos[1] = j;
                pos[2] = i;
                break;
            case FACE1:
                pos[0] = 1;
                pos[1] = j;
                pos[2] = i;
                break;
            case FACE2:
                pos[0] = 2;
                pos[1] = j;
                pos[2] = i;
                break;
            case FACE3:
                pos[0] = 3;
                pos[1] = j;
                pos[2] = i;
                break;
        }
        return pos;
    }

    public void setFrame(Frame f) {
        frame = f;
        reload();
    }

    public boolean getValue(int i, int j) {
        return frame.getValue(calculatePosition(i,j));
    }

    public void setValue(int i, int j, boolean v) {
        frame.setValue(calculatePosition(i,j), v);
    }

    public void setLedView(LedView lv) {
        ledView = lv;
        reload();
    }
}
