import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

/**
 * A class that acts as a view for AddCourseWindowController (controller) and StudentMaintainerModel
 * (model) in the MVC design pattern. This class is the frame of the add course course window
 * in this student maintainer application.
 * @author Benjamin Nielsen
 * @author Nolan Chan
 * @version 1.0
 * @since April 17, 2020
 */
public class AddCourseWindow extends JFrame{
	/**
	 * The serial version UID for this frame
	 */
    static final long serialVersionUID = 20;
    
    /**
     * The BorderLayout of this frame
     */
    private BorderLayout layout;
    
    /**
     * JTextAreas of this frame used for getting user inputs to add course for a course
     */
    private JTextArea name, number, sectionNumber, sectionCapacity;
    
    /**
     * The remove button of this frame, when pressed, attempts to find a course
     */
    private JButton addButton;
    
    /**
     * The return button of this frame, when pressed, sets this frame to not be visible
     */
    private JButton returnButton;

    /**
     * Constructs an SearchWindow and sets it to be visible
     * @param width The width of this frame
     * @param height The height of this frame
     */
    public AddCourseWindow(int width, int height){
        super("Add A New Course");
        setSize(width, height);
        layout = new BorderLayout(1,1);
        setLayout(layout);

        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("Enter The Course Name"));
        name = new JTextArea(1, 4);
        northPanel.add(name);
        add(northPanel, BorderLayout.NORTH);

        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
        JPanel subCentreOne = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subCentreOne.add(new JLabel("Enter The Course Number"));
        number = new JTextArea(1, 3);
        subCentreOne.add(number);
        JPanel subCentreTwo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subCentreTwo.add(new JLabel("Enter The Number Of Sections to Create"));
        sectionNumber = new JTextArea(1, 3);
        subCentreTwo.add(sectionNumber);
        JPanel subCentreThree = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subCentreThree.add(new JLabel("Enter The Capacity Of Each Section"));
        sectionCapacity = new JTextArea(1, 4);
        subCentreThree.add(sectionCapacity);
        centrePanel.add(subCentreOne);
        centrePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centrePanel.add(subCentreTwo);
        centrePanel.add(subCentreThree);
        add(centrePanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        addButton = new JButton("Add Course");
        returnButton = new JButton("Return to Main Window");
        
        southPanel.add(addButton);
        southPanel.add(returnButton);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    /**
     * Adds an ActionListener for the add course button of SearchWindow.
     * @param insertListener The ActionListener to add to the add course button
     */
    public void addAddCourseListener(ActionListener addListener) {
    	addButton.addActionListener(addListener);
    }
    
    /**
     * Adds an ActionListener for the return button of RegisterWindow.
     * @param returnListener The ActionListener to add to the return button
     */
    public void addReturnListener(ActionListener returnListener) {
    	returnButton.addActionListener(returnListener);
    }

    /**
     * Displays an error message window with the supplied String.
     * @param text The text to display on the error message
     */
    public void displayErrorMessage(String text) {
    	JOptionPane.showMessageDialog(this, text, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Displays a message window with the supplied String.
     * @param text The text to display on the message window
     */
    public void displayMessage(String text) {
    	JOptionPane.showMessageDialog(this, text);
    }
    
    /**
     * Returns the String entered in the name JTextArea.
     * @return The String entered in the name JTextArea
     */
    public String getName() {
    	return name.getText();
    }
    
    /**
     * Returns the String entered in the number JTextArea.
     * @return The String entered in the number JTextArea
     */
    public String getNumber() {
    	return number.getText();
    }
    
    /**
     * Returns the String entered in the sectionNumber JTextArea.
     * @return The String entered in the sectionNumber JTextArea
     */
    public String getSectionNumber() {
    	return sectionNumber.getText();
    }
    
    /**
     * Returns the String entered in the sectionCapacity JTextArea.
     * @return The String entered in the sectionCapacity JTextArea
     */
    public String getSectionCapacity() {
    	return sectionCapacity.getText();
    }
}