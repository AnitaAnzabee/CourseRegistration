package client;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

/**
 * A class that acts as a view for RegisterWindowController (controller) and StudentMaintainerModel
 * (model) in the MVC design pattern. This class is the frame of the register window
 * in this student maintainer application.
 * @author Benjamin Nielsen
 * @author Nolan Chan
 * @author Anita
 * @version 1.1
 * @since April 10, 2020
 */
public class RegisterWindow extends JFrame{
	/**
	 * The serial version UID for this frame
	 */
    static final long serialVersionUID = 10;
    
    /**
     * The BorderLayout of this frame
     */
    private BorderLayout layout;
    
    /**
     * JTextAreas of this frame used for getting user inputs to insert a course into
     * the Student's registration
     */
    private JTextArea name, number;
    
    /**
     * The insert button of this frame, when pressed, attempts to insert a course into
     * the Student's registration
     */
    private JButton getOfferingsButton;
    
    /**
     * The return button of this frame, when pressed, sets this frame to not be visible
     */
    private JButton returnButton;
    /**
     * the register button of this frame, registers student for selected course
     */
    private JButton registerButton;
    /**
     * panel that holds buttons
     */
    private JPanel buttonPanel;
    /**
     * panel that holds a selection menu for available course offerings
     */
    private JPanel selectSectionPanel;
    /**
     * selection menu for course offerings
     */
    private JComboBox<String> selectSection;
    /**
     * stored state of the window
     */
    private int windowState;

    /**
     * Constructs an RegisterWindow and sets it to be visible
     * @param width The width of this frame
     * @param height The height of this frame
     */
    public RegisterWindow(int width, int height){
        // contructs a JFrame with "register for a course" as the title
        super("Register for a course");
        //sets the size of the JFrame
        setSize(width, height);
        //sets the frame to use border layout
        layout = new BorderLayout(1,1);
        setLayout(layout);
        //makes the JFrame be disposed when it is closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        windowState = 0;

        //contructs a new panel for the course name input field and prompt
        JPanel northPanel = new JPanel();
        //adds the prompt for course name to north panel
        northPanel.add(new JLabel("Enter The Course Name"));
        //adds the input field for course name
        name = new JTextArea(1, 4);
        northPanel.add(name);
        //adds the north panel to the JFrame
        add(northPanel, BorderLayout.NORTH);

        //contructs a new panel for the entry of course information to add
        JPanel centrePanel = new JPanel();
        //adds the prompt for the course number to the centre panel
        centrePanel.add(new JLabel("Enter The Course Number"));
        //adds the inout field for course number
        number = new JTextArea(1,3);
        centrePanel.add(number);
        //adds the centre panel to the JFrame
        add(centrePanel, BorderLayout.CENTER);

        //contructs a new panel for return and get offerings buttons
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //creates get offerings button
        getOfferingsButton = new JButton("Get Offerings");
        //creates return button
        returnButton = new JButton("Return to Main Window");
        //adds both buttons to the button panel
        buttonPanel.add(getOfferingsButton);
        buttonPanel.add(returnButton);
        
        //contructs a new panel for a selection menu of available courses
        JPanel southPanel = new JPanel();
        //sets the layout of the south panel to a border layout
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        //contructs a new panel for the selection menu
        JPanel selectSectionPanel = new JPanel();
        //contructs a menu to select from a list of available offerings
        selectSection = new JComboBox<String>();
        //adds the selection menu to the selection panel
        selectSectionPanel.add(selectSection);
        //adds the button panel and selection panel to the southern panel
        southPanel.add(selectSectionPanel);
        southPanel.add(buttonPanel);
        //adds the southern panel to the JFrame
        add(southPanel, BorderLayout.SOUTH);

        //activates the window
        setVisible(true);
    }
    /**
     * fetches the state of the window
     * @return the state of the window
     */
    public int getWindowState() {
    	return windowState;
    }
    /**
     * sets the state of the window
     * @param windowState
     */
    public void setWindowState(int windowState) {
    	this.windowState = windowState;
    }
    
    /**
     * Adds an ActionListener for the register button of RegisterWindow.
     * @param insertListener The ActionListener to add to the register button
     */
    public void addRegisterListener(ActionListener registerListener) {
    	registerButton.addActionListener(registerListener);
    }
    
    /**
     * Adds an ActionListener for the return button of RegisterWindow.
     * @param returnListener The ActionListener to add to the return button
     */
    public void addReturnListener(ActionListener returnListener) {
    	returnButton.addActionListener(returnListener);
    }
    
    /**
     * Adds an action listener to the get offerings button
     * @param getOfferingsListener  the listener to add to the get offerings button
     */
    public void addGetCourseOfferingsListener(ActionListener getOfferingsListener) {
    	getOfferingsButton.addActionListener(getOfferingsListener);
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
     * Fetches the information of the selected course
     * @return  the information o fthe selected course
     */
    public String getSelectedItem() {
    	return (String)selectSection.getSelectedItem();
    }
    
    /**
     * Sets the content of the selection pane to passed items and adds a register button
     * @param courseOfferings   the list of available course offerings from which to select
     */
    public void addSelectionPanel(String [] courseOfferings) {
    	selectSection.removeAllItems();
    	for(String s : courseOfferings) {
    		selectSection.addItem(s);
    	}
    	registerButton = new JButton("Register");
    	buttonPanel.add(registerButton);
    	setVisible(true);
    }
    
    /**
     * Sets the content of the selection pane to passed items
     * @param courseOfferingsthe list of available course offerings from which to select
     */
    public void updateSelectionPanel(String [] courseOfferings) {
    	selectSection.removeAllItems();
    	for(String s : courseOfferings) {
    		selectSection.addItem(s);
    	}
    	setVisible(true);
    }
}