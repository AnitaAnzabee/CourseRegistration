package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import model.User;

/**
 * A class that acts as the main controller for the login window. Makes use of the MVC design pattern where there is no model, the
 * view is LoginGUI, and another controller is CommunicationController (for server communication).
 * @author Nolan Chan
 * @author Benjamin Nielsen
 * @author Anita
 * @version 1.0
 * @since April 18, 2020
 */
public class LoginGUIController {
	
	/**
	 * A CommunicationController used for communication with the server
	 */
	private CommunicationController commCtrl;
	
	/**
	 * The view in the MVC relationship
	 */
	private LoginGUI view;
	
	/**
	 * Constructs a LoginGUIController with the supplied LoginGUI and CommunicationController. Adds ActionListeners
	 * to the buttons in the LoginGUI.
	 * @param view The LoginGUI to assign to the LoginGUIController
	 * @param commCtrl The CommunicationController to assign to the LoginGUIController
	 */
	public LoginGUIController(LoginGUI view, CommunicationController commCtrl) {
		this.commCtrl = commCtrl;
		this.view = view;
		
		view.addReturnListener(new ReturnButtonListener());
	}
	
	/**
	 * An inner class that is an ActionListener for the return button in view. When an action is performed, gets the text
	 * entered in the fields for username and password and sends it to the server for authentication.
	 * @author Nolan Chan
	 * @author Benjamin Nielsen
	 * @author Anita
	 * @version 1.0
	 * @since April 18, 2020
	 */
	private class ReturnButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = view.getUsername();
			String password = view.getPassword();
			
			User user = commCtrl.checkAuthentication(username, password);
			
			if(user == null) {
				if(view.getErrorState() == 0) {
					view.addLoginError();
				}
			}else if(!user.getRole().equals("admin")) {
				view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
				ClientGUI clientView = new ClientGUI(1, 600, 500);
				ClientModel clientModel = new ClientModel(user);
				GUIController clientController = new GUIController(1, clientModel, clientView, commCtrl);
			}else if(user.getRole().equals("admin")) {
				view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
				ClientGUI clientView = new ClientGUI(2, 600, 500);
				ClientModel clientModel = new ClientModel(user);
				GUIController clientController = new GUIController(2, clientModel, clientView, commCtrl);
			}
		}		
	}
}
