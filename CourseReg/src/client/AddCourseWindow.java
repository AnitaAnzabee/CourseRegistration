package client;

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
        // contructs a JFrame with "add a new course" as the title
        super("Add A New Course");
        //sets the size of the JFrame
        setSize(width, height);
        //sets the frame to use border layout
        layout = new BorderLayout(1,1);
        setLayout(layout);

        //contructs a new panel for the course name input field and prompt
        JPanel northPanel = new JPanel();
        //adds the prompt for course name to north panel
        northPanel.add(new JLabel("Enter The Course Name"));
        //adds the input field for course name
        name = new JTextArea(1, 4);
        northPanel.add(name);
        //adds the north panel to the JFrame
        add(northPanel, BorderLayout.NORTH);

        //contructs a new panel to hold content in the centre of the frame, sets it to use border layout
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));

        //constructs a new panel to hold the prompt and input field for course number
        JPanel subCentreOne = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //adds the prompt fot course number
        subCentreOne.add(new JLabel("Enter The Course Number"));
        //adds the input field for course number
        number = new JTextArea(1, 3);
        subCentreOne.add(number);

        //contructs a new panel to hold the prompt and input field for number of sections to create
        JPanel subCentreTwo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //adds the prompt for number of sections
        subCentreTwo.add(new JLabel("Enter The Number Of Sections to Create"));
        //adds the inout field for the number of sections
        sectionNumber = new JTextArea(1, 3);
        subCentreTwo.add(sectionNumber);

        //contructs a new panel to hold the prompt and input field for the capacity of each section
        JPanel subCentreThree = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //adds the prompt for capacity
        subCentreThree.add(new JLabel("Enter The Capacity Of Each Section"));
        //adds the input field for capacity
        sectionCapacity = new JTextArea(1, 4);
        subCentreThree.add(sectionCapacity);
        //adds all input panels to the centre panel
        centrePanel.add(subCentreOne);
        centrePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centrePanel.add(subCentreTwo);
        centrePanel.add(subCentreThree);
        //adds the centre panel to the frame
        add(centrePanel, BorderLayout.CENTER);

        //adds a new panel for add course and return buttons
        JPanel southPanel = new JPanel();
        //create buttons for add course and return
        addButton = new JButton("Add Course");
        returnButton = new JButton("Return to Main Window");
        //add buttons to southern panel
        southPanel.add(addButton);
        southPanel.add(returnButton);
        //add the southern panel to the frame
        add(southPanel, BorderLayout.SOUTH);

        //activates the window
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