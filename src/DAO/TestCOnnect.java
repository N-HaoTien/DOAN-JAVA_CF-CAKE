package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestCOnnect {
	
	public static void main(String[] args) {
		try {
			Connection connection;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL="jdbc:sqlserver://TIEN-IT\\SQLEXPRESS;databaseName=ElteeStore;user=sa;password=123";
			connection=DriverManager.getConnection(connectionURL);
			if(connection != null) {
				System.out.println("Kết nối Thành Công33 !!!");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Kết nối thất bại !!!");
		}
	}
}
