import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseClickMain {
    public static void main(String [] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Final Project, Alyeldin and Ibraheem");
        frame.setSize(700,700);

        final JLabel label = new JLabel();
        frame.add(label);
        final Integer[] x = {1};

        frame.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) {
                if(me.getButton() == MouseEvent.BUTTON1 || me.getButton() == MouseEvent.BUTTON2 ||me.getButton() == MouseEvent.BUTTON3 ) {
                    if (x[0] == 1) {
                    label.setText("Welcome to java");
                    x[0] = 0;
                    }
                    else{
                        label.setText("LearningJavaFX");
                        x[0] = 1;


                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
 