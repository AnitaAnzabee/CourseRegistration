package client;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

/**
 * A class that acts as a view for SearchWindowController (controller) and StudentMaintainerModel
 * (model) in the MVC design pattern. This class is the frame of the search course window
 * in this student maintainer application.
 * @author Ben, Nolan, Anita
 * @version 1.0
 * @since April 10, 2020
 */
public class SearchWindow extends JFrame{
	/**
	 * The serial version UID for this frame
	 */
    static final long serialVersionUID = 12;
    
    /**
     * The BorderLayout of this frame
     */
    private BorderLayout layout;
    
    /**
     * JTextAreas of this frame used for getting user inputs to search for a course
     */
    private JTextArea name, number;
    
    /**
     * The remove button of this frame, when pressed, attempts to find a course
     */
    private JButton searchButton;
    
    /**
     * The return button of this frame, when pressed, sets this frame to not be visible
     */
    private JButton returnButton;

    /**
     * Constructs an SearchWindow and sets it to be visible
     * @param width The width of this frame
     * @param height The height of this frame
     */
    public SearchWindow(int width, int height){
        // contructs a JFrame with "search for a course" as the title
        super("Search for a Course");
        //sets the size of the JFrame
        setSize(width, height);
        //sets the frame to use border layout
        layout = new BorderLayout(1,1);
        setLayout(layout);
        //makes the JFrame be disposed when it is closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        //adds the inout feild for course number
        number = new JTextArea(1,3);
        centrePanel.add(number);
        //adds the centre panel to the JFrame
        add(centrePanel, BorderLayout.CENTER);

        //contructs a panel for search and return buttons
        JPanel southPanel = new JPanel();
        //creates searhc button
        searchButton = new JButton("Search");
        //creates return button
        returnButton = new JButton("Return to Main Window");
        //adds both buttons to the southern panel
        southPanel.add(searchButton);
        southPanel.add(returnButton);
        //adds the southern panel to the JFrame
        add(southPanel, BorderLayout.SOUTH);

        //activates the window
        setVisible(true);
    }
    
    /**
     * Adds an ActionListener for the search button of SearchWindow.
     * @param insertListener The ActionListener to add to the search button
     */
    public void addSearchListener(ActionListener removeListener) {
    	searchButton.addActionListener(removeListener);
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
}