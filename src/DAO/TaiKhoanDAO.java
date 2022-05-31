	package DAO;


import java.sql.ResultSet;
import java.util.*;
import entities.NhanVien;

public class TaiKhoanDAO {
	
	public NhanVien Login(String userName,String Password){
		NhanVien a = null;
		try {
			ResultSet rs = DbOperations.getData("select a.* from NhanVien a where a.TaiKhoan = '" + userName + "' and a.MatKhau = '"+ Password +"'");
			while (rs.next()) {
				a = new NhanVien();
				a.setTaiKhoan(rs.getString("TaiKhoan"));
				a.setMatKhau(rs.getString("MatKhau"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	public static boolean SaveorUpdate(NhanVien nv) {
		String query = "INSERT INTO NhanVien (TenNV, Email, TaiKhoan,MatKhau,Quyen)"
				+ "VALUES (N'"+ nv.getTenNv() +"','"+ nv.getEmail() +"','"+ nv.getTaiKhoan() +"','"+ nv.getMatKhau() +"',0);" ;
		try {
			if(DbOperations.setData(query, null) == true) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception ex) {
			
		}
		return false;
	}
	
	public void Update(Integer UserName ,String PassWord) {
		try {
			String query = "update NhanVien set MatKhau = '"+PassWord+"' where IDNV = '"+UserName+"'" ;
			DbOperations.setData(query, "Update");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void Update(Integer ID,String Email,String TenNV) {
		try {
			String query = "update NhanVien set Email = '"+ Email +"',TenNV = N'"+ TenNV +"' where IDNV = '"+ID+"'" ;
			DbOperations.setData(query, "Update");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public NhanVien find(String username) {
		NhanVien a = new NhanVien();
		try {
			ResultSet rs = DbOperations.getData("select a.* from NhanVien a where a.TaiKhoan = '" + username + "' ");
			while (rs.next()) {
				a.setIdnv(rs.getInt("IDNV"));
				a.setTenNv(rs.getString("TenNV"));
				a.setEmail(rs.getString("Email"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	public NhanVien findName(String username) {
		NhanVien a = new NhanVien();
		try {
			ResultSet rs = DbOperations.getData("select a.* from NhanVien a where a.TenNV = '" + username + "' ");
			while (rs.next()) {
				a.setIdnv(rs.getInt("IDNV"));
				a.setTenNv(rs.getString("TenNV"));
				a.setEmail(rs.getString("Email"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	public boolean DeleteNV(String TenNV) {
		String query = "delete from NhanVien where TenNv = N'"+TenNV+"'" ;
		try {
			DbOperations.setData(query, "Delete");
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	public static ArrayList<NhanVien> getAll() {
		ArrayList<NhanVien> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData(
					"select * from NhanVien a");
			while (rs.next()) {
				NhanVien a = new NhanVien();
				a.setIdnv(rs.getInt("IDNV"));
				a.setTenNv(rs.getString("TenNV"));
				a.setEmail(rs.getString("Email"));
				a.setQuyen(rs.getBoolean("Quyen"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public NhanVien findbyID(Integer username) {
		NhanVien a = new NhanVien();
		try {
			ResultSet rs = DbOperations.getData("select a.* from NhanVien a where a.IDNV = '" + username + "' ");
			while (rs.next()) {
				a.setIdnv(rs.getInt("IDNV"));
				a.setMatKhau(rs.getString("MatKhau"));
				a.setTenNv(rs.getString("TenNV"));
				a.setEmail(rs.getString("Email"));
				a.setQuyen(rs.getBoolean("Quyen"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	public NhanVien GetTaiKhoan(String username,String MatKhau) {
		NhanVien a = new NhanVien();
		try {
			ResultSet rs = DbOperations.getData("select a.* from NhanVien a where a.TaiKhoan = '" + username + "' and a.MatKhau = '"+ MatKhau +"'");
			while (rs.next()) {
				a.setIdnv(rs.getInt("IDNV"));
				a.setTenNv(rs.getString("TenNV"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
}




