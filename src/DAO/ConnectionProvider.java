package DAO;

import java.sql.Connection;

import java.sql.DriverManager;

public class ConnectionProvider {
	public static Connection getCon() {
		try {
			Connection connection;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL="jdbc:sqlserver://TIEN-IT\\SQLEXPRESS;databaseName=ElteeStore;user=sa;password=123";
			connection=DriverManager.getConnection(connectionURL);
			return connection;
		}
		catch(Exception e){
			return null;
		}
	}
	public static void main(String[] args) {
		try {
			Connection connection;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL="jdbc:sqlserver://TIEN-IT\\SQLEXPRESS;databaseName=ElteeStore;user=sa;password=123";
			connection=DriverManager.getConnection(connectionURL);
			System.out.print("ketnoithanhcong");
		}
		catch(Exception e){
			System.out.print("ketnoithatbai");
		}
	}
}
