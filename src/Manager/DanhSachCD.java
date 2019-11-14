package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Process.Database;

public class DanhSachCD {
	ArrayList<CD> ds;
	CD cd;

	public DanhSachCD() {
		ds = new ArrayList<CD>();
		cd = new CD();
	}
	
	public ArrayList<CD> docTuBang() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select * from CD";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maCD = rs.getString(1).trim();
				String tenCD = rs.getString(2).trim();
				String theloai = rs.getString(3).trim();
				Boolean tinhtrang = rs.getBoolean(4);
				String hangSX = rs.getString(5).trim();
				String ghichu = rs.getString(6).trim();
				int donGia = rs.getInt(7);
				add(maCD, tenCD, theloai, tinhtrang, hangSX, ghichu, donGia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	private boolean add(String maCD, String tenCD, String theloai, boolean tinhtrang, String hangSX, String ghichu,int donGia) {
		for (CD cdx : ds) {
			int a = Integer.parseInt(maCD.replaceAll("[^0-9]+", ""));
			int b  = Integer.parseInt(cdx.getMaCD().replaceAll("[^0-9]+", ""));
			if(a==b) return false;
		}
		ds.add(new CD(maCD, tenCD, theloai, tinhtrang, hangSX, ghichu, donGia));
		return true;
	}
	public boolean create(String maCD, String tenCD, String theloai, boolean tinhtrang, String hangSX, String ghichu,
			int donGia) {
		int n = 0;
		if(add(maCD, tenCD, theloai, tinhtrang, hangSX, ghichu, donGia)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("insert into CD values(?, ?, ?, ?, ?, ?, ?)");
				stm.setString(1, maCD);
				stm.setString(2, tenCD);
				stm.setString(3, theloai);
				stm.setBoolean(4, tinhtrang);
				stm.setString(5, hangSX);
				stm.setString(6, ghichu);
				stm.setInt(7, donGia);
				n = stm.executeUpdate();
			} catch (Exception e) {}
		}
		return n > 0;
	}

	public boolean delete(String ma) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("delete from CD where maCD = ?");
			stm.setString(1, ma);
			n = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return n>0;
		}
		ds.remove(getCD(ma));
		return n > 0;
	}

	public boolean update(String maCD, String tenCD, String theloai, boolean tinhtrang, String hangSX, String ghichu,
			int donGia) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update CD set tenCD = ? , theloai = ?, Trangthai = ?, hangSX = ?, ghichu = ?, DonGia = ? where maCD = ?");
			stm.setString(1, tenCD);
			stm.setString(2, theloai);
			stm.setBoolean(3, tinhtrang);
			stm.setString(4, hangSX);
			stm.setString(5, ghichu);
			stm.setInt(6, donGia);
			stm.setString(7, maCD);
			n = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return n>0;
		}
		ds.set(getCD(maCD), new CD(maCD, tenCD, theloai, tinhtrang, hangSX, ghichu, donGia));
		return n > 0;
	}
	
	private int getCD(String macd) {
		for (CD cd : ds) {
			if(cd.getMaCD().equals(macd))
				return ds.indexOf(cd); 
		}
		return -1;
	}
	
	public CD getCD(int index) {
		if(ds.size()>0)
			return ds.get(index);
		return null;
	}
}