package entities;
// Generated Apr 20, 2022, 11:27:46 PM by Hibernate Tools 4.3.5.Final

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ChiTietHoaDon generated by hbm2java
 */
@Entity
@Table(name = "ChiTietHoaDon", schema = "dbo", catalog = "ElteeStore")
public class ChiTietHoaDon implements java.io.Serializable {

	private ChiTietHoaDonId id;

	private HoaDon hoaDon;
	private SanPham sanPham;
	private int soLuong;
	private String ghiChu;

	public ChiTietHoaDon() {
	}

	public ChiTietHoaDon(ChiTietHoaDonId id, HoaDon hoaDon, SanPham sanPham, int soLuong) {
		this.id = id;
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}

	public ChiTietHoaDon(ChiTietHoaDonId id, HoaDon hoaDon, SanPham sanPham, int soLuong, String ghiChu) {
		this.id = id;
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "idsp", column = @Column(name = "IDSP", nullable = false)),
			@AttributeOverride(name = "idhd", column = @Column(name = "IDHD", nullable = false)) })
	public ChiTietHoaDonId getId() {
		return this.id;
	}

	public void setId(ChiTietHoaDonId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDHD", nullable = false, insertable = false, updatable = false)
	public HoaDon getHoaDon() {
		return this.hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSP", nullable = false, insertable = false, updatable = false)
	public SanPham getSanPham() {
		return this.sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	@Column(name = "SoLuong", nullable = false)
	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Column(name = "GhiChu")
	public String getGhiChu() {
		return this.ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

}
