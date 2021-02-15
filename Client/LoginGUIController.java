import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LoginGUIController {
	private CommunicationController commCtrl;
	private LoginGUI view;
	private int auth;
	
	public LoginGUIController(LoginGUI view, CommunicationController commCtrl) {
		this.commCtrl = commCtrl;
		this.view = view;
		
		view.addReturnListener(new ReturnButtonListener());
	}
	
	public int getAuth() {
		return auth;
	}
	
	private class ReturnButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = view.getUsername();
			String password = view.getPassword();
			
			Student user = commCtrl.checkAuthentication(username, password);
			
			if(user == null && view.getErrorState() == 0) {
				view.addLoginError();
			}else if(!user.getName().equals("admin")) {
				view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
				ClientGUI clientView = new ClientGUI(1, 600, 500);
				ClientModel clientModel = new ClientModel(user);
				GUIController clientController = new GUIController(1, clientModel, clientView, commCtrl);
			}else if(user.getName().equals("admin")) {
				view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
				ClientGUI clientView = new ClientGUI(2, 600, 500);
				ClientModel clientModel = new ClientModel(user);
				GUIController clientController = new GUIController(2, clientModel, clientView, commCtrl);
			}
		}		
	}
}
