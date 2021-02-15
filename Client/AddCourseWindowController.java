import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


/**
 * A class acts as a controller for the AddCourseWindow (view) and StudentMaintainerModel
 * (model) in the MVC design pattern for the add course GUI. Contains inner-classes for ActionListeners that are
 * listeners for the buttons in AddCourseWindow.
 * @author Benjamin Nielsen
 * @author Nolan Chan
 * @version 1.0
 * @since April 17, 2020
 */
public class AddCourseWindowController {
	
	/**
	 * The view aspect of this MVC design pattern for the add course GUI
	 */
	private AddCourseWindow window;
	
	/**
	 * The class used for communication with the server
	 */
	private CommunicationController commCtrl;
	
	
	/**
	 * Constructs an AddCourseWindowController with the supplied CommunicationController and AddCourseWindow.
	 * Assigns ActionListeners to the buttons in AddCourseWindow.
	 * @param window The AddCourseWindow to assign to AddCourseWindowController
	 * @param commCtrl The CommunicationController to assign to AddCourseWindowController
	 */
	public AddCourseWindowController(AddCourseWindow window, CommunicationController commCtrl) {
		this.window = window;
		this.commCtrl = commCtrl;
		window.addAddCourseListener(new AddButtonListener());
		window.addReturnListener(new ReturnListener());
	}
	
	/**
	 * An inner-class for AddCourseWindowController that acts as an ActionListener for the
	 * add course button in its AddCourseWindow.
	 * @author Benjamin Nielsen
	 * @version 1.0
	 * @since April 17, 2020
	 */
	private class AddButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String name = window.getName();
				int number = Integer.parseInt(window.getNumber());
				int sectionNumber = Integer.parseInt(window.getSectionNumber());
				int sectionCapacity = Integer.parseInt(window.getSectionCapacity());
				
				String result = commCtrl.addCourse(name, number, sectionNumber, sectionCapacity);
				if(result == null) {
					window.displayErrorMessage("Error: Could not add course to database");
				}else {
					window.displayMessage(result);
				}
			}catch (NumberFormatException ex) {
				window.displayErrorMessage("Error: Input for course number or number of sections or section capacity not an integer");
			}
		}
	}
	
	/**
	 * An inner-class for AddCourseWindowController that acts as an ActionListener for the
	 * return button in its AddCourseWindow.
	 * @author Nolan Chan
	 * @version 1.0
	 * @since April 17, 2020
	 */
	private class ReturnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		}
    }
}
