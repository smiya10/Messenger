package messenger;

import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Server {
	/**
	 CONSTRUCTOR:
	  Initializes the map of registered users and the set of logged-in users to be empty 
	*/
	Map<String, MsgUser> nameToUser;
	Set<MsgUser> users;
	
	public Server() {
		nameToUser = new TreeMap<String, MsgUser>();
		users = new TreeSet<MsgUser>();
	}
	   
	//  METHODS:

	/**
		Registers a new user with a given screen name and password.  Returns 0 if the 
	   	registration is successful. Returns one of the following negative integer codes, 
	   	if the registration failed.
	 	-1   invalid screen name, must be from 4 to 10 characters in length
	 	-2   invalid password, must be from 2 to 10 characters in length
	       	-3   the screen name is already taken
	*/
	public int addUser(String name, String password) {
		if(name.length() < 4 || name.length() > 10){
			return -1;
		}
		else if(password.length() < 2 || name.length() > 10){
			return -2;
		}
		else if(nameToUser.containsKey(name)){
			return -3;
		}else{
			nameToUser.put(name, new MsgUser(this, name, password));
			return 0;
		}
	}
	    

	/**
		 Logs in a new user with a given screen name and password.  Returns 0, if successful,
		 and a negative integer error code, if failed.  
		Error codes:
			-1 user not found
			-2 invalid password
	 		-3 user is already logged in
		 This method creates a new MsgUser object and adds it to the “buddy lists” of all
		  previously logged-in users by calling their addBuddy method. If opens a dialog 
		  window for this user by calling its openDialog method and passing all previously
		  logged-in users to it as a “buddy list.” It then adds the new user to the set of 
		  logged-in users.
	*/
	public int login(String name, String password){ 
		if(!nameToUser.containsKey(name)){
			return -1;
		}else if(!nameToUser.get(name).getPassword().equals(password)){
			return -2;
		}else if(users.contains(nameToString.get(name))){
			return -3;
		}else{
			MsgUser user = new MsgUser(this, name, password);
			for (MsgUser u:users){
				u.addBuddy(user);
				users.addBuddy(u)
			}
			user.openDialog
			users.add(user);
		}
	}
	    
	/**
	  Removes a given user from the set of logged-in users and from the ‘buddy lists’ of 
	   all other logged-in users.  
	*/
	public void logout(MsgUser u){ 
		users.remove(u);
		for(MsgUser user:users){
			user.removeBuddy(u);
		}
	}
}
