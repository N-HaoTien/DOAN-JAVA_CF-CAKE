package DAO;

import java.sql.*;

public class DbOperations {
	public static boolean setData(String query,String msg) {
		try {
			Connection con = ConnectionProvider.getCon();
			Statement st = con.createStatement();
			st.execute(query);
			return true;
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
	public static ResultSet getData(String query) {
		try {
			Connection con = ConnectionProvider.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		}
		catch (Exception e) {
			
			return null;
		}
	}
}
