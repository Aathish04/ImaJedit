package ImgUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import ImgUtils.CONSTANTS;

public class MyCanvas {

  public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}  

  public static void ImageCanvasFrame(String path) {
    JFrame frame = new JFrame(); //JFrame Creation
    frame.setTitle("Add Image"); //Add the title to frame
    frame.setLayout(null); //Terminates default flow layout
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Terminate program on close button
    

    // frame.setContentPane();
    int width = 1;
    int height = 1;
    BufferedImage bimg;

    try {
      bimg = ImageIO.read(new File(path));
      width = bimg.getWidth();
      height = bimg.getHeight();

      if(height>CONSTANTS.MAX_IMG_SIZE){

        bimg = resize(bimg,(int)(width/height)*CONSTANTS.MAX_IMG_SIZE, CONSTANTS.MAX_IMG_SIZE);
        width = bimg.getWidth();
        height = bimg.getHeight();
      }
      if(width>CONSTANTS.MAX_IMG_SIZE){
        bimg = resize(bimg,CONSTANTS.MAX_IMG_SIZE,(int)(height/width)*CONSTANTS.MAX_IMG_SIZE);
        width = bimg.getWidth();
        height = bimg.getHeight();
      }

      frame.setBounds(0, 0, width + 100, height + 50); //Sets the position of the frame

      Container c = frame.getContentPane(); //Gets the content layer

      Graphics2D g = (Graphics2D) bimg.getGraphics();
      g.setStroke(new BasicStroke(3));
      g.setColor(Color.BLUE);
      g.drawRect(10, 10, bimg.getWidth() - 20, bimg.getHeight() - 20);

      JLabel label = new JLabel(new ImageIcon(bimg)); //JLabel Creation
      // label.setIcon(new ImageIcon(bimg)); //Sets the image to be displayed as an icon
      Dimension size = label.getPreferredSize(); //Gets the size of the image
      label.setBounds(0, 0, size.width, size.height); //Sets the location of the image

      c.add(label); //Adds objects to the container
      frame.setVisible(true); // Exhibit the frame

    //BufferedImage bi = getMyImage();
      File outputfile = new File("saved.png");
      ImageIO.write(bimg, "png", outputfile);
    } catch (Exception e) {
      //never enters here
      System.out.println(e);
    }
  }
}
