package QLCD;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Process.encrypt;

public class inputNV extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public JTextField txtMa;
	public JTextField txtTen;
	private JTextField txtmota;
	private JLabel lblMa, lblTen, lblSDT, lblmota;
	public JTextField txtSTD;
	public JLabel lblpass;
	public JPasswordField txtpass;
	private JLabel lblrepass;
	public JPasswordField txtrepass;
	private JButton btnclean;
	
	public inputNV() {
		FlowLayout layout = (FlowLayout) this.getLayout();
		layout.setAlignment(FlowLayout.LEFT);
		Box b, b1, b2;
		add(b = Box.createHorizontalBox());
		b.add(b1 = Box.createVerticalBox());
		JPanel p1, p2, p3;

		b1.add(p1 = new JPanel());
		p1.add(lblMa = new JLabel("Mã Nhân Viên: "));
		p1.add(txtMa = new JTextField(25));
		p1.add(lblTen = new JLabel("Tên Nhân Viên: "));
		p1.add(txtTen = new JTextField(25));
		FlowLayout layout1 = (FlowLayout) p1.getLayout();

		layout1.setAlignment(FlowLayout.LEFT);
		b1.add(p2 = new JPanel());
		p2.add(lblSDT = new JLabel("Số điện thoại: "));
		p2.add(txtSTD = new JTextField(25));
		p2.add(lblmota = new JLabel("Mô tả: "));
		p2.add(txtmota = new JTextField(25));
		FlowLayout layout2 = (FlowLayout) p2.getLayout();
		
		layout2.setAlignment(FlowLayout.LEFT);
		b1.add(p3 = new JPanel());
		p3.add(lblpass = new JLabel("Mật khẩu: "));
		p3.add(txtpass = new JPasswordField(25));
		p3.add(lblrepass = new JLabel("Nhập lại: "));
		p3.add(txtrepass = new JPasswordField(25));
		FlowLayout layout3 = (FlowLayout) p3.getLayout();
		layout3.setAlignment(FlowLayout.LEFT);
		
		b.add(b2 = Box.createVerticalBox());
		b2.add(btnclean = new JButton("Reset"));
		btnclean.setIcon(new ImageIcon("Image/clean-button.png"));
		btnclean.addActionListener(this);
		
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblSDT.setPreferredSize(lblTen.getPreferredSize());
		lblmota.setPreferredSize(lblTen.getPreferredSize());
		lblpass.setPreferredSize(lblTen.getPreferredSize());
		lblrepass.setPreferredSize(lblTen.getPreferredSize());
	}

	public void clean()  {
		txtMa.setEditable(true);
		txtpass.setEditable(true);
		txtrepass.setEditable(true);
		txtMa.setText("");
		txtTen.setText("");
		txtSTD.setText("");
		txtmota.setText("");
		txtpass.setText("");
		txtrepass.setText("");
	}
	
	public String[] addNV() {
		String maNV = txtMa.getText().trim();
		String tenNV = txtTen.getText().trim();
		String sdt = txtSTD.getText().trim();
		String mota = txtmota.getText().trim();
		String pass = String.copyValueOf(txtpass.getPassword()).trim();

		if (maNV.length() == 0) {
			JOptionPane.showMessageDialog(null, "Nhập vào mã Nhân Viên!", "Error", JOptionPane.ERROR_MESSAGE);
			txtMa.requestFocus();
			return null;
		} else if (!maNV.matches("^NV[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Mã Nhân Viên Phải là \"NV[0-9]+\"!", "Error",
					JOptionPane.ERROR_MESSAGE);
			txtMa.requestFocus();
			return null;
		}
		if (tenNV.length() == 0) {
			JOptionPane.showMessageDialog(null, "Tên Nhân Viên không rỗng", "Error", JOptionPane.ERROR_MESSAGE);
			txtTen.requestFocus();
			return null;
		} else if (!tenNV.matches("[a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(null, "Tên Nhân Viên bằng chữ không dấu", "Error", JOptionPane.ERROR_MESSAGE);
			txtTen.requestFocus();
			return null;
		}
		if (sdt.length() != 0 && !sdt.matches("[0-9]{9,12}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại Không đúng", "Error", JOptionPane.ERROR_MESSAGE);
			txtSTD.requestFocus();
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
		return new String[] { maNV, tenNV, sdt, mota, encrypt.md5(pass) };
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnclean))
			clean();
	}
}