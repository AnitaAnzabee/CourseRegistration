package client;
import model.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A frame that behaves as a view for the login window. Makes use of the MVC design pattern where there is no model, and
 * the controllers are CommunicationController (communication with server) and LoginGUIController (main login window controller).
 * @author Nolan Chan
 * @author Benjamin Nielsen
 * @author Anita
 * @version 1.0
 * @since April 18, 2020
 */
public class LoginGUI extends JFrame{
	
	/**
	 * A JPanel containing text (i.e. header and text if the login authentication has failed) to display on the login window
	 */
	private JPanel textPanel;
	
	/**
	 * A JPanel containing components for user input (i.e. JLabels and JTextAreas/JPasswordField for inputting username and password)
	 * and a return button
	 */
	private JPanel inputPanel;
	
	/**
	 * A JTextArea for the user to enter their username for logging in
	 */
	private JTextArea username;
	
	/**
	 * A JPasswordField for the user to enter their password for logging in
	 */
	private JPasswordField password;
	
	/**
	 * A JButton that when pressed, attempts to authenticate the user with the inputs in username and password
	 */
	private JButton returnButton;
	
	/**
	 * An integer used to indicate whether or not LoginGUI has already displayed an error message denoting if the
	 * supplied login information could not be authenticated. 0 represents that no error message has been displayed,
	 * and 1 otherwise.
	 */
	private int errorState;
	
	/**
	 * Constructs a LoginGUI with the supplied integers for the width and height of the window.
	 * @param width The width of the login window to create
	 * @param height The height of the login window to create
	 */
	public LoginGUI(int width, int height) {
		super("Login");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		errorState = 0;
		
		textPanel = new JPanel();
		inputPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
			
		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		userPanel.add(new JLabel("Username"));
		username = new JTextArea(1, 25);
		username.setLineWrap(true);
		userPanel.add(username);
		
		passPanel.add(new JLabel("Password"));
		password = new JPasswordField(25);
		passPanel.add(password);
		
		inputPanel.add(userPanel);
		inputPanel.add(passPanel);
		
		JLabel header = new JLabel("Please enter your login credentials");
		header.setFont(new Font(header.getFont().getName(), Font.BOLD, height/10));
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		textPanel.add(header);
		
		JPanel login = new JPanel();
		login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
		login.add(textPanel);
		login.add(Box.createRigidArea(new Dimension(0, 5)));
		login.add(inputPanel);
		returnButton = new JButton("Return");
		returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.add(returnButton);
		login.add(Box.createRigidArea(new Dimension(0, 10)));
		
		add(login);
		setVisible(true);
	}
	
	/**
	 * Adds an ActionListener to the return button.
	 * @param returnListener The ActionListener to assign to the return button
	 */
	public void addReturnListener(ActionListener returnListener) {
		returnButton.addActionListener(returnListener);
	}
	
	/**
	 * Returns the errorState of the LoginGUI.
	 * @return The errorState of the LoginGUI
	 */
	public int getErrorState() {
		return errorState;
	}
	
	/**
	 * Returns the String entered in the username JTextArea.
	 * @return The String entered in the username JTextArea
	 */
	public String getUsername() {
		return username.getText();
	}
	
	/**
	 * Returns the text entered in the password JPasswordField.
	 * @return A String containing the text entered in the password JPasswordField
	 */
	public String getPassword() {
		char [] passwordArray = password.getPassword();
		String password = "";
		for(char c : passwordArray) {
			password += c;
		}
		return password;
	}
	
	/**
	 * Adds an error message to textPanel with information that the user could not be authenticated.
	 */
	public void addLoginError() {
		JLabel error = new JLabel("Invalid login information... Please try again");
		error.setForeground(Color.RED);
		error.setAlignmentX(CENTER_ALIGNMENT);
		textPanel.add(error);
		errorState = 1;
		setVisible(true);
	}
}
