package entities;
// Generated Apr 20, 2022, 11:27:46 PM by Hibernate Tools 4.3.5.Final

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * KhachHang generated by hbm2java
 */
@Entity
@Table(name = "KhachHang", schema = "dbo", catalog = "ElteeStore")
public class KhachHang implements java.io.Serializable {

	private Integer idkh;
	private String tenKh;
	private String diaChi;
	private Integer sdt;
	private Date ngayCapThe;
	private String level;
	private Set<HoaDon> hoaDons = new HashSet<HoaDon>(0);
	private Set<HoaDon> hoaDons_1 = new HashSet<HoaDon>(0);

	public KhachHang() {
	}

	public KhachHang(String tenKh) {
		this.tenKh = tenKh;
	}

	public KhachHang(String tenKh, String diaChi, Integer sdt, Date ngayCapThe, String level,
			Set<HoaDon> hoaDons, Set<HoaDon> hoaDons_1) {
		this.tenKh = tenKh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.ngayCapThe = ngayCapThe;
		this.level = level;
		this.hoaDons = hoaDons;
		this.hoaDons_1 = hoaDons_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IDKH", unique = true, nullable = false)
	public Integer getIdkh() {
		return this.idkh;
	}

	public void setIdkh(Integer idkh) {
		this.idkh = idkh;
	}

	@Column(name = "TenKH", nullable = false)
	public String getTenKh() {
		return this.tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	@Column(name = "DiaChi")
	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Column(name = "SDT")
	public Integer getSdt() {
		return this.sdt;
	}

	public void setSdt(Integer sdt) {
		this.sdt = sdt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NgayCapThe", length = 23)
	public Date getNgayCapThe() {
		return this.ngayCapThe;
	}

	public void setNgayCapThe(Date ngayCapThe) {
		this.ngayCapThe = ngayCapThe;
	}

	@Column(name = "Level")
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHang")
	public Set<HoaDon> getHoaDons() {
		return this.hoaDons;
	}

	public void setHoaDons(Set<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHang")
	public Set<HoaDon> getHoaDons_1() {
		return this.hoaDons_1;
	}

	public void setHoaDons_1(Set<HoaDon> hoaDons_1) {
		this.hoaDons_1 = hoaDons_1;
	}

}
