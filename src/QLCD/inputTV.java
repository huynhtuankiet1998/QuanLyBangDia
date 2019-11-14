package QLCD;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Manager.ThanhVien;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class inputTV extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public JTextField txtMa;
	public JTextField txtTen;
	public  JTextField txtsdt;
	public JTextField txtdiachi;
	private JLabel lblMa, lblTen, lblsdt, lblgioitinh, lbldiachi, lblnghh;
	public JRadioButton radgioitinh;
	public UtilDateModel model;
	private JButton btnclean;
	
	public inputTV() {
		FlowLayout layout = (FlowLayout) this.getLayout();
		layout.setAlignment(FlowLayout.LEFT);
		Box b, b1, b2;
		add(b = Box.createHorizontalBox());
		b.add(b1 = Box.createVerticalBox());
		JPanel p1, p2, p3;
		b1.add(p1 = new JPanel());
		p1.add(lblMa = new JLabel("Mã Thành Viên: "));
		p1.add(txtMa = new JTextField(25));
		p1.add(lblTen = new JLabel("Tên Thành Viên: "));
		p1.add(txtTen = new JTextField(25));
		FlowLayout layout1 = (FlowLayout) p1.getLayout();

		layout1.setAlignment(FlowLayout.LEFT);
		b1.add(p2 = new JPanel());
		p2.add(lblsdt = new JLabel("Số điện thoại: "));
		p2.add(txtsdt = new JTextField(25));
		p2.add(lblgioitinh = new JLabel("Giới tính:"));
		p2.add(radgioitinh = new JRadioButton("Nam"));
		FlowLayout layout2 = (FlowLayout) p2.getLayout();
		
		layout2.setAlignment(FlowLayout.LEFT);
		b1.add(p3 = new JPanel());
		p3.add(lbldiachi = new JLabel("Địa chỉ: "));
		p3.add(txtdiachi = new JTextField(25));
		p3.add(lblnghh = new JLabel("Ngày Hết Hạn: "));
		model = new UtilDateModel();
		Calendar d = Calendar.getInstance();
		model.setDate(d.get(Calendar.YEAR),d.get(Calendar.MONTH), d.get(Calendar.DATE));
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		p3.add(datePicker);
		FlowLayout layout3 = (FlowLayout) p3.getLayout();
		layout3.setAlignment(FlowLayout.LEFT);
		
		b.add(b2 = Box.createVerticalBox());
		b2.add(btnclean = new JButton("Reset"));
		btnclean.setIcon(new ImageIcon("Image/clean-button.png"));
		btnclean.addActionListener(this);
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblsdt.setPreferredSize(lblTen.getPreferredSize());
		lbldiachi.setPreferredSize(lblTen.getPreferredSize());
		lblgioitinh.setPreferredSize(lblTen.getPreferredSize());
		lblnghh.setPreferredSize(lblTen.getPreferredSize());
	}

	public void clean()  {
		txtMa.setEditable(true);
		txtMa.setText("");
		txtTen.setText("");
		txtdiachi.setText("");
		txtsdt.setText("");
		radgioitinh.setSelected(true);
	}
	
	public ThanhVien addTV() {
		String maTV = txtMa.getText().trim();
		String tenTV = txtTen.getText().trim();
		boolean gt = radgioitinh.isSelected();
		String sdt = txtsdt.getText().trim();
		String dc = txtdiachi.getText().trim();
		java.util.Date date =  model.getValue();

		if (maTV.length() == 0) {
			JOptionPane.showMessageDialog(null, "Nhập vào mã Thành Viên!", "Error", JOptionPane.ERROR_MESSAGE);
			txtMa.requestFocus();
			return null;
		} else if (!maTV.matches("^TV[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Mã Thành Viên là 'TV' và sau là một số!", "Error",JOptionPane.ERROR_MESSAGE);
			txtMa.requestFocus();
			return null;
		}
		if (tenTV.length() == 0) {
			JOptionPane.showMessageDialog(null, "Tên Thành Viên không rỗng", "Error", JOptionPane.ERROR_MESSAGE);
			txtTen.requestFocus();
			return null;
		} else if (!tenTV.matches("[a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(null, "Tên Thành Viên bằng chữ và không dấu", "Error", JOptionPane.ERROR_MESSAGE);
			txtTen.requestFocus();
			return null;
		}
		if (sdt.length() != 0 && !sdt.matches("[0-9]{9,12}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại Không đúng", "Error", JOptionPane.ERROR_MESSAGE);
			txtsdt.requestFocus();
			return null;
		}
		return new ThanhVien(maTV, tenTV, gt, sdt, dc, date);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnclean))
			clean();
	}
}
