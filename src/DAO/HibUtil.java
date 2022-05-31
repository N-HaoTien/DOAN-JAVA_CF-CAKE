package DAO;

import java.text.Annotation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import entities.ChiTietHoaDon;
import entities.ChiTietHoaDonId;
import entities.HoaDon;
import entities.KhachHang;
import entities.LoaiSp;
import entities.NhanVien;
import entities.SanPham;

public class HibUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(SanPham.class);
			conf.addAnnotatedClass(LoaiSp.class);
			conf.addAnnotatedClass(HoaDon.class);
			conf.addAnnotatedClass(ChiTietHoaDon.class);
			conf.addAnnotatedClass(ChiTietHoaDonId.class);
			conf.addAnnotatedClass(NhanVien.class);
			conf.addAnnotatedClass(KhachHang.class);
			ServiceRegistry re = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(re);
		} catch (Throwable ex) {
			System.err.println("Loi " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
