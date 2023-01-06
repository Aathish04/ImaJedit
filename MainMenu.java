// File Handling
import java.io.File;

// Abstract Layout Management
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

// Actual UI Elements
import javax.swing.JFrame;

import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

// Events Handling
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Number input filtering
import javax.swing.text.NumberFormatter;

import ImgUtils.MyCanvas;
import ImgUtils.CONSTANTS;
public class MainMenu {
  public static void main(String[] args) {
    // Create a new JFrame
    JFrame frame = new JFrame("ImaJedit");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Set the layout of the frame to be a GridBagLayout
    frame.setLayout(new GridBagLayout());

    // Create a new GridBagConstraints object
    GridBagConstraints constraints = new GridBagConstraints();

    // Set the default values for the constraints
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.ipady = 20;
    constraints.insets = new Insets(0, 0, 0, 0); 

    // Create the "create" button and add it to the frame
    JButton createButton = new JButton("Create");
    frame.add(createButton, constraints);

    // Set the constraints for the "width" label
    constraints.weightx = 0.25;
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.EAST;
    // Create the "width" label and add it to the frame
    JLabel widthLabel = new JLabel("Width:");
    frame.add(widthLabel, constraints);

    // Set the constraints for the "textbox for width"
    constraints.weightx = 0.5;
    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.WEST;

    // Create the "widthspinner" and add it to the frame
    SpinnerNumberModel widthSpinnerModel = new SpinnerNumberModel(640, 1, CONSTANTS.MAX_IMG_SIZE, 1);
    JSpinner widthSpinner = new JSpinner(widthSpinnerModel);
    ((NumberFormatter)(
        (JSpinner.NumberEditor)widthSpinner.getEditor()
      ).getTextField().getFormatter()
    ).setAllowsInvalid(false);
    frame.add(widthSpinner, constraints);

    // Set the constraints for the "height" label
    constraints.weightx = 0.25;
    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.EAST;

    // Create the "height" label and add it to the frame
    JLabel heightLabel = new JLabel("Height: ");
    frame.add(heightLabel, constraints);

    // Set the constraints for the "textbox for height"
    constraints.weightx = 0.5;
    constraints.gridx = 4;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.WEST;

    // Create the "heightspinner" and add it to the frame
    SpinnerNumberModel heightSpinnerModel = new SpinnerNumberModel(480, 1, CONSTANTS.MAX_IMG_SIZE, 1);
    JSpinner heightSpinner = new JSpinner(heightSpinnerModel);
    ((NumberFormatter)(
        (JSpinner.NumberEditor)heightSpinner.getEditor()
      ).getTextField().getFormatter()
    ).setAllowsInvalid(false);
    frame.add(heightSpinner, constraints);

    createButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          int imgwidth = (int) widthSpinner.getValue();
      
          int imgheight = (int) heightSpinner.getValue();
          System.out.println("The Image height is: "+imgheight);
          System.out.println("The Image width is: "+imgwidth);
          // TODO: Use the width and height values here to make a new canvas.
        }
      });

    // Set the constraints for the "open" button
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 5;
    constraints.anchor = GridBagConstraints.CENTER;

    // Create the "open" button and add it to the frame
    JButton openButton = new JButton("Open");
    openButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            // TODO: OPEN IMAGE HERE
            MyCanvas.ImageCanvasFrame(selectedFile.getAbsolutePath());
        }
      }
    });
    frame.add(openButton, constraints);

    // Set the constraints for the "exit" button
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 5;

    // Create the "exit" button and add it to the frame
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new CloseListener());
    frame.add(exitButton, constraints);

    // Set the size and location
    frame.setSize(600, 400);
    frame.setVisible(true);
  }
}

class CloseListener implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e) {
      //DO SOMETHING
      System.exit(0);
  }
}