import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

/**
 * A class that acts as a view for SearchWindowController (controller) and StudentMaintainerModel
 * (model) in the MVC design pattern. This class is the frame of the search course window
 * in this student maintainer application.
 * @author Benjamin Nielsen
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
        super("Search for a Course");
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
        searchButton = new JButton("Search");
        returnButton = new JButton("Return to Main Window");
        
        southPanel.add(searchButton);
        southPanel.add(returnButton);
        add(southPanel, BorderLayout.SOUTH);

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