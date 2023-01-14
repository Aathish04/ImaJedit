package ImgUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import java.io.IOException;

public class MyCanvas {

  static JFrame setDrawingMenuBar(JFrame frame, Palette palette){
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);
    JMenu pencilMenu = new JMenu("Pencil");
    menuBar.add(pencilMenu);
    JMenuItem increasePencilSizeItem = new JMenuItem("Increase Size");
    increasePencilSizeItem.addActionListener(e -> {
      palette.strokeWidth = palette.strokeWidth+1;
    });
    JMenuItem decreasePencilSizeItem = new JMenuItem("Decrease Size");
    decreasePencilSizeItem.addActionListener(e -> {
      if(palette.strokeWidth>1){
        palette.strokeWidth = palette.strokeWidth-1;
      }
    });
    pencilMenu.add(increasePencilSizeItem);
    pencilMenu.add(decreasePencilSizeItem);
    JMenu PixelMenu = new JMenu("Resolution");
    menuBar.add(PixelMenu);
    JMenuItem Blur = new JMenuItem("Increase Blur");
    Blur.addActionListener(e->{
      BufferedImage bufferedImage = palette.bimg;
      System.out.println(palette.bimg.getWidth()+palette.bimg.getHeight());
      BufferedImage bufferedImage2=new BufferedImage(palette.bimg.getWidth(),palette.bimg.getHeight(),palette.bimg.getType());
      palette.graphicsForDrawingOnScreen=palette.getGraphics();
      bufferedImage2.getGraphics().drawImage(bufferedImage,0,0,null);

      Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f,
          1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
      BufferedImageOp op = new ConvolveOp(kernel);

      bufferedImage = op.filter(bufferedImage2, palette.bimg);
      palette.graphicsForDrawingOnScreen.drawImage(palette.bimg,0,0,null);
      palette.graphicsForDrawingOnScreen.dispose();

    });

    JMenuItem FillCanvas = new JMenuItem("Fill Canvas");
    FillCanvas.addActionListener(
      e->{
        palette.setUpDrawingGraphics();
        palette.bimgGraphics.fillRect(0, 0, palette.bimg.getWidth(), palette.bimg.getHeight());
        palette.graphicsForDrawingOnScreen.drawImage(palette.bimg,0,0,null);
        palette.bimgGraphics.dispose();
        palette.graphicsForDrawingOnScreen.dispose();
      }
    );
    PixelMenu.add(Blur);
    PixelMenu.add(FillCanvas);
    return frame;
  }

  public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}  

  public static void ImageCanvasFrame(int width,int height) {
    JFrame frame = new JFrame(); //JFrame Creation
    frame.setTitle("Add Image"); //Add the title to frame
    frame.setLayout(null); //Terminates default flow layout
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Terminate program on close button
    // frame.setContentPane();
    BufferedImage bimg;

    try {
      bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      frame.setBounds(0, 0, width + 100, height + 50); //Sets the position of the frame

      Graphics2D    graphics = bimg.createGraphics();
      graphics.setPaint (Color.WHITE);
      graphics.fillRect ( 0, 0, bimg.getWidth(), bimg.getHeight() );

      JLabel label = new JLabel(new ImageIcon(bimg)); //JLabel Creation
      Dimension size = label.getPreferredSize(); //Gets the size of the image
      label.setBounds(0, 0, size.width, size.height); //Sets the location of the image

      // frame.add(label); //Adds objects to the frame
      Palette content = new Palette(bimg);
      frame = setDrawingMenuBar(frame,content);
      
      frame.setContentPane(content);
      frame.setVisible(true); // Exhibit the frame
    } catch (Exception e) {
      //never enters here
      System.out.println(e);
    }
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

      //Container c = frame.getContentPane(); //Gets the content layer

      // Graphics2D g = (Graphics2D) bimg.getGraphics();
      // g.setStroke(new BasicStroke(3));
      // g.setColor(Color.BLUE);
      // g.drawRect(10, 10, bimg.getWidth() - 20, bimg.getHeight() - 20);

      JLabel label = new JLabel(new ImageIcon(bimg)); //JLabel Creation
      // label.setIcon(new ImageIcon(bimg)); //Sets the image to be displayed as an icon
      Dimension size = label.getPreferredSize(); //Gets the size of the image
      label.setBounds(0, 0, size.width, size.height); //Sets the location of the image

      // frame.add(label); //Adds objects to the frame
      Palette content = new Palette(bimg);
      frame = setDrawingMenuBar(frame,content);
      frame.setContentPane(content);
      frame.setVisible(true); // Exhibit the frame

    //BufferedImage bi = getMyImage();
    } catch (Exception e) {
      //never enters here
      System.out.println(e);
    }
  }
}
