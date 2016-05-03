package messenger;

public class MsgUser implements Comparable<MsgUser>{
	String userName, userPassword;
	Server userServer;
	MsgWindow myWindow;

	/**
	CONSTRUCTOR: saves a reference to the server and initializes this user’s 
                                screen name and password
	 */
	public MsgUser(Server server, String name, String password) {
		userServer = server;
		userPassword = password;
		userName = name;
	}

	// METHODS:

	/**
 	returns this user’s screen name
	 */
	public String toString() {
		return userName;
	}

	/**
	 returns this user’s password
	 */
	public String getPassword() {
		return userPassword;
	}

	/**
 returns true if this user’s name is equal to other’s (case insensitive), false otherwise
	 */
	public boolean equals (MsgUser other) {
		if(this.toString().equals(other.toString()))
			return true;
		return false;
	}

	/**
	 Compares user’s screen name to other’s screen name, case insensitive.
  	Note: we have implemented Comparable<MsgUser> so no need to make other
  	an Object type 
	 */   
	public int compareTo(MsgUser other) {
		String otherName = other.toString().toLowerCase();
		String thisName = this.toString().toLowerCase();
		if(thisName.compareTo(otherName) < 0)
			return -1;
		else if(this.userName.compareTo(otherName) > 0)
			return 1;
		return 0;
	} 

	/**
	 Only needed for the extra credit – commented out until you get to that point
	 */
	//    public int hashCode()  { }

	/**
  	Creates a dialog window passing this user and the buddies set to its constructor. 
   	Saves a reference to the new dialog window in the myWindow field. 
	 */    
	public void openDialog(Set<MsgUser> buddies) {
		myWindow = new MsgWindow(this, buddies);
	} 

	/**
	  If myWindow is initialized, adds u to this user’s ‘buddy list’ by 
	   calling myWindow.addBuddy(u);
	 */
	public void addBuddy(MsgUser u) {
		if(myWindow != null){
			myWindow.addBuddy(u);
		}
	}

	/**
	 If myWIndow is initialized, removes u from this user’s ‘buddy list’ by calling
	 myWindow.removeBuddy(u)
	 */
	public void removeBuddy(MsgUser u) { 
		if(myWindow != null)
			myWindow.removeBuddy(u);
	}

	/**
 	if myWindow is initialized, shows text by calling myWindow.showMessage(text)
	 */
	public void receiveMessage(String text){
		if(myWindow != null)
			myWindow.showMessage(text);
	}

	/**
 	Disposes of this user’s dialog window. Logs out this user by calling server’s logout method.  (This method is called from the MsgWindow class when the ‘close’ button is clicked on the dialog window.) 
	 */  
	public void quit() {
		userServer.logout(this);
	}
}