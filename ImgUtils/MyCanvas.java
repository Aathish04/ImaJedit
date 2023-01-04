package ImgUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
public class MyCanvas {
    public static void ImageCanvasFrame(String path) {

        JFrame frame = new JFrame(); //JFrame Creation       
        frame.setTitle("Add Image"); //Add the title to frame
        frame.setLayout(null); //Terminates default flow layout
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Terminate program on close button
        int width = 1;
        int height = 1;

        try{
        BufferedImage bimg = ImageIO.read(new File(path));
        width          = bimg.getWidth();
        height         = bimg.getHeight(); 
        }
        catch(Exception e)
        {
            //never enters here
            System.out.println("NOOOOOOO");
        }
        frame.setBounds(0, 0, width+100, height+50); //Sets the position of the frame
        
        Container c = frame.getContentPane(); //Gets the content layer

        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon(path)); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(0, 0, size.width, size.height); //Sets the location of the image
 
    c.add(label); //Adds objects to the container
        frame.setVisible(true); // Exhibit the frame

    }
}
