package Manager;

public class NhanVien {
	private String MaNV;
	private String TenNV;
	private String sdt;
	private String Mota;

	public NhanVien() {
		super();
	}

	public NhanVien(String maNV, String tenNV, String sdt, String mota) {
		super();
		setMaNV(maNV);
		setTenNV(tenNV);
		setSdt(sdt);
		setMota(mota);
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		if (maNV == null) {
			this.MaNV = "";
		} else {
			MaNV = maNV;
		}
	}

	public String getMota() {
		return Mota;
	}

	public void setMota(String mota) {
		if (mota == null) {
			this.Mota = "";
		} else {
			Mota = mota;
		}
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		if (sdt == null) {
			this.sdt = "";
		} else {
			this.sdt = sdt;
		}
	}

	public String getTenNV() {
		return TenNV;
	}

	public void setTenNV(String tenNV) {
		if (tenNV == null) {
			this.TenNV = "";
		} else {
			TenNV = tenNV;
		}
	}
}