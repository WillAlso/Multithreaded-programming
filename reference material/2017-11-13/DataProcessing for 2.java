import java.util.*;
import java.sql.*;


public  class DataProcessing {
	private static Connection con;
	private Statement st;
	private ResultSet rs;
//	private int numberofRows;
	private static boolean connectToDB=false;
	
	static Hashtable<String, User> users;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));
		Init();
	}
	
	public static  void Init(){
		// connect to database
		//con = DriverManager.getConnection( "url", "username", "password" );

		// create Statement to query database
		//st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );

		// update database connection status
		if (Math.random()>0.3)
			connectToDB = true;
		else
			connectToDB = false;
		
	}
	
	
	public static User search(String name, String password) throws SQLException,IllegalStateException {
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );

		
		if (Math.random()>0.7)
			throw new SQLException( "Error in excecuting Query" );
		
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException,IllegalStateException{
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		if (Math.random()>0.7)
			throw new SQLException( "Error in excecuting Query" );
		
		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	
	public static boolean update(String name, String password, String role) throws SQLException,IllegalStateException{
		User user;
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		if (Math.random()>0.7)
			throw new SQLException( "Error in excecuting Update" );
		
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
	
	public static boolean insert(String name, String password, String role) throws SQLException,IllegalStateException{
		User user;
		
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		if (Math.random()>0.7)
			throw new SQLException( "Error in excecuting Insert" );
		
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
	
	public static boolean delete(String name) throws SQLException,IllegalStateException{
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		
		if (Math.random()>0.7)
			throw new SQLException( "Error in excecuting Delete" );
		
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
		
	}	
            
	public void disconnectFromDB() {
		if ( connectToDB ){
			// close Statement and Connection            
			try{
				rs.close();                        
			    st.close();                        
			    con.close();                       
			}catch ( SQLException sqlException ){                                            
			    sqlException.printStackTrace();           
			}finally{                                            
				connectToDB = false;              
			}                             
		} 
   }           

	
	public static void main(String[] args) {		

	}
	
}
