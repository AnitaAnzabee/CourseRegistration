import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


/**
 * A class acts as a controller for the RemoveCourseWindow (view) and StudentMaintainerModel
 * (model) in the MVC design pattern for the register GUI. Contains inner-classes for ActionListeners that are
 * listeners for the buttons in RemoveCourseWindow.
 * @author Benjamin Nielsen
 * @version 1.0
 * @since April 10, 2020
 */
public class RemoveCourseWindowController {
	/**
	 * The view aspect of this MVC design pattern for the Remove course GUI
	 */
	private RemoveCourseWindow window;
	private GUIController clientController;
	private CommunicationController commCtrl;
	
	/**
	 * Constructs an RemoveCourseWindowController with the supplied StudentMaintainerModel and RemoveCourseWindow.
	 * Assigns ActionListeners to the buttons in RemoveCourseWindow.
	 * @param model The StudentMaintainerModel to assign to RemoveCourseWindowController
	 * @param window The RemoveCourseWindow to assign to RemoveCourseWindowController
	 */
	public RemoveCourseWindowController(RemoveCourseWindow window, GUIController clientController, CommunicationController commCtrl) {
		this.window = window;
		this.clientController = clientController;
		this.commCtrl = commCtrl;
		
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
			String courseInfo = window.getSelectedItem();
			Registration removedRegistration = null;
			for(Registration r : window.getRegistrationList()) {
				if(courseInfo.equals(r.toString())) {
					removedRegistration = r;
					break;
				}
			}

			String result = commCtrl.removeRegistration(removedRegistration.getStudentId() + "", removedRegistration.getSectionId() + "");
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
