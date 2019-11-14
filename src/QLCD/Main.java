package QLCD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Process.Database;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;;

public class Main {
	private static boolean checkAdmin() {
		Connection con = Database.getInstance().getConnection();
		String getUser = "SELECT MaNV FROM dbo.NhanVien";
		
		try {
			
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(getUser);
			
			if(rs.next())
				do {
					if(rs.getString(1).trim().equals("Admin"))
						return false;
				}
				while(rs.next());
			else
			if (JOptionPane.showConfirmDialog(null, "Chưa có thông tin người quản trị!!\nBạn có muốn đăng kí không",
						"Register??", JOptionPane.YES_NO_OPTION) == 0)
					return true;
			con.close();
			
		} catch (Exception e) {}
		return false;
	}

	public static void main(String[] args) {
		
		System.setProperty("file.encoding", "UTF-8");
		try {
			
			UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Database.getInstance().connect("1433");
		
		 if(checkAdmin())
			 new RegisterForm().setVisible(true);
		 else
			 new LoginForm().setVisible(true);
	}
}