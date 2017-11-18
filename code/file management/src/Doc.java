import java.sql.Timestamp;

public class Doc {
	private String number;
	private String owner;
	private Timestamp timestamp;
	private String description;
	private String path;
	public Doc(String n,String o,Timestamp t,String d,String p){
		number = n;
		owner = o;
		timestamp = t;
		description = d;
		path = p;
	}
	public String getNumber() {
		return number;
	}
	public String getOwner() {
		return owner;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public String getDescription() {
		return description;
	}
	public String getPath() {
		return path;
	}
}
