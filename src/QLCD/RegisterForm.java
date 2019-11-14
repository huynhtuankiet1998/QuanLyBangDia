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
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Manager.DanhSachNhanVien;
import Manager.NhanVien;
import Process.Database;
import Process.encrypt;

public class RegisterForm extends JFrame implements ActionListener {
	private JButton btnReg;
	private JButton btnExit;
	private inputNV input;
	private DanhSachNhanVien dsnv;
	private JLabel lblMa;
	private JTextField txtMa;
	private JLabel lblTen;
	private JTextField txtTen;
	private JLabel lblsdt;
	private JTextField txtsdt;
	private JLabel lblMoTa;
	private JTextField txtMota;
	private JLabel lblpass;
	private JPasswordField txtpass;
	private JLabel lblrepass;
	private JPasswordField txtrepass;

	public RegisterForm() {
		super("Register");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setForeground(Color.BLUE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;

		setBounds(width / 2 - 352, height / 2 - 200, 650, 300);
		setContentPane(new JLabel(new ImageIcon("Image/Background_login.jpg")));
		setLayout(new BorderLayout());

		JPanel pTitle, pImage, pCenter, sPanel;
		pTitle = new JPanel();
		JLabel lblTitle = new JLabel(new ImageIcon("Image/login_logo.png"));

		lblTitle.setFont(new Font("Time new Roman", Font.BOLD, 25));
		lblTitle.setForeground(Color.orange);
		pTitle.add(lblTitle);
		add(pTitle, BorderLayout.NORTH);
		pImage = new JPanel();
		add(pImage, BorderLayout.WEST);

		pCenter = new JPanel();
		pCenter.setBorder(BorderFactory.createLoweredBevelBorder());
		FlowLayout layout = (FlowLayout) pCenter.getLayout();
		layout.setAlignment(FlowLayout.LEFT);
		Box b, b1;
		pCenter.add(b = Box.createHorizontalBox());
		b.add(b1 = Box.createVerticalBox());
		JPanel p1, p2, p3, p4;
		b1.add(p1 = new JPanel());
		p1.add(lblMa = new JLabel("Mã Thành Viên: "));
		p1.add(txtMa = new JTextField(62));
		txtMa.setText("Admin");
		txtMa.setEditable(false);
		FlowLayout layout1 = (FlowLayout) p1.getLayout();

		layout1.setAlignment(FlowLayout.LEFT);
		b1.add(p2 = new JPanel());
		p2.add(lblTen = new JLabel("Tên Thành Viên: "));
		p2.add(txtTen = new JTextField(25));
		p2.add(lblsdt = new JLabel("Số điện thoại:"));
		p2.add(txtsdt = new JTextField(25));
		FlowLayout layout2 = (FlowLayout) p2.getLayout();
		layout2.setAlignment(FlowLayout.LEFT);
		
		b1.add(p3 = new JPanel());
		p3.add(lblpass = new JLabel("Mật khẩu: "));
		p3.add(txtpass = new JPasswordField(25));
		p3.add(lblrepass = new JLabel("Nhập lại: "));
		p3.add(txtrepass = new JPasswordField(25));
		FlowLayout layout3 = (FlowLayout) p3.getLayout();
		layout3.setAlignment(FlowLayout.LEFT);
		
		b1.add(p4 = new JPanel());
		p4.add(lblMoTa = new JLabel("Mô tả: "));
		p4.add(txtMota = new JTextField(62));
		FlowLayout layout4 = (FlowLayout) p4.getLayout();
		layout4.setAlignment(FlowLayout.LEFT);
		
		b1.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblsdt.setPreferredSize(lblTen.getPreferredSize());
		lblMoTa.setPreferredSize(lblTen.getPreferredSize());
		lblpass.setPreferredSize(lblTen.getPreferredSize());
		lblrepass.setPreferredSize(lblTen.getPreferredSize());
		
		add(sPanel = new JPanel(), BorderLayout.SOUTH);
		sPanel.add(btnReg = new JButton("Đăng kí"));
		sPanel.add(btnExit = new JButton("Thoát"));
		btnReg.setIcon(new ImageIcon("Image/btnReg.png"));
		btnExit.setIcon(new ImageIcon("Image/btnexit.png"));
		btnReg.setPreferredSize(new Dimension(150, 30));
		btnExit.setPreferredSize(btnReg.getPreferredSize());
		pTitle.setOpaque(false);
		pImage.setOpaque(false);
		sPanel.setOpaque(false);
		btnReg.addActionListener(this);
		btnExit.addActionListener(this);
		add(pCenter, BorderLayout.CENTER);
	}
	
	private String[] getAdmin() {
		String maNV = txtMa.getText().trim();
		String tenNV = txtTen.getText().trim();
		String sdt = txtsdt.getText().trim();
		String mota = txtMota.getText().trim();
		String pass = String.copyValueOf(txtpass.getPassword()).trim();
		if (tenNV.length() == 0) {
			JOptionPane.showMessageDialog(null, "Tên Quản Trị không rỗng", "Error", JOptionPane.ERROR_MESSAGE);
			txtTen.requestFocus();
			return null;
		} else if (!tenNV.matches("[a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(null, "Tên Quản Trị bằng chữ KHÔNG DẤU", "Error", JOptionPane.ERROR_MESSAGE);
			txtTen.requestFocus();
			return null;
		}
		if (sdt.length() == 0) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không rỗng", "Error", JOptionPane.ERROR_MESSAGE);
			txtsdt.requestFocus();
			return null;
		} else
		if (sdt.length() != 0 && !sdt.matches("[0-9]{9,12}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại Không đúng", "Error", JOptionPane.ERROR_MESSAGE);
			txtsdt.requestFocus();
			return null;
		}
		if (pass.length() == 0) {
			JOptionPane.showMessageDialog(null, "Password Không rỗng", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if (!pass.equals(String.copyValueOf(txtrepass.getPassword()).trim())) {
			JOptionPane.showMessageDialog(null, "Password Không khớp", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return new String[] { maNV, tenNV, sdt, mota, encrypt.md5(pass)};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src.equals(btnReg)) {
			String nv[] = getAdmin();
			if(nv!=null) {
				Connection con = Database.getInstance().getConnection();
				PreparedStatement stm = null;
				PreparedStatement stm1 = null;
				int n;
				try {
					stm = con.prepareStatement("insert into NhanVien values(?, ?, ?, ?)");
					stm.setString(1, nv[0]);
					stm.setString(2, nv[1]);
					stm.setString(3, nv[2]);
					stm.setString(4, nv[3]);
					n = stm.executeUpdate();
					stm1 = con.prepareStatement("insert into Account values(?, ?)");
					stm1.setString(1, nv[0]);
					stm1.setString(2, nv[4]);
					stm1.executeUpdate();
				} catch (Exception w) {
					w.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Thành công!");
				new LoginForm().setVisible(true);
				this.dispose();
			}
		}
		if (src.equals(btnExit))
			this.dispose();
	}
}