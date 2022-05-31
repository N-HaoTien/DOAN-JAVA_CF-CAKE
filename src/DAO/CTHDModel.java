package DAO;

import java.util.Date;

public class CTHDModel {
	
	public Integer IDHD;
	public String TenNV;
	public Date ThoiDiemLap;
	public Integer GiamGia;
	public Integer IDKH;
	public Integer getIDKH() {
		return IDKH;
	}

	public void setIDKH(Integer iDKH) {
		IDKH = iDKH;
	}

	public String getTenNV() {
		return TenNV;
	}

	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}

	public Date getThoiDiemLap() {
		return ThoiDiemLap;
	}

	public void setThoiDiemLap(Date thoiDiemLap) {
		ThoiDiemLap = thoiDiemLap;
	}

	public Integer getGiamGia() {
		return GiamGia;
	}

	public void setGiamGia(Integer giamGia) {
		GiamGia = giamGia;
	}

	public String getTenSP() {
		return TenSP;
	}

	public void setTenSP(String tenSP) {
		TenSP = tenSP;
	}

	public Integer getGiaBan() {
		return GiaBan;
	}

	public void setGiaBan(Integer giaBan) {
		GiaBan = giaBan;
	}

	public double getThanhTien() {
		return ThanhTien;
	}

	public void setThanhTien(double thanhTien) {
		ThanhTien = thanhTien;
	}

	public Integer IDSP;
	
	public Integer Soluong;
	
	public Integer getThang() {
		return Thang;
	}

	public void setThang(Integer thang) {
		Thang = thang;
	}

	public String TenSP;
	
	public Integer GiaBan;
	public Integer Thang;
	
	public double ThanhTien;
	
	public String Ghichu;

	public Integer getIDHD() {
		return IDHD;
	}

	public void setIDHD(Integer iDHD) {
		IDHD = iDHD;
	}

	public Integer getIDSP() {
		return IDSP;
	}

	public void setIDSP(Integer iDSP) {
		IDSP = iDSP;
	}

	public Integer getSoluong() {
		return Soluong;
	}

	public void setSoluong(Integer soluong) {
		Soluong = soluong;
	}

	public String getGhichu() {
		return Ghichu;
	}

	public void setGhichu(String ghichu) {
		Ghichu = ghichu;
	}
}
