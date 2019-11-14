package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Process.Database;

public class DanhSachPhieuThue {
	ArrayList<PhieuThue> ds;

	public DanhSachPhieuThue() {
		ds = new ArrayList<PhieuThue>();
	}

	public ArrayList<PhieuThue> docTuBang() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select * from PhieuThue";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String soPhieu = rs.getString(1).trim();
				Date ngaythue = new Date(rs.getDate(2).getTime());
				String maTV = rs.getString(3).trim();
				int soLuong = rs.getInt(2);
				add(soPhieu, maTV, ngaythue,soLuong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	
	private boolean add(String soPhieu, String maTV, Date ngaythue, int soLuong){
		for (PhieuThue phieuThue : ds) {
			int a = Integer.parseInt(soPhieu.replaceAll("[^0-9]+", ""));
			int b  = Integer.parseInt(phieuThue.getSoPhieu().replaceAll("[^0-9]+", ""));
			if(a==b) return false;
		}
		ds.add(new PhieuThue(soPhieu, maTV, ngaythue,soLuong));
		return true;
	}

	public boolean create(String soPhieu, String maTV, Date ngaythue,int soLuong) {
		int n = 0;
		if(add(soPhieu, maTV, ngaythue,soLuong)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("insert PhieuThue values(?, ?, ?)");
				stm.setString(1, soPhieu);
				stm.setDate(2, new java.sql.Date(ngaythue.getTime()));
				stm.setString(3, maTV);
				n = stm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean delete(String sophieu) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("delete from PhieuThue where SoPhieu = ?");
			stm.setString(1, sophieu);
			n = stm.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		ds.remove(getPT(sophieu));
		return n > 0;
	}

	public boolean update(String sophieu, String maTV, Date ngaythue) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("UPDATE PhieuThue SET Ngaythue = ?, MaTV = ? WHERE SoPhieu = ?");
			stm.setDate(1, new java.sql.Date(ngaythue.getTime()));
			stm.setString(2, maTV);
			stm.setString(3, sophieu);
			n = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	private int getPT(String sophieu) {
		for (PhieuThue phieuThue : ds) {
			if(phieuThue.getSoPhieu().equals(sophieu))
				return ds.indexOf(phieuThue);
		}
		return -1;
	}
	
	public PhieuThue getPT(int index) {
		if(ds.size()>0)
			return ds.get(index);
		else return null;
	}
}
