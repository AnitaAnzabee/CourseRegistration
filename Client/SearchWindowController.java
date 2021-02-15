import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


/**
 * A class acts as a controller for the SearchWindow (view) and StudentMaintainerModel
 * (model) in the MVC design pattern for the search GUI. Contains inner-classes for ActionListeners that are
 * listeners for the buttons in SearchWindow.
 * @author Benjamin Nielsen
 * @version 1.0
 * @since April 10, 2020
 */
public class SearchWindowController {
	/**
	 * The view aspect of this MVC design pattern for the search GUI
	 */
	private SearchWindow window;
	private GUIController clientController;
	private CommunicationController commCtrl;
	
	/**
	 * Constructs an SearchWindowController with the supplied StudentMaintainerModel and SearchWindow.
	 * Assigns ActionListeners to the buttons in SearchWindow.
	 * @param model The StudentMaintainerModel to assign to SearchWindowController
	 * @param window The SearchWindow to assign to SearchWindowController
	 */
	public SearchWindowController(SearchWindow window, GUIController clientController, CommunicationController commCtrl) {
		this.window = window;
		this.clientController = clientController;
		this.commCtrl = commCtrl;
		
		window.addSearchListener(new SearchButtonListener());
		window.addReturnListener(new ReturnListener());
	}
	
	/**
	 * An inner-class for SearchWindowController that acts as an ActionListener for the
	 * search button in its SearchWindow.
	 * @author Benjamin Nielsen
	 * @version 1.0
	 * @since April 10, 2020
	 */
	private class SearchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String courseName = window.getName();
				int courseNumber = Integer.parseInt(window.getNumber());
				
				
				Course searchCourse = commCtrl.searchCourse("" + courseName + courseNumber);
				if(searchCourse == null){
					clientController.display("Could not find course");
					window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
				}else{
					clientController.display(searchCourse.toString());
					window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
				}
				
			}catch (NumberFormatException ex) {
				window.displayErrorMessage("Error: Input for course number not an integer");
			}
		}
	}
	
	/**
	 * An inner-class for SearchWindowController that acts as an ActionListener for the
	 * return button in its SearchWindow.
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
