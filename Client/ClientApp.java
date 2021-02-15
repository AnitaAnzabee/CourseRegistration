
public class ClientApp {
	public static void main(String[] args) {		
		CommunicationController commCtrl = new CommunicationController("localhost", 8099);
		
		
		LoginGUI loginView = new LoginGUI(400, 170);
		LoginGUIController loginController = new LoginGUIController(loginView, commCtrl);
	}
}
