import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class GUIController {
	private CommunicationController commCtrl;
	private ClientModel model;
	private ClientGUI view;
	private int loginState;
	
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
	
	public ClientModel getModel() {
		return model;
	}
	
	public void display(String text) {
		view.clearDisplay();
		view.display(text);
	}
	
	private class SearchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			SearchWindow search = new SearchWindow(300, 125);
			SearchWindowController searchController = new SearchWindowController(search, GUIController.this, commCtrl);
		}
	}
	
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
	
	private class RegisterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			RegisterWindow register = new RegisterWindow(425, 160);
			RegisterWindowController registerController = new RegisterWindowController(register, GUIController.this, commCtrl);
		}
	}
	
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
	
	private class AddCourseButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			AddCourseWindow addCourseView = new AddCourseWindow(350, 210);
			AddCourseWindowController addCourseController = new AddCourseWindowController(addCourseView, commCtrl);
		}
	}
	
	private class LogoutButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginGUI loginView = new LoginGUI(400, 170);
			LoginGUIController loginController = new LoginGUIController(loginView, commCtrl);
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
		}
	}
}