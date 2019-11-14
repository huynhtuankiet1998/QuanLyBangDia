package QLCD;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Search extends JDialog implements ActionListener{
	private JComboBox<Object> cbcollumn;
	private JTextField txtSearch;
	private JButton btnSearch;
	private MainForm owner;
	private JTextField txtSearch1;
	private JButton btnSearch1;
	public Search(int index, Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		this.owner = (MainForm) owner;
		setSize(400, 200);
		setLocationRelativeTo(null);
		JTabbedPane tab = new JTabbedPane();
		add(tab);
		
		JPanel p1, p2;
		
		tab.addTab("Tìm đơn giản", p1 = new JPanel());
		p1.add(new JLabel("Tìm: "));
		p1.add(txtSearch1 = new JTextField(20));
		p1.add(btnSearch1 = new JButton("Tìm Kiếm"));
		btnSearch1.addActionListener(this);
		
		tab.addTab("Tìm nâng cao", p2 = new JPanel());
		p2.add(new JLabel("Tìm kiếm theo:"));
		p2.add(cbcollumn = new JComboBox<>());
		p2.add(new JLabel("Tìm: "));
		p2.add(txtSearch = new JTextField(20));
		p2.add(btnSearch = new JButton("Tìm Kiếm"));
		btnSearch.addActionListener(this);
		if(index==0)
			loadCD();
		else if(index==1)
			loadPM();
		else if(index==2)
			loadTV();
		else if(index==3)
			loadNV();
	}
	
	private void loadCD() {
		cbcollumn.addItem("Mã CD");
		cbcollumn.addItem("Tên CD");
		cbcollumn.addItem("Thể loại");
		cbcollumn.addItem("Tình trạng");
		cbcollumn.addItem("Hãng SX");
	}
	private void loadNV() {
		cbcollumn.addItem("Mã NV");
		cbcollumn.addItem("Tên NV");
		cbcollumn.addItem("Số điện thoại");
	}
	private void loadPM() {
		cbcollumn.addItem("Số Phiếu");
		cbcollumn.addItem("Mã TV");
	}
	private void loadTV() {
		cbcollumn.addItem("Mã TV");
		cbcollumn.addItem("Họ Tên");
		cbcollumn.addItem("Giới tính");
		cbcollumn.addItem("Số điện thoại");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = cbcollumn.getSelectedIndex();
		if(e.getSource().equals(btnSearch))
			owner.Search(txtSearch.getText().trim(), index);
		else {
			owner.Search(txtSearch1.getText().trim(), -1);			
		}
		this.dispose();
	}
}
