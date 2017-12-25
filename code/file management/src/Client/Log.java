package Client;

import java.io.*;
import java.sql.*;

public class Log implements Serializable {
	private String maker;
	private String made;
	private String event;
	private Timestamp time;
	public Log(String maker,String made,String event,Timestamp time){
		this.maker = maker;
		this.made = made;
		this.event = event;
		this.time = time;
	}
	public String getMaker() {
		return maker;
	}
	public String getMade() {
		return made;
	}
	public String getEvent() {
		return event;
	}
	public Timestamp getTime() {
		return time;
	}
}
