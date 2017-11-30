import java.sql.*;
import java.util.*;

public class Browser extends User{
	Browser(String name,String password,String role){
		setName(name);
		setPassword(password);
		setRole(role);
	}
}
