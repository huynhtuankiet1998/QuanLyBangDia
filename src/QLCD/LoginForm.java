package QLCD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Process.Database;
import Process.encrypt;

public class LoginForm extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblUser;
	private JLabel lblPass;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnLogin;
	private JButton btnExit;
	private ImageIcon icon;
	private JCheckBox check;

	public LoginForm() {
		super("Login");
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;

		setBounds(width/2-262, height/2-123, 520, 247);
		setContentPane(new JLabel(new ImageIcon("Image/Background_login.jpg")));
		setLayout(new BorderLayout());
		
		JPanel pTitle, pImage, pCenter, pUser, pPass, sPanel;
		pTitle = new JPanel();
		JLabel lblTitle = new JLabel(new ImageIcon("Image/login_logo.png"));

		lblTitle.setFont(new Font("Time new Roman", Font.BOLD, 25));
		lblTitle.setForeground(Color.orange);
		pTitle.add(lblTitle);
		add(pTitle, BorderLayout.NORTH);
		icon = new ImageIcon(new ImageIcon("Image/login_label.png").getImage());
		pImage = new JPanel();
		pImage.add(new JLabel(icon));
		add(pImage, BorderLayout.WEST);
		
		pCenter = new JPanel();
		FlowLayout layout = (FlowLayout) pCenter.getLayout();
		layout.setAlignment(FlowLayout.LEFT);
		pCenter.add(pUser = new JPanel());
		pUser.add(lblUser = new JLabel("Tên đăng nhập: "));
		pUser.add(txtUser = new JTextField(28));
		pUser.add(new JLabel(new ImageIcon("Image/users.png")));
		pCenter.add(pPass = new JPanel());
		pPass.add(lblPass = new JLabel("Mật khẩu: "));
		pPass.add(txtPass = new JPasswordField(28));
		pPass.add(new JLabel(new ImageIcon("Image/Password.png")));
		pCenter.add(check = new JCheckBox("Nhớ mật khẩu"));
		pCenter.add(sPanel = new JPanel());
		sPanel.add(btnLogin = new JButton("Đăng nhập"));
		sPanel.add(btnExit = new JButton("Thoát"));
		btnLogin.setIcon(new ImageIcon("Image/btnlogin.png"));
		btnExit.setIcon(new ImageIcon("Image/btnexit.png"));
		btnLogin.setPreferredSize(new Dimension(150, 30));
		btnExit.setPreferredSize(btnLogin.getPreferredSize());
		pTitle.setOpaque(false);
		pImage.setOpaque(false);
		pCenter.setOpaque(false);
		pUser.setOpaque(false);
		pPass.setOpaque(false);
		check.setOpaque(false);
		sPanel.setOpaque(false);
		lblUser.setForeground(Color.red);
		lblPass.setForeground(Color.red);
		check.setForeground(Color.red);
		lblUser.setFont(new Font("Time new Roman", Font.BOLD, 12));
		lblPass.setFont(new Font("Time new Roman", Font.BOLD, 12));
		check.setFont(new Font("Time new Roman", Font.BOLD, 12));
		lblPass.setPreferredSize(lblUser.getPreferredSize());
		txtPass.addActionListener(this);
		btnLogin.addActionListener(this);
		btnExit.addActionListener(this);
		add(pCenter, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLogin) || e.getSource().equals(txtPass)) {
			String user = txtUser.getText();
			if(user.trim().length()==0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập UserName", "Error", JOptionPane.ERROR_MESSAGE);
				txtUser.requestFocus();
				return;
			} else if(String.copyValueOf(txtPass.getPassword()).trim().length()==0){
				JOptionPane.showMessageDialog(this, "Chưa nhập password", "Error", JOptionPane.ERROR_MESSAGE);
				txtPass.requestFocus();
				return;
			}
			int val = validAccount(user, encrypt.md5(String.copyValueOf(txtPass.getPassword()).trim()));
			if (val != 0) {
				new MainForm(val).setVisible(true);
				this.dispose();
			}
		} else
			this.dispose();
	}

	private int validAccount(String user, String pass) {
		Connection con = Database.getInstance().getConnection();
		try {
			String getUser = "SELECT MaNV FROM dbo.NhanVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(getUser);
			while(rs.next()) {
				if(user.equals(rs.getString(1).trim())) {
					String getpass = "SELECT * FROM Account WHERE MaNV = '"+user+"'";
					Statement stm1 = con.createStatement();
					ResultSet rs1 = stm.executeQuery(getpass);
					rs1.next();
					if(pass.equals(rs1.getString(2).trim())) {
						JOptionPane.showMessageDialog(this, "Đăng Nhập thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
						if(user.equals("Admin"))
							return 2;
						else
							return 1;
					}
				}
			}
		} catch (SQLException e) {}
		JOptionPane.showMessageDialog(this, "Đăng Nhập Thất Bại!", "Fail", JOptionPane.ERROR_MESSAGE);
		txtPass.requestFocus();
		return 0;
	}
}
