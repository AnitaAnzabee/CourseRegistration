package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import model.Course;


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
	/**
	 * the controller for the client application
	 */
	private GUIController clientController;
	/**
	 * The class used for communication with the server
	 */
	private CommunicationController commCtrl;
	
	/**
	 * Constructs an RegisterWindowController with the supplied StudentMaintainerModel and RegisterWindow.
	 * Assigns ActionListeners to the buttons in RegisterWindow.
	 * @param model The StudentMaintainerModel to assign to RegisterWindowController
	 * @param window The RegisterWindow to assign to RegisterWindowController
	 */
	public RegisterWindowController(RegisterWindow window, GUIController clientController, CommunicationController commCtrl) {
		//sets the window
		this.window = window;
		//sets the client controller
		this.clientController = clientController;
		//sets the communication controller
		this.commCtrl = commCtrl;
		//adds listeners to the buttons of the window
		window.addGetCourseOfferingsListener(new GetCourseOfferingsListener());
		window.addReturnListener(new ReturnListener());
	}
	
	private class GetCourseOfferingsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String [] courseOfferingsListing = {"null"};
			
			try {
				//fetches course information from the window
				String name = window.getName();
				int number = Integer.parseInt(window.getNumber());
				//searches the database for course using the communication controller
				Course selectedCourse = commCtrl.searchCourse(name + number);
				if(selectedCourse == null || selectedCourse.getSections().isEmpty()) {
					//display error message if course is not found
					window.displayErrorMessage("Error: Course not found or course has no offerings");
				}else {
					//makes useful String array out of fetched courses
					courseOfferingsListing = new String[selectedCourse.getSections().size()];
					for(int i = 0; i < courseOfferingsListing.length; i++) {
						courseOfferingsListing[i] = (selectedCourse.getCourseName() + " L0" + selectedCourse.getSections().get(i).getSectionNum() + " " + selectedCourse.getSections().get(i).getSectionId());
					}
				}
			}catch (NumberFormatException ex) {
				//displays error if inputs are not integers
				window.displayErrorMessage("Error: Input for course number not an integer");
			}
			
			if(courseOfferingsListing != null && window.getWindowState() == 0) {
				//changes window state to registration if course offerings are found
				window.setWindowState(1);
				
				//makes an offering selection menu in the connected register window
				window.addSelectionPanel(courseOfferingsListing);
				//adds a listener to the regidter button of the connected window
				window.addRegisterListener(new RegisterButtonListener());
			}else if(courseOfferingsListing != null && window.getWindowState() == 1) {
				//Only update information about course offering without adding new register button if window 
				//has already entered registration stae
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
			//fetch selected offering from connected window
			String selectedCourseOffering = window.getSelectedItem();
			//Split fetched information into useful Strings
			String [] offeringInfo = selectedCourseOffering.split(" ");
			//registers logged in student in selected course offering
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
