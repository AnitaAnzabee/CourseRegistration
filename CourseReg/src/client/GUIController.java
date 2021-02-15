package client;
import model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * A class that acts as the main controller for the main client window. Makes use of the MVC design pattern where the model
 * is ClientModel (stores user info), the view is ClientGUI, and another controller is CommunicationController (for server
 * communication).
 * @author Nolan Chan
 * @author Benjamin Nielsen
 * @author Anita
 * @version 1.0
 * @since April 18, 2020
 */
public class GUIController {
	
	/**
	 * CommunicationController used for all communication with the server
	 */
	private CommunicationController commCtrl;
	
	/**
	 * The model in this MVC relationship, stores user data
	 */
	private ClientModel model;
	
	/**
	 * The view in this MVC relationship
	 */
	private ClientGUI view;
	
	/**
	 * If loginState == 1, GUIController controls a student GUI, else if loginState == 2, GUIController controls an admin GUI
	 */
	private int loginState;
	
	/**
	 * Constructs a GUIController with the supplied loginState, ClientModel, ClientGUI, and CommunicationController.
	 * @param loginState If loginState == 1, GUIController controls a student GUI, else if loginState == 2, GUIController controls an admin GUI
	 * @param model The ClientModel to assign to the Controller
	 * @param view The ClientGUI to assign to the Controller
	 * @param commCtrl The CommunicationController to assign to GUIController
	 */
	public GUIController(int loginState, ClientModel model, ClientGUI view, CommunicationController commCtrl) {
		this.model = model;
		this.view = view;
		this.loginState = loginState;
		this.commCtrl = commCtrl;
		
		view.addSearchListener(new SearchButtonListener());
		view.addViewAllListener(new ViewButtonListener());
		view.addLogoutListener(new LogoutButtonListener());
		if(loginState == 1) {
			view.addViewAllRegisteredListener(new ViewRegisteredButtonListener());
			view.addRegisterListener(new RegisterButtonListener());
			view.addDeRegisterListener(new DeRegisterButtonListener());
		}else {
			view.addCourseListener(new AddCourseButtonListener());
		}
	}
	
	/**
	 * Returns the model portion of the client application, which stores user info.
	 * @return The model portion of the client application
	 */
	public ClientModel getModel() {
		return model;
	}
	
	/**
	 * Displays the supplied String to the JTextArea on view by first clearing it and then displaying the supplied
	 * String.
	 * @param text The String to display on the view JTextArea
	 */
	public void display(String text) {
		view.clearDisplay();
		view.display(text);
	}
	
	/**
	 * An inner class that is the ActionListener for searchButton in the main client GUI. When an action is performed
	 * creates a view and controller for the search course GUI.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class SearchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			SearchWindow search = new SearchWindow(300, 125);
			SearchWindowController searchController = new SearchWindowController(search, GUIController.this, commCtrl);
		}
	}
	
	/**
	 * An inner class that is the ActionListener for viewAllButton in the main client GUI. When an action is performed,
	 * receives all courses from the server to display on view's JTextArea.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class ViewButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			CourseCatalog allCourses = commCtrl.receiveAllCourses();
			String text = "";
			if(allCourses == null || allCourses.getCatalog().isEmpty()){
				text = "No courses in database";
			}else{
				text = allCourses.toString();
			}
	
			GUIController.this.display(text);
		}
	}
	
	/**
	 * An inner class that is the ActionListener for viewAllRegisteredButton in the main client GUI. When an action is performed,
	 * receives all registered courses for the user from the server to display on view's JTextArea.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class ViewRegisteredButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Registration> allRegisteredCourses = commCtrl.receiveRegisteredCourses(model.getUser().getId());
			String text = "";
			if(allRegisteredCourses == null || allRegisteredCourses.isEmpty()){
				text = "No registered courses";
			}else{
				text += "---------------------------------------------------\n";
				for(Registration r : allRegisteredCourses) {
					text += r.toString()+"\n" +  
						"---------------------------------------------------\n";
				}
			}
			
			GUIController.this.display(text);
		}
	}
	
	/**
	 * An inner class that is the ActionListener for registerButton in the main client GUI. When an action is performed,
	 * constructs a view and controller for the register GUI.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class RegisterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			RegisterWindow register = new RegisterWindow(425, 160);
			RegisterWindowController registerController = new RegisterWindowController(register, GUIController.this, commCtrl);
		}
	}
	
	/**
	 * An inner class that is the ActionListener for deRegisterButton in the main client GUI. When an action is performed,
	 * constructs a view and controller for the remove registration GUI by first getting all registered courses from the 
	 * server.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class DeRegisterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Registration> allRegisteredCourses = commCtrl.receiveRegisteredCourses(model.getUser().getId());
			if(allRegisteredCourses == null || allRegisteredCourses.isEmpty()) {
				GUIController.this.display("Error: You are not registered in any courses");
			}else {
				RemoveCourseWindow deRegister = new RemoveCourseWindow(500, 160, allRegisteredCourses);
				RemoveCourseWindowController deRegisterController = new RemoveCourseWindowController(deRegister, GUIController.this, commCtrl);
			}
		}
	}
	
	/**
	 * An inner class that is the ActionListener for addCourseButton in the main client GUI. When an action is performed,
	 * constructs a view and controller for the add course GUI.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class AddCourseButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			AddCourseWindow addCourseView = new AddCourseWindow(350, 210);
			AddCourseWindowController addCourseController = new AddCourseWindowController(addCourseView, commCtrl);
		}
	}
	
	/**
	 * An inner class that is the ActionListener for logoutButton in the main client GUI. When an action is performed, closes
	 * the main client GUI, constructs a view and controller for another login GUI and sends a message to the server that the
	 * user has logged out.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class LogoutButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String result = commCtrl.logout(model.getUser().getId());
			if(result == null) {
				view.displayErrorMessage("Error: An error occured when logging out");
			}else {
				view.displayMessage(result);
			}
			LoginGUI loginView = new LoginGUI(400, 170);
			LoginGUIController loginController = new LoginGUIController(loginView, commCtrl);
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
		}
	}
}
