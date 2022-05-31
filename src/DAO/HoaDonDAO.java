package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.LoaiSp;
import entities.SanPham;

public class HoaDonDAO {
	public void Save(Integer idNV,Integer IDKH) {
		try {
			if(IDKH == null) {
				String query = "INSERT INTO HoaDon([IDNV] ,[TongTien] ,[ThoiDiemLap], [GiamGia]) VALUES ('" + idNV
						+ "',0,GETDATE(),0)";
				// java.time.LocalDate.now();
				DbOperations.setData(query, "ThemHD");
			}
			else {
				String query = "INSERT INTO HoaDon([IDNV],IDKH ,[TongTien] ,[ThoiDiemLap], [GiamGia]) VALUES ('" + idNV
						+ "','"+ IDKH +"',0,GETDATE(),0)";
				// java.time.LocalDate.now();
				DbOperations.setData(query, "ThemHD");
			}
			
		}
		catch (Exception ex) {
			// TODO: handle exception
		}
	}

	public static HoaDon GetBillMaxID() {
		HoaDon a = new HoaDon();
		try {
			ResultSet rs = DbOperations.getData("select Max(c.IDHD) as 'IDHD' from HoaDon c");
			while (rs.next()) {
				a.setIdhd(rs.getInt("IDHD"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}

	public static ArrayList<CTHDModel> getAll(int idhd) {
		ArrayList<CTHDModel> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData(
					"select *,a.GiaBan* b.SoLuong as 'ThanhTien' from SanPham a , ChiTietHoaDon b where a.IDSP = b.IDSP and b.IDHD = '"
							+ idhd + "'");
			while (rs.next()) {
				CTHDModel a = new CTHDModel();
				a.setTenSP(rs.getString("TenSP"));
				a.setSoluong(rs.getInt("SoLuong"));
				a.setGiaBan(rs.getInt("GiaBan"));
				a.setThanhTien(rs.getDouble("ThanhTien"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public static ArrayList<CTHDModel> getAllHD() {
		ArrayList<CTHDModel> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData(
					"select a.TongTien,b.TenNV,a.IDHD,a.GiamGia,FORMAT(CAST(a.ThoiDiemLap as datetime),'dd/MM/yyyy') as 'ThoiDiemLap' from HoaDon a, NhanVien b where a.idnv = b.idnv");
			while (rs.next()) {
				CTHDModel a = new CTHDModel();
				a.setIDHD(rs.getInt("IDHD"));
				a.setGhichu(rs.getString("ThoiDiemLap"));
				a.setGiamGia(rs.getInt("GiamGia"));
				a.setTenNV(rs.getString("TenNV"));
				a.setThanhTien(rs.getDouble("TongTien"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}

	public static ArrayList<CTHDModel> getAllHDbyTime(String Start, String End) {
		ArrayList<CTHDModel> sp = new ArrayList<>();
		try {
			if (Start.isEmpty() && End.isEmpty()) {
				ResultSet rs = DbOperations.getData(
						"select a.TongTien,a.[IDKH],b.TenNV,a.IDHD,a.GiamGia,FORMAT(CAST(a.ThoiDiemLap as datetime),'dd/MM/yyyy') as 'ThoiDiemLap' from HoaDon a, NhanVien b where a.idnv = b.idnv");
				while (rs.next()) {
					CTHDModel a = new CTHDModel();
					a.setIDHD(rs.getInt("IDHD"));
					a.setGhichu(rs.getString("ThoiDiemLap"));
					a.setGiamGia(rs.getInt("GiamGia"));
					a.setIDKH(rs.getInt("IDKH"));
					a.setTenNV(rs.getString("TenNV"));
					a.setThanhTien(rs.getDouble("TongTien"));
					sp.add(a);
				}
			} else {
				ResultSet rs = DbOperations.getData(
						"select a.TongTien,b.TenNV,a.IDHD,a.GiamGia,FORMAT(CAST(a.ThoiDiemLap as datetime),'dd/MM/yyyy') as 'ThoiDiemLap' from HoaDon a, NhanVien b where a.idnv = b.idnv and CONVERT(datetime, a.ThoiDiemLap, 105) between CONVERT(datetime, '"
								+ Start + "', 105) and CONVERT(datetime, '" + End + "', 105)");
				while (rs.next()) {
					CTHDModel a = new CTHDModel();
					a.setIDHD(rs.getInt("IDHD"));
					a.setGhichu(rs.getString("ThoiDiemLap"));
					a.setGiamGia(rs.getInt("GiamGia"));
					a.setTenNV(rs.getString("TenNV"));
					a.setThanhTien(rs.getDouble("TongTien"));
					sp.add(a);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public double GetMoneyofDay() {
		double Money = 0;
		try {
			ResultSet rs = DbOperations.getData("select SUM(a.TongTien) as N'TongTien' from HoaDon a where DATEPART(day, a.ThoiDiemLap) = DATEPART(day, GETDATE())");
			while (rs.next()) {
				Money = rs.getDouble("TongTien");
			}
		}
		catch (Exception ex) {
			// TODO: handle exception
		}
		return Money;
	}
	public static CTHDModel findCTHD (Integer idhd , Integer idsp) {
		String query = "select a.* from ChiTietHoaDon a join HoaDon b on a.IDHD = b.IDHD where a.IDHD ='"+ idhd +"' and a.IDSP ='"+ idsp +"'";
		try {
			ResultSet rs = DbOperations.getData("select a.* from ChiTietHoaDon a where a.IDHD ='"+ idhd +"' and a.IDSP ='"+ idsp +"'");
				while(rs.next()) {
					CTHDModel a = new CTHDModel();
					a.setSoluong(rs.getInt("SoLuong"));
					a.setIDHD(rs.getInt("IDHD"));
					a.setIDSP(rs.getInt("IDSP"));
					return a;
				}
				return null;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public void SaveCTHD(CTHDModel ct) {

			String queryfoCTHD = "INSERT INTO ChiTietHoaDon([IDSP],IDHD,SoLuong) VALUES ('" + ct.getIDSP() + "','"
					+ ct.getIDHD() + "','" + ct.getSoluong() + "') ";
			DbOperations.setData(queryfoCTHD, null);
	}

	public static ArrayList<CTHDModel> getCTHDbyID(int idhd) {
		ArrayList<CTHDModel> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData(
					"select *,a.GiaBan* b.SoLuong as 'ThanhTien' from SanPham a , ChiTietHoaDon b where a.IDSP = b.IDSP and b.IDHD = '"
							+ idhd + "'");
			while (rs.next()) {
				CTHDModel a = new CTHDModel();
				a.setTenSP(rs.getString("TenSP"));
				a.setSoluong(rs.getInt("SoLuong"));
				a.setGiaBan(rs.getInt("GiaBan"));
				a.setThanhTien(rs.getDouble("ThanhTien"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}

	public void UpdateCTHD(CTHDModel ct) {

		String queryfoCTHD = "UPDATE ChiTietHoaDon set SoLuong = '" + ct.getSoluong() + "'  where IDHD = '"
				+ ct.getIDHD() + "'and IDSP = '" + ct.getIDSP() + "' ";
		DbOperations.setData(queryfoCTHD, null);
	}

	public void UpdateHD(CTHDModel hd) {
		if(hd.getIDKH() == null) {
			String query = "UPDATE HoaDon SET TongTien = '" + hd.getThanhTien() + "',GiamGia = '"+ hd.getGiamGia() +"' WHERE IDHD = '" + hd.getIDHD() + "'";
			DbOperations.setData(query, "Update");
		}
		else {
			String query = "UPDATE HoaDon SET TongTien = '" + hd.getThanhTien() + "',GiamGia = '"+ hd.getGiamGia() +"',IDKH = '"+ hd.getIDKH() +"' WHERE IDHD = '" + hd.getIDHD() + "'";
			DbOperations.setData(query, "Update");
		}
		
	}

	public void Delete(CTHDModel sp) {
		String query = "DELETE FROM ChiTietHoaDon WHERE IDSP = '" + sp.getIDSP() + "'";
		DbOperations.setData(query, "Delete");
	}

	public ArrayList<CTHDModel> ThongKeTien() {
		ArrayList<CTHDModel> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData(
					"select MONTH(a.ThoiDiemLap) as'Thang' , Sum(a.TongTien) as 'ThuNhap' from HoaDon a Group by MONTH(a.ThoiDiemLap)");
			while (rs.next()) {
				CTHDModel a = new CTHDModel();
				a.setThang(rs.getInt("Thang"));
				a.setThanhTien(rs.getDouble("ThuNhap"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}

	public ArrayList<CTHDModel> ThongKeSL() {
		ArrayList<CTHDModel> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData(
					"select b.TenSP as N'TenSP',Sum(a.SoLuong) as 'SOLUONG' from ChiTiethoaDon a , SanPham b where a.IDSP = b.IDSP group by b.TenSP");
			while (rs.next()) {
				CTHDModel a = new CTHDModel();
				a.setTenSP(rs.getString("TenSP"));
				a.setSoluong(rs.getInt("SOLUONG"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}

}
