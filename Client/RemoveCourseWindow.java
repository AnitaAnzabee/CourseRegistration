import javax.swing.*;
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
 * @author Benjamin Nielsen
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
    
    private JComboBox<String> selectCourses;
    private ArrayList<Registration> registeredCourses;

    public RemoveCourseWindow(int width, int height, ArrayList<Registration> registeredCourses) {
    	super("Remove a registered course");
    	setSize(width, height);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	JPanel removeCourse = new JPanel();
    	removeCourse.setLayout(new BoxLayout(removeCourse, BoxLayout.Y_AXIS));
    	
    	JLabel header = new JLabel("Please select a course to remove registration for");
    	header.setFont(new Font(header.getFont().getName(), Font.BOLD, 20));
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		removeCourse.add(header);
		removeCourse.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.registeredCourses = registeredCourses;
		String [] registeredCoursesListing = new String[registeredCourses.size()];
		for(int i = 0; i < registeredCoursesListing.length; i++) {
			registeredCoursesListing[i] = registeredCourses.get(i).toString();
		}
		
		selectCourses = new JComboBox<String>(registeredCoursesListing);
		removeCourse.add(selectCourses);
		removeCourse.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		removeButton = new JButton("Remove registration for course");
		returnButton = new JButton("Return to Main Window");
		buttonsPanel.add(removeButton);
		buttonsPanel.add(returnButton);
		removeCourse.add(buttonsPanel);
		removeCourse.add(Box.createRigidArea(new Dimension(0, 10)));
		
    	add(removeCourse);
    	setVisible(true);
    }
    
    /*
    public RemoveCourseWindow(int width, int height){
        super("Remove a Registered Course");
        setSize(width, height);
        layout = new BorderLayout(1,1);
        setLayout(layout);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("Enter The Course Name"));
        name = new JTextArea(1, 4);
        northPanel.add(name);
        add(northPanel, BorderLayout.NORTH);

        JPanel centrePanel = new JPanel();
        centrePanel.add(new JLabel("Enter The Course Number"));
        number = new JTextArea(1,3);
        centrePanel.add(number);
        add(centrePanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        removeButton = new JButton("Remove Registration");
        returnButton = new JButton("Return to Main Window");
        
        southPanel.add(removeButton);
        southPanel.add(returnButton);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
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
    
    public String getSelectedItem() {
    	return (String)selectCourses.getSelectedItem();
    }
}