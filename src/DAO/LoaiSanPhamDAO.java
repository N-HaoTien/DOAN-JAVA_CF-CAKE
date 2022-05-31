package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.LoaiSp;
import entities.SanPham;

public class LoaiSanPhamDAO {

	private final SessionFactory sf = HibUtil.getSessionFactory();
	public List<LoaiSp> findAll() {
		try {
			sf.getCurrentSession().beginTransaction();
			List<LoaiSp> a = sf.getCurrentSession().createCriteria(LoaiSp.class).list();
			sf.getCurrentSession().getTransaction().commit();
			return a;
		} catch (Exception ex) {
			sf.getCurrentSession().getTransaction().rollback();
			return null;
		}
	}
	public LoaiSp getById (Integer id) {
		try {
			sf.getCurrentSession().beginTransaction();
			LoaiSp loaisp = (LoaiSp)sf.getCurrentSession().get(LoaiSp.class,id);
			sf.getCurrentSession().getTransaction().commit();
			return loaisp;	
		}
		catch (Exception e) {
			return null;
		}

	}

	public LoaiSp find(String tenSP) {
		sf.getCurrentSession().beginTransaction();
		LoaiSp loaisp = (LoaiSp)sf.getCurrentSession().get(LoaiSp.class,tenSP);
		sf.getCurrentSession().getTransaction().commit();
		return loaisp;	
	}
	public static boolean delete(String TenLoaiSP) {
		String query = "delete from LoaiSp where TenLoaiSP=N'"+TenLoaiSP+"'";
		if(DbOperations.setData(query, "Delete") == true) {
			return true;
		}
		return false;
	}
	public boolean Save(LoaiSp nv) {
		try {
			sf.getCurrentSession().beginTransaction();
			sf.getCurrentSession().save(nv);
			sf.getCurrentSession().getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	public static void Update(LoaiSp sp) {
		
		String query = "update LoaiSp set TenLoaiSP = N'"+sp.getTenLoaiSp()+"' where IDLoaiSP = '"+sp.getIdloaiSp()+"'" ;
		DbOperations.setData(query, "Update");
	}
	
	public static  ArrayList<LoaiSp> getAllCategory(){
		ArrayList<LoaiSp> sp = new ArrayList<>();
		try {
			ResultSet rs = DbOperations.getData("select * from LoaiSP ");
			while(rs.next()) {
				LoaiSp a = new LoaiSp();
				a.setIdloaiSp(rs.getInt("IDLoaiSP"));
				a.setTenLoaiSp(rs.getString("TenLoaiSP"));
				sp.add(a);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	public static  LoaiSp getAllCategory(String Name){
		LoaiSp sp = new LoaiSp();
		try {
			ResultSet rs = DbOperations.getData("select * from LoaiSP where TenLoaiSp = N'"+ Name +"'");
			while(rs.next()) {
				sp.setIdloaiSp(rs.getInt("IDLoaiSP"));
				sp.setTenLoaiSp(rs.getString("TenLoaiSP"));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return sp;
	}
	//ok cho chut
	 
	
}
