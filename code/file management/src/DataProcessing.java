import java.util.*;


public  class DataProcessing {
	static Hashtable<String, User> users;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","Operator"));
		users.put("rose", new Browser("rose","123","Browser"));
		users.put("kate", new Administrator("kate","123","Administrator"));		
	}
	
	public static User search(String name, String password){
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser(){
		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	
	public static boolean update(String name, String password, String role){
		User user;
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;
	}
	
	public static boolean insert(String name, String password, String role){
		User user;
		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}
	}
	
	public static boolean delete(String name){
				
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
		
	}
	
}
