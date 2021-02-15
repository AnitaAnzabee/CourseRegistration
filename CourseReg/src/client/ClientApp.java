package client;

/**
 * The main class that the Client is run from. Initializes a connection to the Sever.
 * @author Nolan Chan
 * @author Benjamin Nielsen
 * @author Anita
 * @version 1.0
 * @since April 18, 2020
 */
public class ClientApp {
	
	/**
	 * The main method that the client is run form.
	 * @param args Unused
	 */
	public static void main(String[] args) {		
		CommunicationController commCtrl = new CommunicationController("localhost", 8099);		
		
		LoginGUI loginView = new LoginGUI(400, 170);
		LoginGUIController loginController = new LoginGUIController(loginView, commCtrl);
	}
}
