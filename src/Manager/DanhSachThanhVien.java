package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Process.Database;

public class DanhSachThanhVien {
	ArrayList<ThanhVien> ds;

	public DanhSachThanhVien() {
		ds = new ArrayList<ThanhVien>();
	}

	public ArrayList<ThanhVien> docTuBang() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select * from ThanhVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maTV = rs.getString(1).trim();
				String tenTV = rs.getString(2).trim();
				boolean gioitinh = rs.getBoolean(3);
				String sdt = rs.getString(4).trim();
				String diachi = rs.getString(5).trim();
				Date ngayhh = new Date(rs.getDate(6).getTime());
				add(maTV, tenTV, gioitinh, sdt, diachi, ngayhh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	
	public boolean add(String maTV,String hoTen,boolean gioitinh,String sdt,String dchi, Date ngayhh){
		for (ThanhVien thanhVien : ds) {
			int a = Integer.parseInt(maTV.replaceAll("[^0-9]+", ""));
			int b  = Integer.parseInt(thanhVien.getMaTV().replaceAll("[^0-9]+", ""));
			if(a==b) return false;
		}
		ds.add(new ThanhVien(maTV, hoTen, gioitinh, sdt, dchi, ngayhh));
		return true;
	}

	public boolean create(String maTV,String hoTen,boolean gioitinh,String sdt,String dchi, Date ngayhh) {
		int n = 0;
		if(add(maTV,hoTen,gioitinh,sdt,dchi, ngayhh)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("insert into ThanhVien values(?, ?, ?, ?, ?, ?)");
				stm.setString(1, maTV);
				stm.setString(2, hoTen);
				stm.setBoolean(3, gioitinh);
				stm.setString(4, sdt);
				stm.setString(5, dchi);
				stm.setDate(6, new java.sql.Date(ngayhh.getTime()));
				n = stm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean delete(String ma) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("delete from ThanhVien where maTV = ?");
			stm.setString(1, ma);
			n = stm.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		ds.remove(getTV(ma));
		return n > 0;
	}

	public boolean update(String maTV,String hoTen,boolean gioitinh,String sdt,String dchi, Date ngayhh) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("UPDATE ThanhVien SET HoTen = ?, Gioitinh = ?, SDT= ?, Diachi = ?, NgayHH = ? WHERE MaTV = ?");
			stm.setString(1, hoTen);
			stm.setBoolean(2, gioitinh);
			stm.setString(3, sdt);
			stm.setString(4, dchi);
			stm.setDate(5, new java.sql.Date(ngayhh.getTime()));
			stm.setString(6, maTV);
			n = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	private int getTV(String matv) {
		for (ThanhVien thanhVien : ds) {
			if(thanhVien.getMaTV().equals(matv))
				return ds.indexOf(thanhVien);
		}
		return -1;
	}
	
	public ThanhVien getTV(int index) {
		if(ds.size()>0)
			return ds.get(index);
		else return null;
	}
}
