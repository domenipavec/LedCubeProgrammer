import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.NumberFormat;

/**
 * Created by domen on 3/13/15.
 */
public class LedCubeProgrammer {

    private JPanel LedCubeProgrammer;
    private JList framesList;
    private JButton plusButton;
    private JButton deleteButton;
    private JButton downButton;
    private JButton upButton;
    private JButton duplicateButton;
    private JSpinner trajanjeSpinner;
    private JPanel ledPanel;
    private JToggleButton horizontal0;
    private JToggleButton horizontal1;
    private JToggleButton horizontal2;
    private JToggleButton horizontal3;
    private JToggleButton vertical0;
    private JToggleButton vertical1;
    private JToggleButton vertical2;
    private JToggleButton vertical3;
    private JToggleButton face0;
    private JToggleButton face1;
    private JToggleButton face2;
    private JToggleButton face3;
    private ButtonGroup viewSelect;
    private Animation animation;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LedCubeProgrammer");
        frame.setContentPane(new LedCubeProgrammer().LedCubeProgrammer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        /* make new animation */
        animation = new Animation(4);

        /* init led view */
        ledPanel = new LedGrid(animation.getFrame(0));

        /* init additional frame settings controls */
        trajanjeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        /* init frame list */
        framesList = new JList(animation);
        framesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        framesList.setSelectedIndex(0);
        framesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Rectangle r = framesList.getCellBounds(0, framesList.getLastVisibleIndex());
                    if (r != null && r.contains(e.getPoint())) {
                        int index = framesList.locationToIndex(e.getPoint());
                        Frame f = animation.getFrame(index);
                        String name = JOptionPane.showInputDialog("Rename", animation.getElementAt(index));
                        if (name != null && name.length() > 0) {
                            animation.getFrame(index).setName(name);
                        }
                    }
                }
            }
        });
        framesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {
                    if (framesList.getSelectedIndex() >= 0) {
                        ((LedGrid)ledPanel).setFrame(animation.getFrame(framesList.getSelectedIndex()));
                    }
                }
            }
        });

        /* init frame manipulation buttons */
        downButton = new JButton("\u2193");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = framesList.getSelectedIndex();
                if (index >= 0 && index < animation.getSize()-1) {
                    animation.moveDown(index);
                    framesList.setSelectedIndex(index + 1);
                }
            }
        });
        upButton = new JButton("\u2191");
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = framesList.getSelectedIndex();
                if (index > 0) {
                    animation.moveUp(index);
                    framesList.setSelectedIndex(index - 1);
                }
            }
        });
        plusButton = new JButton();
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                animation.addFrame();
                framesList.setSelectedIndex(animation.getSize()-1);
            }
        });
        deleteButton = new JButton();
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = framesList.getSelectedIndex();
                animation.deleteFrame(index);
                if (index < animation.getSize()) {
                    framesList.setSelectedIndex(index);
                } else {
                    framesList.setSelectedIndex(animation.getSize()-1);
                }
            }
        });
        duplicateButton = new JButton();
        duplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                animation.duplicateFrame(framesList.getSelectedIndex());
                framesList.setSelectedIndex(animation.getSize()-1);
            }
        });

        /* init view change buttons */
        horizontal0 = new JToggleButton();
        horizontal1 = new JToggleButton();
        horizontal2 = new JToggleButton();
        horizontal3 = new JToggleButton();
        vertical0 = new JToggleButton();
        vertical1 = new JToggleButton();
        vertical2 = new JToggleButton();
        vertical3 = new JToggleButton();
        face0 = new JToggleButton();
        face1 = new JToggleButton();
        face2 = new JToggleButton();
        face3 = new JToggleButton();

        /* view change action listener */
        ActionListener vcal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((LedGrid)ledPanel).setLedView(LedGrid.LedView.valueOf(actionEvent.getActionCommand()));
            }
        };
        horizontal0.addActionListener(vcal);
        horizontal1.addActionListener(vcal);
        horizontal2.addActionListener(vcal);
        horizontal3.addActionListener(vcal);
        vertical0.addActionListener(vcal);
        vertical1.addActionListener(vcal);
        vertical2.addActionListener(vcal);
        vertical3.addActionListener(vcal);
        face0.addActionListener(vcal);
        face1.addActionListener(vcal);
        face2.addActionListener(vcal);
        face3.addActionListener(vcal);

        horizontal0.setActionCommand(LedGrid.LedView.HORIZONTAL0.name());
        horizontal1.setActionCommand(LedGrid.LedView.HORIZONTAL1.name());
        horizontal2.setActionCommand(LedGrid.LedView.HORIZONTAL2.name());
        horizontal3.setActionCommand(LedGrid.LedView.HORIZONTAL3.name());
        vertical0.setActionCommand(LedGrid.LedView.VERTICAL0.name());
        vertical1.setActionCommand(LedGrid.LedView.VERTICAL1.name());
        vertical2.setActionCommand(LedGrid.LedView.VERTICAL2.name());
        vertical3.setActionCommand(LedGrid.LedView.VERTICAL3.name());
        face0.setActionCommand(LedGrid.LedView.FACE0.name());
        face1.setActionCommand(LedGrid.LedView.FACE1.name());
        face2.setActionCommand(LedGrid.LedView.FACE2.name());
        face3.setActionCommand(LedGrid.LedView.FACE3.name());

        viewSelect = new ButtonGroup();
        viewSelect.add(horizontal0);
        viewSelect.add(horizontal1);
        viewSelect.add(horizontal2);
        viewSelect.add(horizontal3);
        viewSelect.add(vertical0);
        viewSelect.add(vertical1);
        viewSelect.add(vertical2);
        viewSelect.add(vertical3);
        viewSelect.add(face0);
        viewSelect.add(face1);
        viewSelect.add(face2);
        viewSelect.add(face3);

        horizontal0.doClick();
    }
}
