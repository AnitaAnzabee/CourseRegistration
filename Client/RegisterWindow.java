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
    private JButton registerButton;
    
    private JPanel buttonPanel;
    private JPanel selectSectionPanel;
    private JComboBox<String> selectSection;
    private int windowState;

    /**
     * Constructs an RegisterWindow and sets it to be visible
     * @param width The width of this frame
     * @param height The height of this frame
     */
    public RegisterWindow(int width, int height){
        super("Register for a course");
        setSize(width, height);
        layout = new BorderLayout(1,1);
        setLayout(layout);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        windowState = 0;

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

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        getOfferingsButton = new JButton("Get Offerings");
        returnButton = new JButton("Return to Main Window");
        
        buttonPanel.add(getOfferingsButton);
        buttonPanel.add(returnButton);
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        JPanel selectSectionPanel = new JPanel();
        selectSection = new JComboBox<String>();
        selectSectionPanel.add(selectSection);
        southPanel.add(selectSectionPanel);
        southPanel.add(buttonPanel);
        
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    public int getWindowState() {
    	return windowState;
    }
    
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
    
    public String getSelectedItem() {
    	return (String)selectSection.getSelectedItem();
    }
    
    public void addSelectionPanel(String [] courseOfferings) {
    	selectSection.removeAllItems();
    	for(String s : courseOfferings) {
    		selectSection.addItem(s);
    	}
    	registerButton = new JButton("Register");
    	buttonPanel.add(registerButton);
    	setVisible(true);
    }
    
    public void updateSelectionPanel(String [] courseOfferings) {
    	selectSection.removeAllItems();
    	for(String s : courseOfferings) {
    		selectSection.addItem(s);
    	}
    	setVisible(true);
    }
}