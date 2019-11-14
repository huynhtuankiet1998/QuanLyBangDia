package QLCD;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Manager.DanhSachPhieuThue;
import Manager.DanhSachThanhVien;
import Manager.PhieuThue;
import Manager.ThanhVien;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class inputPT extends JPanel implements ActionListener{


	public JTextField txtsp;
	public JTextField txtma;
	public JTextField txtngay;
	public JTextField txtngthue;
	public JTextField txtsl;
	public JTextField txtmanv;
	public UtilDateModel model;
	private ArrayList<PhieuThue> ds;
	private AbstractButton btnclean;
	public inputPT() {
		super();
		ds= new DanhSachPhieuThue().docTuBang();
		
		
		FlowLayout layout = (FlowLayout) this.getLayout();
		layout.setAlignment(FlowLayout.LEFT);
		Box b, b1, b2;
		add(b = Box.createHorizontalBox());
		b.add(b1 = Box.createVerticalBox());
		JPanel p1, p2, p3;
		b1.add(p1 = new JPanel());
		JLabel lblsp;
		p1.add(lblsp = new JLabel("Số phiếu thuê: "));
		p1.add(txtsp = new JTextField(25));
		JLabel lblsl;
		p1.add(lblsl = new JLabel("Số lượng: "));
		p1.add(txtsl = new JTextField(25));
		FlowLayout layout1 = (FlowLayout) p1.getLayout();

		layout1.setAlignment(FlowLayout.LEFT);
		b1.add(p2 = new JPanel());
		JLabel lblMa;
		p2.add(lblMa = new JLabel("Mã CD: "));
		p2.add(txtma = new JTextField(25));
		JLabel lblngay;
		p2.add(lblngay = new JLabel("Số ngày thuê: "));
		p2.add(txtngay = new JTextField(25));
		FlowLayout layout2 = (FlowLayout) p2.getLayout();
		
		
		layout2.setAlignment(FlowLayout.LEFT);
		b1.add(p3 = new JPanel());
		JLabel lblmanv;
		p3.add(lblmanv = new JLabel("Mã NV: "));
		p3.add(txtmanv = new JTextField(25));
		JLabel lblngthue;
		p3.add(lblngthue = new JLabel("Ngày thuê: "));
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
		
		lblMa.setPreferredSize(lblsp.getPreferredSize());
		lblsl.setPreferredSize(lblsp.getPreferredSize());
		lblngay.setPreferredSize(lblsp.getPreferredSize());
		lblmanv.setPreferredSize(lblsp.getPreferredSize());
		lblngthue.setPreferredSize(lblsp.getPreferredSize());
		
	}
//	--------------------------------------code chuc nang-----------------------------------


	
	

	public void clean() {
		txtsp.setEditable(true);
		txtsp.setText("");
		txtsl.setText("");
		txtma.setText("");
		txtngay.setText("");
		txtngthue.setText("");
	}
	

	public PhieuThue addPT() {
		String sp = txtsp.getText().trim();
		String sl = txtsl.getText().trim();
		String ma = txtma.getText().trim();
		String n = txtngay.getText().trim();
		String nt = txtngthue.getText().trim();
		String manv = txtmanv.getText().trim();
		java.util.Date date =  model.getValue();

		if (sp.length() == 0) {
			JOptionPane.showMessageDialog(null, "số phiếu thuê không được trống!", "Error", JOptionPane.ERROR_MESSAGE);
			txtsp.requestFocus();
			return null;
		} else if (!sp.matches("^SP[0-9]+")) {
			JOptionPane.showMessageDialog(null, "số phiểu thuê là 'SP' và sau là một số!", "Error",JOptionPane.ERROR_MESSAGE);
			txtsp.requestFocus();
			return null;
		}
		if (sl.length() == 0) {
			JOptionPane.showMessageDialog(null, "Số lượng không được rỗng", "Error", JOptionPane.ERROR_MESSAGE);
			txtsl.requestFocus();
			return null;
		} else if (!ma.matches("CD[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Mã CD là 'CD' và 1 số ", "Error", JOptionPane.ERROR_MESSAGE);
			txtma.requestFocus();
			return null;
		}
		if (n.length() != 0 && !n.matches("[0-45]")) {
			JOptionPane.showMessageDialog(null, "Số ngày thuê từ 0-45 ngày", "Error", JOptionPane.ERROR_MESSAGE);
			txtngay.requestFocus();
			return null;
			if (manv.length() == 0) {
				JOptionPane.showMessageDialog(null, "Nhập vào mã Nhân Viên!", "Error", JOptionPane.ERROR_MESSAGE);
				txtmanv.requestFocus();
				return null;
			} else if (!manv.matches("^NV[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Mã Nhân Viên Phải là \"NV[0-9]+\"!", "Error",
						JOptionPane.ERROR_MESSAGE);
				txtmanv.requestFocus();
				return null;
		}
		return new PhieuThue(sp, ma, date,Integer.valueOf(sl));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnclean))
			clean();
	}
}