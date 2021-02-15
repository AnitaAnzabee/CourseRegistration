package client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A frame that behaves as a view for the main client window. Makes use of the MVC design pattern where the model
 * is ClientModel (stores user info), and the controllers are CommunicationController (communication with server) and
 * GUIController (main client controller).
 * @author Nolan Chan
 * @author Benjamin Nielsen
 * @author Anita
 * @version 1.0
 * @since April 18, 2020
 */
public class ClientGUI extends JFrame{
	
	/**
	 * A JPanel that contains all of the buttons on the frame for user interation except for the
	 * logout button.
	 */
	private JPanel buttonsPanel;
	
	/**
	 * A non-editable JTextArea that displays user requests from the server (e.x. displaying all courses)
	 */
	private JTextArea textArea;
	
	/**
	 * A JButton that when pressed, opens up a window for the user to search for a course
	 */
	private JButton searchButton;
	
	/**
	 * A JButton that when pressed, displays all courses to textArea
	 */
	private JButton viewAllButton;
	
	/**
	 * A JButton that when pressed, displays all user registered courses to textArea
	 */
	private JButton viewAllRegisteredButton;
	
	/**
	 * A JButton that when pressed, opens up a window for the user to register for a course
	 */
	private JButton registerButton;
	
	/**
	 * A JButton that when pressed, opens up a window for the user to remove registration for a course
	 */
	private JButton deRegisterButton;
	
	/**
	 * A JButton that when pressed, opens up a window to add a course to the database
	 */
	private JButton addCourseButton;
	
	/**
	 * A JButton used for a user logging out
	 */
	private JButton logoutButton
	
	/**
	 * Constructs a ClientGUI with the supplied width and height dimensions. auth determines whether or not
	 * to show buttons for student or admin use.
	 * @param auth If auth == 1, create a student client, else if auth == 2, create an admin client
	 * @param width The width of the main client window to create
	 * @param height The height of the main client window to create
	 */
	public ClientGUI(int auth, int width, int height) {
		super("Course Registration");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		BorderLayout layout = new BorderLayout(5, 5);
		setLayout(layout);
		
		String loginState = "";
		if(auth == 1) {
			loginState = "Student";
		}else {
			loginState = "Admin";
		}
		JLabel header = new JLabel("You are currently logged in as: " + loginState);
		header.setFont(new Font(header.getFont().getName(), Font.BOLD, height/20));
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		headerPanel.add(header);
		headerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		logoutButton = new JButton("Logout");
		headerPanel.add(logoutButton);
		add(headerPanel, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		
		buttonsPanel = new JPanel();
		BoxLayout buttonLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
		buttonsPanel.setLayout(buttonLayout);
		searchButton = new JButton("Search Courses");
		viewAllButton = new JButton("View All Courses");
		if(auth == 1) {
			JPanel topSubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JPanel bottomSubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			viewAllRegisteredButton = new JButton("View All Registered Courses");
			registerButton = new JButton("Register for a Course");
			deRegisterButton = new JButton("Remove Course Registration");
			topSubPanel.add(searchButton);
			topSubPanel.add(viewAllButton);
			topSubPanel.add(viewAllRegisteredButton);
			bottomSubPanel.add(registerButton);
			bottomSubPanel.add(deRegisterButton);
			buttonsPanel.add(topSubPanel);
			buttonsPanel.add(bottomSubPanel);
		}else {
			JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			addCourseButton = new JButton("Add a Course");
			subPanel.add(searchButton);
			subPanel.add(viewAllButton);
			subPanel.add(addCourseButton);
			buttonsPanel.add(subPanel);
		}
		add(buttonsPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	/**
	 * Adds an ActionListener for searchButton.
	 * @param searchListener The ActionListener to assign to searchButton
	 */
	public void addSearchListener(ActionListener searchListener) {
		searchButton.addActionListener(searchListener);
	}
	
	/**
	 * Adds an ActionListener for viewAllButton.
	 * @param viewAllListener The ActionListener to assign to viewAllButton
	 */
	public void addViewAllListener(ActionListener viewAllListener) {
		viewAllButton.addActionListener(viewAllListener);
	}
	
	/**
	 * Adds an ActionListener for viewAllRegisteredButton.
	 * @param viewAllRegisteredListener The ActionListener to assign to viewAllRegisteredButton
	 */
	public void addViewAllRegisteredListener(ActionListener viewAllRegisteredListener) {
		viewAllRegisteredButton.addActionListener(viewAllRegisteredListener);
	}
	
	/**
	 * Adds an ActionListener for registerButton.
	 * @param registerListener The ActionListener to assign to registerButton
	 */
	public void addRegisterListener(ActionListener registerListener) {
		registerButton.addActionListener(registerListener);
	}
	
	/**
	 * Adds an ActionListener for deRegisterButton.
	 * @param deRegisterListener The ActionListener to assign to deRegisterButton
	 */
	public void addDeRegisterListener(ActionListener deRegisterListener) {
		deRegisterButton.addActionListener(deRegisterListener);
	}
	
	/**
	 * Adds an ActionListener for addCourseButton.
	 * @param addCourseListener The ActionListener to assign to addCourseButton
	 */
	public void addCourseListener(ActionListener addCourseListener) {
		addCourseButton.addActionListener(addCourseListener);
	}
	
	/**
	 * Adds an ActionListener for logoutButton.
	 * @param logoutListener The ActionListener to assign to logoutButton
	 */
	public void addLogoutListener(ActionListener logoutListener) {
		logoutButton.addActionListener(logoutListener);
	}
	
	/**
	 * Displays the supplied String to textArea.
	 * @param text The String to display to textArea
	 */
	public void display(String text) {
		textArea.append(text);
	}
	
	/**
	 * Clears all text on textArea
	 */
	public void clearDisplay() {
		textArea.setText(null);
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
}
