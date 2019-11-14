package Manager;

import java.util.Date;

public class ThanhVien {
	private String maTV;
	private String hoTen;
	private boolean gioitinh;
	private String sdt;
	private String dchi; 
	private Date ngayhh;
	
	public ThanhVien(String maTV, String hoTen, boolean gioitinh, String sdt, String dchi, Date ngayhh) {
		super();
		this.maTV = maTV;
		this.hoTen = hoTen;
		this.gioitinh = gioitinh;
		this.sdt = sdt;
		this.dchi = dchi;
		this.ngayhh = ngayhh;
	}

	public ThanhVien() {
		super();
	}

	public String getMaTV() {
		return maTV;
	}

	public void setMaTV(String maTV) {
		this.maTV = maTV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDchi() {
		return dchi;
	}

	public void setDchi(String dchi) {
		this.dchi = dchi;
	}

	public Date getNgayhh() {
		return ngayhh;
	}

	public void setNgayhh(Date ngayhh) {
		this.ngayhh = ngayhh;
	}
}
