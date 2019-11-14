package QLCD;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Manager.CD;

public class inputCD extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public JTextField txtMaCD;
	public JTextField txtTenCD;
	public JComboBox<String> cbTheLoai;
	public JTextField txtHangsx;
	public JTextField txtghichu;
	public JTextField txtDonGia;
	public JRadioButton radtt;
	private JLabel lblMaCD, lblTenCD, lbltheloai, lblHangsx, lblDonGia, lblghichu;
	private JButton btnclean;

	public inputCD() {
		FlowLayout layout = (FlowLayout) this.getLayout();
		layout.setAlignment(FlowLayout.LEFT);
		Box bcenter = Box.createHorizontalBox();
		add(bcenter, BorderLayout.CENTER);
		Box b1 = Box.createVerticalBox();
		bcenter.add(b1);
		JPanel p1, p2, p3;
		b1.add(p1 = new JPanel());
		p1.add(lblMaCD = new JLabel("Mã CD: "));
		p1.add(txtMaCD = new JTextField(25));
		p1.add(lblTenCD = new JLabel("Tên CD: "));
		p1.add(txtTenCD = new JTextField(25));
		FlowLayout layout1 = (FlowLayout) p1.getLayout();
		layout1.setAlignment(FlowLayout.LEFT);
		
		b1.add(p2 = new JPanel());
		p2.add(lblHangsx = new JLabel("Hãng Sản Xuất: "));
		p2.add(txtHangsx = new JTextField(25));
		p2.add(lbltheloai = new JLabel("Thể Loại: "));
		p2.add(cbTheLoai = new JComboBox<>());
		p2.add(new JLabel("Tình trạng: "));
		p2.add(radtt = new JRadioButton("Mới"));
		FlowLayout layout2 = (FlowLayout) p2.getLayout();
		layout2.setAlignment(FlowLayout.LEFT);
		radtt.setSelected(true);
		
		b1.add(p3 = new JPanel());
		p3.add(lblDonGia = new JLabel("Đơn Giá: "));
		p3.add(txtDonGia = new JTextField(25));
		p3.add(lblghichu = new JLabel("Ghi Chú: "));
		p3.add(txtghichu = new JTextField(25));
		FlowLayout layout3 = (FlowLayout) p3.getLayout();
		layout3.setAlignment(FlowLayout.LEFT);
		
		Box b2 = Box.createVerticalBox();
		bcenter.add(b2);
		b2.add(btnclean = new JButton("Reset"));
		btnclean.setIcon(new ImageIcon("Image/clean-button.png"));
		btnclean.addActionListener(this);
		
		String theloai[] = "Không Xác Định;Pop;Nhạc trẻ;Nhạc Vàng;Nhạc đỏ;Nhạc Ballad;Nonstop;Funk;EDM;Remix".split(";");
		for (int i = 0; i < theloai.length; i++) cbTheLoai.addItem(theloai[i]);
		lblMaCD.setPreferredSize(lblHangsx.getPreferredSize());
		lblTenCD.setPreferredSize(lblHangsx.getPreferredSize());
		lbltheloai.setPreferredSize(lblHangsx.getPreferredSize());
		lblDonGia.setPreferredSize(lblHangsx.getPreferredSize());
		lblghichu.setPreferredSize(lblHangsx.getPreferredSize());
	}
	public void clean() {
		txtMaCD.setEditable(true);
		txtMaCD.setText("");
		txtTenCD.setText("");
		txtHangsx.setText("");
		txtghichu.setText("");
		txtDonGia.setText("");
		cbTheLoai.setSelectedIndex(0);
	}

	public CD addCD() {
		String maCD = txtMaCD.getText().trim();
		String hangSX = txtHangsx.getText().trim();
		String ghichu = txtghichu.getText().trim();
		String donGia = txtDonGia.getText().trim();
		String tenCD = txtTenCD.getText().trim();
		if (maCD.length() == 0) {
			JOptionPane.showMessageDialog(null, "Nhập vào mã băng đĩa", "Error", JOptionPane.ERROR_MESSAGE);
			txtMaCD.requestFocus();
			return null;
		} else if (!maCD.matches("CD[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Mã Băng đĩa sai định dạng \"CD[0-9]+\"", "Error", JOptionPane.ERROR_MESSAGE);
			txtMaCD.requestFocus();
			return null;
		}
		if(tenCD.trim().length()==0) {
			JOptionPane.showMessageDialog(null, "Tên Không rỗng");
			txtTenCD.requestFocus();
			return null;
		} else
		if (!tenCD.matches("[a-zA-Z ]*")) {
			JOptionPane.showMessageDialog(null, "Tên CD bằng chữ không dấu", "Error", JOptionPane.ERROR_MESSAGE);
			txtTenCD.requestFocus();
			return null;
		}
		if(hangSX.trim().length()==0) {
			JOptionPane.showMessageDialog(null, "Hãng SX Không rỗng");
			txtHangsx.requestFocus();
			return null;
		}
		String theloai = cbTheLoai.getSelectedItem().toString();
		boolean tinhtrang = radtt.isSelected() ? true : false;
		if (donGia.length() > 0 && !donGia.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Nhập sai định dạng số", "Error", JOptionPane.ERROR_MESSAGE);
			txtDonGia.requestFocus();
			return null;
		} else donGia = "0";
		return new CD(maCD, tenCD, theloai, tinhtrang, hangSX, ghichu, Integer.valueOf(donGia));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnclean))
			clean();
	}
}