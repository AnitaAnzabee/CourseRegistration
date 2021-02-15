import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClientGUI extends JFrame{
	private JPanel buttonsPanel;
	
	private JTextArea textArea;
	
	private JButton searchButton;
	private JButton viewAllButton;
	private JButton viewAllRegisteredButton;
	private JButton registerButton;
	private JButton deRegisterButton;
	private JButton addCourseButton;
	private JButton logoutButton;
	
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
	
	public void addSearchListener(ActionListener searchListener) {
		searchButton.addActionListener(searchListener);
	}
	public void addViewAllListener(ActionListener viewAllListener) {
		viewAllButton.addActionListener(viewAllListener);
	}
	public void addViewAllRegisteredListener(ActionListener viewAllRegisteredListener) {
		viewAllRegisteredButton.addActionListener(viewAllRegisteredListener);
	}
	public void addRegisterListener(ActionListener registerListener) {
		registerButton.addActionListener(registerListener);
	}
	public void addDeRegisterListener(ActionListener deRegisterListener) {
		deRegisterButton.addActionListener(deRegisterListener);
	}
	public void addCourseListener(ActionListener addCourseListener) {
		addCourseButton.addActionListener(addCourseListener);
	}
	public void addLogoutListener(ActionListener logoutListener) {
		logoutButton.addActionListener(logoutListener);
	}
	
	public void display(String text) {
		textArea.append(text);
	}
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
}
