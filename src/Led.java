import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.*;

/**
 * Created by domen on 3/13/15.
 * View for one led
 */
public class Led extends JComponent implements MouseListener {

    private Color color = Color.cyan;
    private static final int trace = 2;
    private LedGrid parent;
    private int x,y;

    public Led(LedGrid lg, int i, int j) {
        parent = lg;
        x = i;
        y = j;
        addMouseListener(this);
    }

    public boolean getOn() {
        return parent.getValue(x,y);
    }

    public void setOn(boolean value) {
        parent.setValue(x,y,value);
        repaint();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color value) {
        color = value;
        repaint();
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(3,3);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,50);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        int minDimension = min(getWidth(), getHeight());
        int xBorder = (getWidth() - minDimension)/2;
        int yBorder = (getHeight() - minDimension)/2;
        g.fillOval(xBorder, yBorder, minDimension, minDimension);
        if (getOn()) {
            g.setColor(color);
            g.fillOval(xBorder + trace, yBorder + trace, minDimension-2*trace, minDimension-2*trace);
        } else {
            g.setColor(Color.white);
            g.fillOval(xBorder + trace, yBorder + trace, minDimension-2*trace, minDimension-2*trace);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        setOn(!getOn());
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
