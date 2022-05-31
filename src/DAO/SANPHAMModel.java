package DAO;


public class SANPHAMModel {
	private Integer idsp;
	private int IDloaiSp;
	private String tenSp;
	private int giaBan;
	public Integer getIdsp() {
		return idsp;
	}
	public void setIdsp(Integer idsp) {
		this.idsp = idsp;
	}
	public int getIDloaiSp() {
		return IDloaiSp;
	}
	public void setIDloaiSp(int iDloaiSp) {
		IDloaiSp = iDloaiSp;
	}
	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	public int getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
	}
	private String hinh;
}
