import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginGUI extends JFrame{
	private JPanel textPanel;
	private JPanel inputPanel;
	
	private JTextArea username;
	private JTextArea password;
	private JButton returnButton;
	
	private int errorState;
	
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
		password = new JTextArea(1, 25);
		password.setLineWrap(true);
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
	
	public void addReturnListener(ActionListener returnListener) {
		returnButton.addActionListener(returnListener);
	}
	
	public int getErrorState() {
		return errorState;
	}
	
	public String getUsername() {
		return username.getText();
	}
	public String getPassword() {
		return password.getText();
	}
	
	public void addLoginError() {
		JLabel error = new JLabel("Invalid login information... Please try again");
		error.setForeground(Color.RED);
		error.setAlignmentX(CENTER_ALIGNMENT);
		textPanel.add(error);
		errorState = 1;
		setVisible(true);
	}
	
	
}
