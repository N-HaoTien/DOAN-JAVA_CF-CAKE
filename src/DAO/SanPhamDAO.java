package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.LoaiSp;
import entities.SanPham;

public class SanPhamDAO {
	private final SessionFactory sf = HibUtil.getSessionFactory();

	public List<SanPham> findAll() {
		try {

			sf.getCurrentSession().beginTransaction();
			List<SanPham> a = sf.getCurrentSession().createCriteria(SanPham.class).list();
			sf.getCurrentSession().getTransaction().commit();
			return a;
		} catch (Exception ex) {
			return null;
		}
	}

	public SanPham find(Integer id) {
		sf.getCurrentSession().beginTransaction();
		SanPham loaisp = (SanPham) sf.getCurrentSession().get(SanPham.class, id);
		sf.getCurrentSession().getTransaction().commit();
		return loaisp;
	}

	public SanPham findbyName(String id) {
		sf.getCurrentSession().beginTransaction();
		SanPham loaisp = (SanPham) sf.getCurrentSession().get(SanPham.class, id);
		sf.getCurrentSession().getTransaction().commit();
		return loaisp;
	}

	public boolean delete(SanPham sp) {
		String query = "delete from SanPham where IDSP=N'" + sp.getIdsp() + "'";
		if(DbOperations.setData(query, null) == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean SaveorUpdate(SanPham nv) {
		try {
			sf.getCurrentSession().beginTransaction();
			sf.getCurrentSession().saveOrUpdate(nv);
			sf.getCurrentSession().getTransaction().commit();

			return true;
		} catch (Exception ex) {
			sf.getCurrentSession().getTransaction().rollback();
			return false;
		}
	}
	
	public void Save(SANPHAMModel sp) {
		String query = "INSERT INTO table_name (column1, column2, column3, ...)"
				+ "VALUES (value1, value2, value3, ...)";
		DbOperations.setData(query, "Update");
	}
	
	public boolean Update(SANPHAMModel sp) {
		String query = "update SanPham set TenSP = N'" + sp.getTenSp() + "',Hinh = '"+ sp.getHinh() +"' , GiaBan = '" + sp.getGiaBan() 
				+ "',IDLoaiSP = '"+ sp.getIDloaiSp() +"' where  IDSP = '" + sp.getIdsp() + "'";
		if(DbOperations.setData(query, "Update") == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public static ArrayList<SanPham> getAllbyNameCategory(String Name) {
		ArrayList<SanPham> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations
					.getData("select a.* from SanPham a, LoaiSP b where a.IDLoaiSP = b.IDLoaiSP and b.TenLoaiSP = N'"
							+ Name + "' ");
			while (rs.next()) {
				SanPham a = new SanPham();
				a.setTenSp(rs.getString("TenSP"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public static ArrayList<SanPham> getAllbyNameCategory(Integer ID) {
		ArrayList<SanPham> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations
					.getData("select a.* from SanPham a, LoaiSP b where a.IDLoaiSP = b.IDLoaiSP where a.IDSP = '"+ ID +"' ");
			while (rs.next()) {
				SanPham a = new SanPham();
				a.setTenSp(rs.getString("TenSP"));
				a.setGiaBan(rs.getInt("GiaBan"));
				a.setHinh(rs.getString("Hinh"));
				sp.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public static ArrayList<SanPham> getAllbyNameProduct(String Name, String NameCategory) {
		ArrayList<SanPham> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations
					.getData("select a.* from SanPham a, LoaiSP b where  a.IDLoaiSP = b.IDLoaiSP and a.TenSP like N'%"
							+ Name + "%' and b.TenLoaiSP = N'" + NameCategory + "' ");
			while (rs.next()) {
				SanPham a = new SanPham();
				a.setTenSp(rs.getString("TenSP"));
				sp.add(a);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}

	public static SanPham getSanPhamByName(String Name) {
		SanPham a = new SanPham();
		try {
			ResultSet rs = DbOperations.getData("select a.* from SanPham a where a.TenSP = N'" + Name + "' ");
			while (rs.next()) {
				a.setIdsp(rs.getInt("IDSP"));
				a.setTenSp(rs.getString("TenSP"));
				a.setGiaBan(rs.getInt("GiaBan"));
				a.setHinh(rs.getString("Hinh"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
}
