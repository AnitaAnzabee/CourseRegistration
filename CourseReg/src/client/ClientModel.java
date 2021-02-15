package client;

import model.User;

/**
 * The class that acts as a model for the client application. Stores info
 * about the user currently logged in.
 * @author Nolan Chan
 * @author Benjamin Nielsen
 * @author Anita
 * @version 1.0
 * @since April 18, 2020
 *
 */
public class ClientModel {
	
	/**
	 * A User object storing information about the user currently logged in
	 */
	private User user;
	
	/**
	 * Constructs a ClientModel with the supplied User.
	 * @param user Ther User to assign to user
	 */
	public ClientModel(User user) {
		this.user = user;
	}
	
	/**
	 * Returns the User stored in ClientModel
	 * @return The User stored in ClientModel
	 */
	public User getUser() {
		return user;
	}
}
