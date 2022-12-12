import java.awt.*;
import javax.swing.*;
// import javax.swing.border.EmptyBorder;
public class SwingDemo {
    public static void main(String[] args) {
       JPanel panel = new JPanel(new BorderLayout());
    //    panel.setBorder(new border.EmptyBorder(2, 3, 2, 3));
       JPanel layout = new JPanel(new GridBagLayout());
    //    layout.setBorder(new border.EmptyBorder(5, 5, 5, 5));
       JPanel btnPanel = new JPanel(new GridLayout(3, 1, 3, 5));
       btnPanel.add(new JButton("Open Image"));
       btnPanel.add(new JButton("New Image"));
       layout.add(btnPanel);
       panel.add(layout, BorderLayout.CENTER);
       JFrame frame = new JFrame("Demo");
       frame.add(panel);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.setLocationByPlatform(true);
       frame.setSize(500, 400);
       frame.setVisible(true);
    }
 }