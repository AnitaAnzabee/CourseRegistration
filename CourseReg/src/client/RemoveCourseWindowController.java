package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import model.Registration;


/**
 * A class acts as a controller for the RemoveCourseWindow (view) and StudentMaintainerModel
 * (model) in the MVC design pattern for the register GUI. Contains inner-classes for ActionListeners that are
 * listeners for the buttons in RemoveCourseWindow.
 * @author Anita, Nolan, Benjamin 
 * @version 1.0
 * @since April 10, 2020
 */
public class RemoveCourseWindowController {
	/**
	 * The view aspect of this MVC design pattern for the Remove course GUI
	 */
	private RemoveCourseWindow window;
	/**
	 * the controller for the client application
	 */
	private GUIController clientController;
	/**
	 * The class used for communication with the server
	 */
	private CommunicationController commCtrl;
	
	/**
	 * Constructs an RemoveCourseWindowController with the supplied StudentMaintainerModel and RemoveCourseWindow.
	 * Assigns ActionListeners to the buttons in RemoveCourseWindow.
	 * @param model The StudentMaintainerModel to assign to RemoveCourseWindowController
	 * @param window The RemoveCourseWindow to assign to RemoveCourseWindowController
	 */
	public RemoveCourseWindowController(RemoveCourseWindow window, GUIController clientController, CommunicationController commCtrl) {
		//sets the connected window
		this.window = window;
		//sets the connected client controller
		this.clientController = clientController;
		//sets the connected communucation controller
		this.commCtrl = commCtrl;
		//adds listeners to the burrons of connected window
		window.addRemoveListener(new RemoveButtonListener());
		window.addReturnListener(new ReturnListener());
	}
	
	/**
	 * An inner-class for RemoveCourseWindowController that acts as an ActionListener for the
	 * remove button in its RemoveCourseWindow.
	 * @author Benjamin Nielsen
	 * @version 1.0
	 * @since April 10, 2020
	 */
	private class RemoveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//fetch selected course info from connected window
			String courseInfo = window.getSelectedItem();
			Registration removedRegistration = null;
			//searches the window's list of registered courses for a match, sets matching item to be removed
			for(Registration r : window.getRegistrationList()) {
				if(courseInfo.equals(r.toString())) {
					removedRegistration = r;
					break;
				}
			}
			//removes selected registered course
			String result = commCtrl.removeRegistration(removedRegistration. getRegisterId() + "", removedRegistration.getSectionId() + "");
			clientController.display(result);
		}
	}
	
	/**
	 * An inner-class for RemoveCourseWindowController that acts as an ActionListener for the
	 * return button in its RemoveCourseWindow.
	 * @author Nolan Chan
	 * @version 1.0
	 * @since March 3, 2020
	 */
	private class ReturnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		}
    }
}
