import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


/**
 * A class acts as a controller for the RegisterWindow (view) and StudentMaintainerModel
 * (model) in the MVC design pattern for the register GUI. Contains inner-classes for ActionListeners that are
 * listeners for the buttons in RegisterWindow.
 * @author Benjamin Nielsen
 * @version 1.0
 * @since April 10, 2020
 */
public class RegisterWindowController {
	
	/**
	 * The view aspect of this MVC design pattern for the Register GUI
	 */
	private RegisterWindow window;
	private GUIController clientController;
	private CommunicationController commCtrl;
	
	/**
	 * Constructs an RegisterWindowController with the supplied StudentMaintainerModel and RegisterWindow.
	 * Assigns ActionListeners to the buttons in RegisterWindow.
	 * @param model The StudentMaintainerModel to assign to RegisterWindowController
	 * @param window The RegisterWindow to assign to RegisterWindowController
	 */
	public RegisterWindowController(RegisterWindow window, GUIController clientController, CommunicationController commCtrl) {
		this.window = window;
		this.clientController = clientController;
		this.commCtrl = commCtrl;
		
		window.addGetCourseOfferingsListener(new GetCourseOfferingsListener());
		window.addReturnListener(new ReturnListener());
	}
	
	private class GetCourseOfferingsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] courseOfferingsListing = {"null"};
			
			try {
				String name = window.getName();
				int number = Integer.parseInt(window.getNumber());
				
				Course selectedCourse = commCtrl.searchCourse(name + number);
				if(selectedCourse == null || selectedCourse.getSections().isEmpty()) {
					window.displayErrorMessage("Error: Course not found or course has no offerings");
				}else {
					courseOfferingsListing = new String[selectedCourse.getSections().size()];
					for(int i = 0; i < courseOfferingsListing.length; i++) {
						courseOfferingsListing[i] = (selectedCourse.getCourseName() + " L0" + selectedCourse.getSections().get(i).getSectionNum() + " " + selectedCourse.getSections().get(i).getSectionId());
					}
				}
			}catch (NumberFormatException ex) {
				window.displayErrorMessage("Error: Input for course number not an integer");
			}
			
			if(courseOfferingsListing != null && window.getWindowState() == 0) {
				window.setWindowState(1);
				
				window.addSelectionPanel(courseOfferingsListing);
				window.addRegisterListener(new RegisterButtonListener());
			}else if(courseOfferingsListing != null && window.getWindowState() == 1) {
				window.updateSelectionPanel(courseOfferingsListing);
			}
		}
	}
	
	/**
	 * An inner-class for RegisterWindowController that acts as an ActionListener for the
	 * register button in its RegisterWindow.
	 * @author Nolan Chan
	 * @version 1.0
	 * @since April 10, 2020
	 */
	private class RegisterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String selectedCourseOffering = window.getSelectedItem();
			String [] offeringInfo = selectedCourseOffering.split(" ");
			String result = commCtrl.registerCourse(clientController.getModel().getUser().getId() + "", offeringInfo[2]);
			window.displayMessage(result);
		}
	}
	
	/**
	 * An inner-class for RegisterWindowController that acts as an ActionListener for the
	 * return button in its RegisterWindow.
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
