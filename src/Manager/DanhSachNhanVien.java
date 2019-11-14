package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Process.Database;

public class DanhSachNhanVien {
	ArrayList<NhanVien> ds;

	public DanhSachNhanVien() {
		ds = new ArrayList<NhanVien>();
	}

	public ArrayList<NhanVien> docTuBang() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select * from NhanVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1).trim();
				String tenNV = rs.getString(2).trim();
				String sdt = rs.getString(3).trim();
				String mota = rs.getString(4).trim();
				add(maNV, tenNV, sdt, mota);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean add(String maNV, String tenNV, String sdt, String mota) {
		for (NhanVien nvx : ds) {
			int a=0,b = 0;
			try {
				 a = Integer.parseInt(maNV.replaceAll("[^0-9]+", ""));
				 b = Integer.parseInt(nvx.getMaNV().replaceAll("[^0-9]+", ""));
			} catch (NumberFormatException e) {}
			if(a==b) return false;
		}
		ds.add(new NhanVien(maNV, tenNV, sdt, mota));
		return true;
	}

	public boolean create(String maNV, String tenNV, String sdt, String mota, String password) {
		int n = 0;
		if (add(maNV, tenNV, sdt, mota)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stm = null;
			PreparedStatement stm1 = null;
			try {
				stm = con.prepareStatement("insert into NhanVien values(?, ?, ?, ?)");
				stm.setString(1, maNV);
				stm.setString(2, tenNV);
				stm.setString(3, sdt);
				stm.setString(4, mota);
				n = stm.executeUpdate();
				stm1 = con.prepareStatement("insert into Account values(?, ?)");
				stm1.setString(1, maNV);
				stm1.setString(2, password);
				stm1.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean delete(String ma) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		PreparedStatement stm1 = null;
		int n = 0;
		try {
			stm = con.prepareStatement("delete from NhanVien where maNV = ?");
			stm1 = con.prepareStatement("delete from Account where maNV = ?");
			stm1.setString(1, ma);
			stm.setString(1, ma);
			stm1.executeUpdate();
			n = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		ds.remove(getNV(ma));
		return n > 0;
	}

	public boolean update(String maNV, String tenNV, String sdt, String mota) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update NhanVien set TenNV = ? , SDT = ?, MoTa = ? where MaNV = ?");
			stm.setString(1, tenNV);
			stm.setString(2, sdt);
			stm.setString(3, mota);
			stm.setString(4, maNV);
			n = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	private int getNV(String manv) {
		for (NhanVien nhanVien : ds)
			if (nhanVien.getMaNV().equals(manv))
				return ds.indexOf(nhanVien);
		return -1;
	}

	public NhanVien getNV(int index) {
		if (ds.size() > 0)
			return ds.get(index);
		else
			return null;
	}
}