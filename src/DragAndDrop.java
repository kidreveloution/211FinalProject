import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DragAndDrop extends JLayeredPane {
    public static final int WIDTH = 780; //Inititating width of the program
    public static final int HEIGHT = 580; //Intiiting Length of the program
    private static final int GRID_ROWS = 5; // Number of Rows
    private static final int GRID_COLS = 5; // Number of columns
    private static final int GAP = 5; //Gap of each box
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT); //Using values to create a panel
    private static final Dimension LABEL_SIZE = new Dimension(140, 90); //The size of the text label
    private final GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP); //Initilizing the entirity of the box
    private final JPanel backingPanel = new JPanel(gridlayout);
    private final JPanel[][] panelGrid = new JPanel[GRID_ROWS][GRID_COLS];
    //private JLabel blueLabel = new JLabel("Blue", SwingConstants.CENTER);

    public DragAndDrop() {
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(2 * GAP, 2 * GAP); //initial location of the label
        backingPanel.setBackground(Color.black);
        for (int row = 0; row < GRID_ROWS; row++) { //looping to start program
            for (int col = 0; col < GRID_COLS; col++) {
                panelGrid[row][col] = new JPanel(new GridBagLayout());
                backingPanel.add(panelGrid[row][col]);
            }
        }



        JLabel redLabel = new JLabel("Cowan 211 Object", SwingConstants.CENTER); //Title of the program
        redLabel.setText("LearningJavaFx"); //Label name
        redLabel.setOpaque(true); // making background of label visible
        redLabel.setBackground(Color.red.brighter().brighter());
        redLabel.setPreferredSize(LABEL_SIZE);
        panelGrid[4][3].add(redLabel);


        //backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    }

    private class MyMouseAdapter extends MouseAdapter { //accepting mouse preferences
        private JLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
                return;
            }
            // if we click on jpanel that holds a jlabel
            if (components[0] instanceof JLabel) {

                // remove label from panel
                dragLabel = (JLabel) components[0];
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();

                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                int x = me.getPoint().x - dragLabelWidthDiv2;
                int y = me.getPoint().y - dragLabelHeightDiv2;
                dragLabel.setLocation(x, y);
                add(dragLabel, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) { //collect mouse drag
            if (dragLabel == null) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) { //collecting if mouse is released
            if (dragLabel == null) {
                return;
            }
            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            if (droppedPanel == null) {
                // if off the grid, return label to home
                clickedPanel.add(dragLabel);
                clickedPanel.revalidate();
            } else {
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == droppedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    // if off the grid, return label to home
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } else {
                    droppedPanel.add(dragLabel);
                    droppedPanel.revalidate();
                }
            }

            repaint();
            dragLabel = null;
        }
    }

    private static void createAndShowUI() { //start the program
        JFrame frame = new JFrame("Cowan 211 Final");
        frame.getContentPane().add(new DragAndDrop());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) { //main driver association
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}
