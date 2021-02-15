package client;

import javax.swing.*;

import model.Registration;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that acts as a view for RemoveCourseWindowController (controller) and StudentMaintainerModel
 * (model) in the MVC design pattern. This class is the frame of the remove course window
 * in this student maintainer application.
 * @author Ben, Nolan, Anita
 * @version 1.0
 * @since April 10, 2020
 */
public class RemoveCourseWindow extends JFrame{
	/**
	 * The serial version UID for this frame
	 */
    static final long serialVersionUID = 11;
    
    /**
     * The BorderLayout of this frame
     */
    private BorderLayout layout;
    
    /**
     * JTextAreas of this frame used for getting user inputs to remove a course from
     * the Student's registration
     */
    private JTextArea name, number;
    
    /**
     * The remove button of this frame, when pressed, attempts to remove a course from
     * the Student's registration
     */
    private JButton removeButton;
    
    /**
     * The return button of this frame, when pressed, sets this frame to not be visible
     */
    private JButton returnButton;
    /**
     * selection menu for registered courses
     */
    private JComboBox<String> selectCourses;
    /**
     * list of all registered courses
     */
    private ArrayList<Registration> registeredCourses;

    /**
     * Constructs an Remove Course Window and sets it to be visible
     * @param width The width of this frame
     * @param height The height of this frame
     * @param registeredCourses the courses the active student is registered in
     */
    public RemoveCourseWindow(int width, int height, ArrayList<Registration> registeredCourses) {
        // contructs a JFrame with "remove a registered course" as the title
    	super("Remove a registered course");
        //sets the size of the JFrame
    	setSize(width, height);
        //makes the JFrame be disposed when it is closed
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	//contructs a new panel, sets it to use border layout
    	JPanel removeCourse = new JPanel();
    	removeCourse.setLayout(new BoxLayout(removeCourse, BoxLayout.Y_AXIS));
    	//sets a text object to prompt selection of course to remove
    	JLabel header = new JLabel("Please select a course to remove registration for");
    	header.setFont(new Font(header.getFont().getName(), Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        //adds header to the panel
		removeCourse.add(header);
		removeCourse.add(Box.createRigidArea(new Dimension(0, 20)));
		//sets the registered courses to the proper argument
        this.registeredCourses = registeredCourses;
        //splite the contents of registered courses into an array of strings
		String [] registeredCoursesListing = new String[registeredCourses.size()];
		for(int i = 0; i < registeredCoursesListing.length; i++) {
			registeredCoursesListing[i] = registeredCourses.get(i).toString();
		}
		//creates a selection menu for courses
		selectCourses = new JComboBox<String>(registeredCoursesListing);
		removeCourse.add(selectCourses);
		removeCourse.add(Box.createRigidArea(new Dimension(0, 10)));
		//creates a new panel to hold buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //adds a new button to remove registration
        removeButton = new JButton("Remove registration for course");
        //adds a new button to close window
        returnButton = new JButton("Return to Main Window");
        //adds buttons to button panel and button panel to the main panel
        buttonsPanel.add(removeButton);
		buttonsPanel.add(returnButton);
		removeCourse.add(buttonsPanel);
		removeCourse.add(Box.createRigidArea(new Dimension(0, 10)));
		//adds the main panel to the JFrame
        add(removeCourse);
        //activates the window
    	setVisible(true);
    }
    /**
     * fetches the list of all registered courses
     * @return  the list of all courses the student is registered in
     */
    public ArrayList<Registration> getRegistrationList() {
    	return registeredCourses;
    }
    
    /**
     * Adds an ActionListener for the remove button of RemoveCourseWindow.
     * @param insertListener The ActionListener to add to the remove button
     */
    public void addRemoveListener(ActionListener removeListener) {
    	removeButton.addActionListener(removeListener);
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
     * Fetches the information of the selected course
     * @return  the information o fthe selected course
     */
    public String getSelectedItem() {
    	return (String)selectCourses.getSelectedItem();
    }
}
