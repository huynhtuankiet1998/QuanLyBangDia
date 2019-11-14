package Manager;

import java.util.Date;

public class PhieuThue extends CD {
	private String soPhieu;
	private String maTv;
	private Date ngaythue;
	protected int soLuong;
	private String maCD;
	private String songaythue;
	private int Tongtien;

	public PhieuThue(String soPhieu, String maTv, Date ngaythue, int soLuong) {
		super();
		this.soPhieu = soPhieu;
		this.maTv = maTv;
		this.ngaythue = ngaythue;
		this.soLuong = soLuong;
	}

	public String getSoPhieu() {
		return soPhieu;
	}
	public void setSoPhieu(String soPhieu) {
		this.soPhieu = soPhieu;
	}
	public String getMaTv() {
		return maTv;
	}
	public void setMaTv(String maTv) {
		this.maTv = maTv;
	}
	public Date getNgaythue() {
		return ngaythue;
	}
	public void setNgaythue(Date ngaythue) {
		this.ngaythue = ngaythue;
	}
	
	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	/*public int TinhTien(CD donGia)
	{
		
		return CD.this.donGia* soLuong;
		
	}*/
	
}
