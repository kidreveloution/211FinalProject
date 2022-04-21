import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseClickMain {
    public static void main(String [] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Final Project, Alyeldin and Ibraheem");
        frame.setSize(700,700); // Size of our frame

        final JLabel label = new JLabel();
        frame.add(label);
        final Integer[] x = {1}; // X is the loop identifier

        frame.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent mouseInput) { }
            public void mouseReleased(MouseEvent mouseInput) { }
            public void mouseEntered(MouseEvent mouseInput) { }
            public void mouseExited(MouseEvent mouseInput) { }
            public void mouseClicked(MouseEvent mouseInput) {
                if(mouseInput.getButton() == MouseEvent.BUTTON1 || mouseInput.getButton() == MouseEvent.BUTTON2 ||mouseInput.getButton() == MouseEvent.BUTTON3 ) {
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

        frame.setVisible(true); //initatie program
    }
}
 