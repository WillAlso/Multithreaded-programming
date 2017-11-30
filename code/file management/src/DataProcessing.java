import java.util.Enumeration;
import java.util.Hashtable;


import java.sql.*;



@SuppressWarnings("unused")
public  class DataProcessing {
	private static Connection con;
	private static Statement st;
	private ResultSet rs;
	private int numberofRows;
	private static boolean connectToDB=false;
	
	static {
		Init();
	}
	
	public static  void Init(){
		String url = "jdbc:mysql://localhost:3306/foruser";
		String username = "root";
		String password = "12345678";
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			con = DriverManager.getConnection( url, username, password);
			st = con.createStatement();
			connectToDB = true;
		} catch (SQLException e) {
			connectToDB = false;
			e.printStackTrace();
			System.out.println("Not Connected to Database");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Doc searchDoc(String ID) throws SQLException,IllegalStateException {
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		String sql = "SELECT * FROM doc WHERE number = '"+ID+"'";
		ResultSet result = st.executeQuery(sql);
		if(result.next()){
			return new Doc(result.getString("number"),result.getString("owner"),result.getTimestamp("timestamp"),
					result.getString("description"),result.getString("filename"));
		}
		return null;
	}
	
	public static Doc[] getAllDocs() throws SQLException,IllegalStateException{
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		String sql = "SELECT * FROM doc";
		ResultSet result = st.executeQuery(sql);
		result.last();
		Doc[] doc = new Doc[result.getRow()];
		result.first();
		int c = 0;
		String ID;
		do{
			 ID = result.getString(1);
			String owner = result.getString("owner");
			Timestamp time = result.getTimestamp("timestamp");
			String des = result.getString("description");
			String name = result.getString("filename");
			doc[c++] = new Doc(ID,owner,time,des,name);
		}while(result.next());
		return doc;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException,IllegalStateException{
		Doc doc;	
		String sql = "INSERT INTO doc VOLUES ('"+ID+"','"+creator+"',"+timestamp+",'"+description+"','"+filename+"')";
		if(st.execute(sql)){
			return true;
		}
		return false;
	} 
	
	
	public static User searchUser(String name, String password) throws SQLException,IllegalStateException {
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		String sql = "SELECT * FROM user WHERE username = '"+name+"'";
		ResultSet result = st.executeQuery(sql);
		if(result.next()){
			if(password.equals(result.getString("password"))){
				String role = result.getString("role");
				if(role.equals("administrator")){
					return new Administrator(name,password,role);
				}else if(role.equals("operator")){
					return new Operator(name,password,role);
				}else{
					return new Browser(name,password,role);
				}
			}
		}
		return null;
	}
	
	public  static User[] getAllUser() throws SQLException,IllegalStateException{
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		String sql = "SELECT * FROM user";
		ResultSet result = st.executeQuery(sql);
		result.last();
		User[] temp = new User[result.getRow()];
		result.first();
		int c = 0;
		do{
			if(result.getString("role").equals("administrator")){
				temp[c++] = new Administrator(result.getString("username"),result.getString("password"),result.getString("role"));
			}else if(result.getString("role").equals("operator")){
				temp[c++] = new Operator(result.getString("username"),result.getString("password"),result.getString("role"));
			}else{
				temp[c++] = new Browser(result.getString("username"),result.getString("password"),result.getString("role"));
			}
		}while(result.next());
		return temp;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException,IllegalStateException{
		if ( !connectToDB ) 
			throw new IllegalStateException( "Not Connected to Database" );
		String sql = "UPDATE user SET username = '"+name+"' password = '"+password+"' role = '"+role+"' WHERER username = '"+name+"'";
		if(st.execute(sql)){
			return true;
		}
		return false;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException,IllegalStateException{
		User user;
		if ( !connectToDB ) 
			throw new IllegalStateException( "Not Connected to Database" );
		String sql = "INSERT INTO user VALUES ('"+name+"','"+password+"','"+role+"')";
		if(st.execute(sql)){
			return true;
		}
		return false;
	}
	
	public static boolean deleteUser(String name) throws SQLException,IllegalStateException{
		if ( !connectToDB ) 
	        throw new IllegalStateException( "Not Connected to Database" );
		String sql = "DELETE FROM user WHERE username = '"+name+"'";
		if (st.execute(sql)){
			return true;
		}else
			return false;
	}	
            
	
           
}
