package entities;
// Generated Apr 20, 2022, 11:27:46 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ChiTietHoaDonId generated by hbm2java
 */
@Embeddable
public class ChiTietHoaDonId implements java.io.Serializable {

	private int idsp;
	private int idhd;

	public ChiTietHoaDonId() {
	}

	public ChiTietHoaDonId(int idsp, int idhd) {
		this.idsp = idsp;
		this.idhd = idhd;
	}

	@Column(name = "IDSP", nullable = false)
	public int getIdsp() {
		return this.idsp;
	}

	public void setIdsp(int idsp) {
		this.idsp = idsp;
	}

	@Column(name = "IDHD", nullable = false)
	public int getIdhd() {
		return this.idhd;
	}

	public void setIdhd(int idhd) {
		this.idhd = idhd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChiTietHoaDonId))
			return false;
		ChiTietHoaDonId castOther = (ChiTietHoaDonId) other;

		return (this.getIdsp() == castOther.getIdsp()) && (this.getIdhd() == castOther.getIdhd());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdsp();
		result = 37 * result + this.getIdhd();
		return result;
	}

}
