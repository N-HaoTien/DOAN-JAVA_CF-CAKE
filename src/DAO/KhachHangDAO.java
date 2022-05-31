package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.KhachHang;
import entities.SanPham;

public class KhachHangDAO {

	public static ArrayList<KhachHang> getAll() {
		ArrayList<KhachHang> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData(
					"select * from KhachHang a order by a.Level");
			while (rs.next()) {
				KhachHang a = new KhachHang();
				a.setIdkh(rs.getInt("IDKH"));
				a.setDiaChi(rs.getString("DiaChi"));
				a.setSdt(rs.getInt("SDT"));
				a.setTenKh(rs.getString("TenKH"));
				a.setNgayCapThe(rs.getDate("NgayCapThe"));
				a.setLevel(rs.getString("Level"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public boolean Save(KhachHang kh) {
		String query = "INSERT INTO KhachHang (TenKH, DiaChi, SDT,NgayCapThe,Level)"
				+ "VALUES (N'"+ kh.getTenKh() +"', N'"+ kh.getDiaChi() +"', N'"+ kh.getSdt() +"',GetDate(),N'"+ kh.getLevel() +"')";
		try {
			if(DbOperations.setData(query, null) == true)
				return true;
			else {
				return false;
			}
		}
		catch (Exception ex) {
		}
		return false;
	}
	public boolean Update(KhachHang kh,String Name) {
		String query = "update KhachHang SET TenKH = N'"+ kh.getTenKh() +"', DiaChi = N'"+ kh.getDiaChi() +"',SDT = N'"+ kh.getSdt() +"' , Level = N'"+ kh.getLevel() +"'"
				+ "WHERE TenKH = N'"+ Name +"' ";
		try {
			if(DbOperations.setData(query, null) == true)
				return true;
			else {
				return false;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public static KhachHang FindbyName(String Name) {
		KhachHang a = new KhachHang();
		try {
			ResultSet rs = DbOperations.getData("select a.* from KhachHang a where a.tenKH = N'" + Name + "' ");
			while (rs.next()) {
				a.setIdkh(rs.getInt("IDKH"));
				a.setDiaChi(rs.getString("DiaChi"));
				a.setSdt(rs.getInt("SDT"));
				a.setTenKh(rs.getString("TenKH"));
				a.setNgayCapThe(rs.getDate("NgayCapThe"));
				a.setLevel(rs.getString("Level"));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	public static KhachHang FindbySDT(String Name) {
		KhachHang a = new KhachHang();
		try {
			ResultSet rs = DbOperations.getData("select a.* from KhachHang a where a.SDT = N'" + Name + "' ");
			while (rs.next()) {
				a.setIdkh(rs.getInt("IDKH"));
				a.setDiaChi(rs.getString("DiaChi"));
				a.setSdt(rs.getInt("SDT"));
				a.setTenKh(rs.getString("TenKH"));
				a.setNgayCapThe(rs.getDate("NgayCapThe"));
				a.setLevel(rs.getString("Level"));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	public boolean delete(String Name) {
		if(Name == "") {
			return false;
		}
		else {
			String query = "DELETE FROM KhachHang WHERE SDT = N'"+ Name +"'";
			DbOperations.setData(query, "Delete");
			return true;
		}
	}
	
	
	
	
}
