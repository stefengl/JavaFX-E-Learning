package model;

import java.sql.*;

public class SQLiteConnector {
	
	 private Connection connection;
	 
	public Connection connector(){
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:9616.sqlite");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return connection;
	}
}
	
