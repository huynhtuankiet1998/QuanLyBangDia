package Process;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static Connection con = null;
	private static Database instance = new Database();

	public static void connect(String host) {
		String url = "jdbc:sqlserver://localhost:" + host + ";databasename=QLCD";
		String user = "sa";
		String password = "sapassword";

		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	public Connection getConnection() {
		return con;
	}

	public static Database getInstance() {
		return instance;
	}
}