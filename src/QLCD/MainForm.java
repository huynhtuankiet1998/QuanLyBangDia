package QLCD;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import Manager.CD;
import Manager.DanhSachCD;
import Manager.DanhSachNhanVien;
import Manager.DanhSachPhieuThue;
import Manager.DanhSachThanhVien;
import Manager.NhanVien;
import Manager.PhieuThue;
import Manager.ThanhVien;

public class MainForm extends JFrame implements ActionListener, MouseListener {
	private static Font btnFont = new Font("Time new roman", Font.BOLD, 12);
	private JTabbedPane tab;
	private JTable table;
	private DefaultTableModel tbModel;
	private JButton btnDsCD;
	private JButton btnthem;
	private JButton btnUpdate;
	private JButton btnDsNv;
	private JButton btnthemnv;
	private JButton btnUpdatenv;
	private JButton btnDeletenv;
	private JButton btnDelete;
	private JButton btnlogout;
	private JButton btnexit;
	private JButton btnaddTV;
	private JButton btneditTV;
	private JButton btndeleteTV;
	private JButton btnlistTV;
	private JButton btncreatePT;
	private JButton btnremovePT;
	private JButton btnlistpt;
	private inputPT inputpt;
	private inputTV inputtv;
	private inputNV inputnv;
	private inputCD inputcd;
	private DanhSachCD DsCD;
	private DanhSachNhanVien dsnv;
	private JScrollPane scroll;
	private ArrayList<CD> listcd;
	private ArrayList<NhanVien> listnv;
	private DanhSachThanhVien dstv;
	private ArrayList<ThanhVien> listtv;
	private DanhSachPhieuThue dspt;
	private ArrayList<PhieuThue> listpt;
	private JButton btnsearch;
	private JButton btnSearchPT;
	private JButton btnSearchTV;
	private JButton btnSearchNV;

	public MainForm(int index) {
		setIconImage(new ImageIcon("Image/users.png").getImage());
		if (index == 1)
			setTitle("Quản lí Băng đĩa:  Users!");
		else
			setTitle("Quản lí Băng đĩa:  Administrator!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		add(tab = new JTabbedPane(), BorderLayout.NORTH);
		tab.addMouseListener(this);
		CDInit();
		muontraInit();
		TVInit();
		if (index == 2) {
			NVInit();
		}
		systemInit();
		// Tạo Bảng
		scroll = new JScrollPane(table = new JTable(tbModel = new DefaultTableModel()));
		scroll.setAutoscrolls(true);
		scroll.setPreferredSize(new Dimension(scroll.getWidth(), 300));
		table.setRowHeight(20);
		table.getAutoCreateRowSorter();
		table.getAutoResizeMode();
		table.addMouseListener(this);
		scroll.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		add(scroll, BorderLayout.CENTER);
	}

	// Hàm khởi tạo tab CD
	private void CDInit() {
		JToolBar toolBarCD = new JToolBar();
		toolBarCD.setFloatable(false);
		tab.addTab("Quản lí đĩa", new ImageIcon("Image/cd-bar.png"), toolBarCD);
		Box box, boxremove, box2, box3, box4;
		toolBarCD.add(box = Box.createVerticalBox());
		toolBarCD.addSeparator();
		box.add(btnDsCD = new JButton(new ImageIcon("Image/cd.png")));
		JLabel lblnv;
		box.add(lblnv = new JLabel("Tải lại DS"));
		lblnv.setFont(btnFont);
		toolBarCD.add(boxremove = Box.createVerticalBox());
		toolBarCD.addSeparator();
		boxremove.add(btnthem = new JButton(new ImageIcon("Image/cd-add.png")));
		JLabel lbladdnv;

		boxremove.add(lbladdnv = new JLabel("Thêm Đĩa"));
		lbladdnv.setFont(btnFont);
		toolBarCD.add(box2 = Box.createVerticalBox());
		toolBarCD.addSeparator();
		box2.add(btnUpdate = new JButton(new ImageIcon("Image/cd-edit.png")));
		JLabel lbleditnv;
		box2.add(lbleditnv = new JLabel("Cập nhật CD"));
		lbleditnv.setFont(btnFont);

		toolBarCD.add(box3 = Box.createVerticalBox());
		toolBarCD.addSeparator();
		box3.add(btnDelete = new JButton(new ImageIcon("Image/cd-delete.png")));		
		JLabel lbldelnv;
		box3.add(lbldelnv = new JLabel("Xóa bỏ CD"));

		toolBarCD.add(box4 = Box.createVerticalBox());
		toolBarCD.addSeparator();
		box4.add(btnsearch = new JButton(new ImageIcon("Image/search.png")));
		JLabel lblsearch;
		box4.add(lblsearch = new JLabel("Tìm CD"));
		
		lbldelnv.setFont(btnFont);
		btnDelete.setMnemonic(KeyEvent.VK_DELETE);
		btnthem.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnDsCD.addActionListener(this);
		btnsearch.addActionListener(this);
		btnthem.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnsearch.setEnabled(false);
		toolBarCD.add(inputcd = new inputCD());
		toolBarCD.addSeparator();
	}

	
	
	// ===========Khởi tạo tab Mượn- trả sách==========
	private void muontraInit() {
		JToolBar toolBarmuon = new JToolBar();
		toolBarmuon.setFloatable(false);
		tab.addTab("Thuê - trả đĩa", new ImageIcon("Image/users.png"), toolBarmuon);
		
		Box box0;
		toolBarmuon.add(box0 = Box.createVerticalBox());
		toolBarmuon.addSeparator();
		box0.add(btnlistpt = new JButton(new ImageIcon("Image/note-list.png")));
		JLabel lbllist;
		box0.add(lbllist = new JLabel("Tải lại DS"));
		lbllist.setFont(btnFont);
		btnlistpt.addActionListener(this);
		
		Box box, boxremove, box1;
		toolBarmuon.add(box = Box.createVerticalBox());
		toolBarmuon.addSeparator();
		box.add(btncreatePT = new JButton(new ImageIcon("Image/note-edit.png")));
		
		JLabel lblcreate;
		box.add(lblcreate = new JLabel("Tạo Phiếu Thuê"));
		lblcreate.setFont(btnFont);
		btncreatePT.addActionListener(this);
		
		toolBarmuon.add(boxremove = Box.createVerticalBox());
		toolBarmuon.addSeparator();
		boxremove.add(btnremovePT  = new JButton(new ImageIcon("Image/note-remove.png")));
		btnremovePT.addActionListener(this);
		JLabel lblremove;
		boxremove.add(lblremove = new JLabel("Xóa Phiếu Thuê"));
		lblremove.setFont(btnFont);
		
		toolBarmuon.add(box1 = Box.createVerticalBox());
		toolBarmuon.addSeparator();
		box1.add(btnSearchPT = new JButton(new ImageIcon("Image/search.png")));
		btnSearchPT.addActionListener(this);
		JLabel lblsearch;
		box1.add(lblsearch = new JLabel("Tìm Phiếu"));
		
		JPanel pinput;
		toolBarmuon.add(pinput = new JPanel());
		FlowLayout l = (FlowLayout) pinput.getLayout();
		l.setAlignment(FlowLayout.LEFT);
		pinput.add(inputpt = new inputPT());
		btncreatePT.setEnabled(false);
		btnremovePT.setEnabled(false);
		btnSearchPT.setEnabled(false);
		btnremovePT.setMnemonic(KeyEvent.VK_DELETE);
	}
	
	
	
	// ================ Hàm khởi tạo tab TV ==============
	private void TVInit() {
		JToolBar toolBarTV = new JToolBar();
		toolBarTV.setFloatable(false);
		tab.addTab("Thành Viên", new ImageIcon("Image/member-icon.png"), toolBarTV);

		Box box, boxremove, box2, box3, box4;

		toolBarTV.add(box = Box.createVerticalBox());
		toolBarTV.addSeparator();
		box.add(btnlistTV = new JButton(new ImageIcon("Image/member-list.png")));
		JLabel lbllist;
		box.add(lbllist = new JLabel("Tải lại DS"));
		lbllist.setFont(btnFont);

		toolBarTV.add(boxremove = Box.createVerticalBox());
		toolBarTV.addSeparator();
		boxremove.add(btnaddTV = new JButton(new ImageIcon("Image/member-add.png")));
		JLabel lbladd;
		boxremove.add(lbladd = new JLabel("Thêm Thành viên"));
		lbladd.setFont(btnFont);

		toolBarTV.add(box2 = Box.createVerticalBox());
		toolBarTV.addSeparator();
		box2.add(btneditTV = new JButton(new ImageIcon("Image/member-edit.png")));
		JLabel lbledit;
		box2.add(lbledit = new JLabel("CN Thành viên"));
		lbledit.setFont(btnFont);

		toolBarTV.add(box3 = Box.createVerticalBox());
		toolBarTV.addSeparator();
		box3.add(btndeleteTV = new JButton(new ImageIcon("Image/member-delete.png")));
		JLabel lbldel;
		box3.add(lbldel = new JLabel("Xóa Thành viên"));
		lbldel.setFont(btnFont);
		
		toolBarTV.add(box4 = Box.createVerticalBox());
		toolBarTV.addSeparator();
		box4.add(btnSearchTV = new JButton(new ImageIcon("Image/search.png")));
		btnSearchTV.addActionListener(this);
		JLabel lblsearch;
		box4.add(lblsearch = new JLabel("Tìm Thành Viên"));
		
		toolBarTV.add(inputtv = new inputTV());

		btnaddTV.addActionListener(this);
		btneditTV.addActionListener(this);
		btndeleteTV.addActionListener(this);
		btnlistTV.addActionListener(this);
		btnaddTV.setEnabled(false);		
		btneditTV.setEnabled(false);
		btndeleteTV.setEnabled(false);
		btndeleteTV.setMnemonic(KeyEvent.VK_DELETE);
		btnSearchTV.setEnabled(false);
	}
	
	
	
	// ================ Hàm khởi tạo tab NV ==============
	private void NVInit() {
		JToolBar toolBarNV = new JToolBar();
		toolBarNV.setFloatable(false);
		tab.addTab("Quản lí Nhân Viên", new ImageIcon("Image/users.png"), toolBarNV);

		// Các button chức năng
		Box box, boxremove, box2, box3, box4;
		toolBarNV.add(box = Box.createVerticalBox());
		toolBarNV.addSeparator();
		box.add(btnDsNv = new JButton(new ImageIcon("Image/user-list.png")));
		JLabel lblnv;
		box.add(lblnv = new JLabel("Tải lại DSNV"));
		lblnv.setFont(btnFont);
		
		toolBarNV.add(boxremove = Box.createVerticalBox());
		toolBarNV.addSeparator();
		boxremove.add(btnthemnv = new JButton(new ImageIcon("Image/user-add.png")));
		JLabel lbladdnv;
		boxremove.add(lbladdnv = new JLabel("Thêm Nhân Viên"));
		lbladdnv.setFont(btnFont);
		
		toolBarNV.add(box2 = Box.createVerticalBox());
		toolBarNV.addSeparator();
		box2.add(btnUpdatenv = new JButton(new ImageIcon("Image/user-edit.png")));
		JLabel lbleditnv;
		box2.add(lbleditnv = new JLabel("Cập nhật NV"));
		lbleditnv.setFont(btnFont);
		
		toolBarNV.add(box3 = Box.createVerticalBox());
		toolBarNV.addSeparator();
		box3.add(btnDeletenv = new JButton(new ImageIcon("Image/delete-user.png")));
		JLabel lbldelnv;
		box3.add(lbldelnv = new JLabel("Xóa Nhân Viên"));
		lbldelnv.setFont(btnFont);
		
		toolBarNV.add(box4 = Box.createVerticalBox());
		toolBarNV.addSeparator();
		box4.add(btnSearchNV = new JButton(new ImageIcon("Image/search.png")));
		btnSearchNV.addActionListener(this);
		JLabel lblsearch;
		box4.add(lblsearch = new JLabel("Tìm Nhân Viên"));
		toolBarNV.add(inputnv = new inputNV());
		toolBarNV.addSeparator();
		btnthemnv.addActionListener(this);
		btnUpdatenv.addActionListener(this);
		btnDeletenv.addActionListener(this);
		btnDsNv.addActionListener(this);
		btnthemnv.setEnabled(false);
		btnUpdatenv.setEnabled(false);
		btnDeletenv.setEnabled(false);
		btnDeletenv.setMnemonic(KeyEvent.VK_DELETE);
		btnSearchNV.setEnabled(false);
	}
	
	
	

	// ====================================Hàm khởi tạo tab hệ thống====================================
	private void systemInit() {
		JToolBar toolbarSystem = new JToolBar();
		tab.addTab("Hệ thống", new ImageIcon("Image/system.png"), toolbarSystem, "Thao tác với màn hình");
		Box box, boxremove;
		toolbarSystem.add(box = Box.createVerticalBox());
		toolbarSystem.addSeparator();
		JLabel lbllogout;
		box.add(btnlogout = new JButton(new ImageIcon("Image/logout-icon.png")));
		box.add(lbllogout = new JLabel("Đăng xuất"));
		btnlogout.addActionListener(this);
		lbllogout.setFont(btnFont);

		toolbarSystem.add(boxremove = Box.createVerticalBox());
		toolbarSystem.addSeparator();
		JLabel lblexit;
		boxremove.add(btnexit = new JButton(new ImageIcon("Image/exit-icon.png")));
		boxremove.add(lblexit = new JLabel("Thoát hệ thống"));
		btnexit.addActionListener(this);
		lblexit.setFont(btnFont);
	}
	
	
	
	// ====================================ActionPerForm====================================
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		// Button load Data
		if (src.equals(btnDsCD)) {
			updateTableData(0);
			btnthem.setEnabled(true);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			btnsearch.setEnabled(true);
			inputcd.txtMaCD.setEditable(true);
			inputcd.clean();
			Search("", 0);
		}
		if(src.equals(btnlistpt)) {
			updateTableData(1);
			btncreatePT.setEnabled(true);
			btnremovePT.setEnabled(false);
			btnSearchPT.setEnabled(true);
			Search("", 0);
		}
		if (src.equals(btnlistTV)) {
			updateTableData(2);
			btnaddTV.setEnabled(true);
			btneditTV.setEnabled(false);
			btndeleteTV.setEnabled(false);
			btnSearchTV.setEnabled(true);
			inputtv.clean();
			Search("", 0);
		}
		if (src.equals(btnDsNv)) {
			updateTableData(3);
			btnthemnv.setEnabled(true);
			btnUpdatenv.setEnabled(false);
			btnDeletenv.setEnabled(false);
			btnSearchNV.setEnabled(true);
			inputnv.clean();
			Search("", 0);
		}
		// Button Thêm
		if (src.equals(btnthem)) {
			CD cd = inputcd.addCD();
			if (cd != null) {
				String rowData[] = { cd.getMaCD(), cd.getTenCD(), cd.getTheloai(), cd.isTinhtrang() ? "Mới" : "Cũ",
						cd.getHangSX(), cd.getGhichu(), String.valueOf(cd.getDonGia()) };
				if (DsCD.create(cd.getMaCD(), cd.getTenCD(), cd.getTheloai(), cd.isTinhtrang(), cd.getHangSX(),
						cd.getGhichu(), cd.getDonGia())) {
					JOptionPane.showMessageDialog(this, "Thêm thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
					updateTableData(0);
				} else
					JOptionPane.showMessageDialog(this, "Trùng mã", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		} 
		else if(src.equals(btncreatePT)) {
			PhieuThue pt = inputpt.get();
			if(pt!=null) {
				if(dspt.create(pt.getSoPhieu(), pt.getMaTv(), pt.getNgaythue(),pt.getSoLuong())) {
					updateTableData(1);
					JOptionPane.showMessageDialog(null, "Tạo phiếu thành công");
				}
				else
					JOptionPane.showMessageDialog(null, "Trùng mã");
			}
		}
		else if (src.equals(btnaddTV)) {
			ThanhVien tv = inputtv.addTV();
			if (tv != null) {
				if (dstv.create(tv.getMaTV(), tv.getHoTen(), tv.isGioitinh(), tv.getSdt(), tv.getDchi(),tv.getNgayhh())) {
					JOptionPane.showMessageDialog(this, "Thêm thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
					updateTableData(2);
				} else
					JOptionPane.showMessageDialog(this, "Trùng mã", "Fail", JOptionPane.ERROR_MESSAGE);
			}
		} else if (src.equals(btnthemnv)) {
			String a[] = inputnv.addNV();
			if (a != null) {
				if (dsnv.create(a[0], a[1], a[2], a[3], a[4])) {
					JOptionPane.showMessageDialog(this, "Thêm thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
					updateTableData(3);
				} else {
					JOptionPane.showMessageDialog(this, "Trùng mã", "Fail", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		// Button cập nhật dữ liệu
		if (src.equals(btnUpdate)) {
			CD cd = inputcd.addCD();
			if (cd != null) {
				if (DsCD.update(cd.getMaCD(), cd.getTenCD(), cd.getTheloai(), cd.isTinhtrang(), cd.getHangSX(),
						cd.getGhichu(), cd.getDonGia())) {
					JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					updateTableData(0);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
					inputcd.clean();
				} else
					JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "fair", JOptionPane.ERROR_MESSAGE);
			}
		} 
		if (src.equals(btneditTV)) {
			ThanhVien tv = inputtv.addTV();
			if (tv != null) {
				if (dstv.update(tv.getMaTV(), tv.getHoTen(), tv.isGioitinh(), tv.getSdt(), tv.getDchi(),
						tv.getNgayhh())) {
					JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					updateTableData(2);
					btneditTV.setEnabled(false);
					btndeleteTV.setEnabled(false);
					inputtv.clean();
				} else
					JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "fair", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (src.equals(btnUpdatenv)) {
			String nv[] = inputnv.addNV();
			if (dsnv.update(nv[0], nv[1], nv[2], nv[3])) {
				JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
				updateTableData(3);
				btnDeletenv.setEnabled(false);
				btnUpdatenv.setEnabled(false);
				inputnv.clean();
			} else
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "Success", JOptionPane.INFORMATION_MESSAGE);
		}

		// Button xóa dữ liệu
		if (src.equals(btnDelete)) {
			int index = table.getSelectedRow();
			if (index != -1)
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa", "Delete", JOptionPane.WARNING_MESSAGE) == 0) {
					DsCD.delete(DsCD.getCD(index).getMaCD());
					updateTableData(0);
					JOptionPane.showMessageDialog(this, "Xóa thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			btnDelete.setEnabled(false);
			btnUpdate.setEnabled(false);
			inputcd.clean();
		} 
		if(src.equals(btnremovePT)) {
			int index = table.getSelectedRow();
			if(index!=-1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa", "Delete", JOptionPane.WARNING_MESSAGE) == 0) {
					if(dspt.delete(dspt.getPT(index).getSoPhieu())) {
						updateTableData(1);
						JOptionPane.showMessageDialog(this, "Xóa thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
						btnremovePT.setEnabled(false);
					}
					else
						JOptionPane.showMessageDialog(this, "Không thể xóa vì chưa hết thời hạn thuê!!");
				}
			}
		}
			
		else if (src.equals(btndeleteTV)) {
			int index = table.getSelectedRow();
			if (index != -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa", "Delete", JOptionPane.WARNING_MESSAGE) == 0) {
					if(dstv.delete(dstv.getTV(index).getMaTV())) {
						updateTableData(2);
						JOptionPane.showMessageDialog(this, "Xóa thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(this, "Không thể xóa vì khách hàng còn đang thuê CD!!");
				}
			}
			btneditTV.setEnabled(false);
			btndeleteTV.setEnabled(false);
			inputtv.clean();
		}
		if (src.equals(btnDeletenv)) {
			int index = table.getSelectedRow();
			if (index != -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa", "Delete", JOptionPane.WARNING_MESSAGE) == 0) {
					String ma = dsnv.getNV(index).getMaNV();
					if(!ma.equals("Admin")) {
						dsnv.delete(dsnv.getNV(index).getMaNV());
						updateTableData(3);
						JOptionPane.showMessageDialog(this, "Xóa thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(this, "Không được xóa người quản trị", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			btnDeletenv.setEnabled(false);
			btnUpdatenv.setEnabled(false);
			inputnv.clean();
		}
		
		//Button tìm kiếm
		if(src.equals(btnsearch))
			new Search(0, this, "Tìm kiếm", true).setVisible(true);
		else if(src.equals(btnSearchPT))
			new Search(1, this, "Tìm kiếm", true).setVisible(true);
		else if(src.equals(btnSearchTV))
			new Search(2, this, "Tìm kiếm", true).setVisible(true);
		else if(src.equals(btnSearchNV))
			new Search(3, this, "Tìm kiếm", true).setVisible(true);
		// Button Đăng xuất
		if (src.equals(btnlogout)) {
			new LoginForm().setVisible(true);
			this.dispose();
		}
		// Button Thoát
		if (src.equals(btnexit))
			if (JOptionPane.showConfirmDialog(this, "Muốn thoát hệ thống?", "Thoát", JOptionPane.YES_NO_OPTION) == 0)
				this.dispose();
	}

	
	
	// ==============================Cập nhật lại tên cột===========================================
	private void updateTableheader(int index) {
		tbModel.getDataVector().removeAllElements();
		tbModel.fireTableDataChanged();
		if (index == 0) {
			String header[] = "Mã CD;Tên CD;Thể Loại;Tình Trạng;Hãng SX;Ghi Chú;Đơn Giá".split(";");
			tbModel.setColumnIdentifiers(header);
		} else if(index==1) {
			String header[] = "Số Phiếu;Ngày Thuê;Mã Thành Viên".split(";");
			tbModel.setColumnIdentifiers(header);
		}
		else if (index == 2) {
			String header[] = "Mã Thành Viên;Tên Thành Viên;Giới tính;Số điện Thoại;Địa chỉ;Ngày hết hạn".split(";");
			tbModel.setColumnIdentifiers(header);
		} else if (index == 3) {
			String header[] = "Mã Nhân Viên;Tên Nhân Viên;Số điện Thoại;Mô Tả".split(";");
			tbModel.setColumnIdentifiers(header);
		}
	}

	
	
	// ============================= Load dữ liệu Lên Bảng=====================================
	private void updateTableData(int index) {
		updateTableheader(index);
		if (index == 0) {
			DsCD = new DanhSachCD();
			listcd = DsCD.docTuBang();
			ArrayList<String> listType = new ArrayList<>();
			for (CD cd : listcd) {
				String rowData[] = { cd.getMaCD().trim(), cd.getTenCD(), cd.getTheloai(),
						(cd.isTinhtrang() == true) ? "Mới" : "Cũ", cd.getHangSX(), cd.getGhichu(),
						String.valueOf(cd.getDonGia()) };
				if (!listType.contains(cd.getTheloai()))
					listType.add(cd.getTheloai());
				tbModel.addRow(rowData);
			}
		}
		
		if(index==1) {
			dspt = new DanhSachPhieuThue();
			listpt = dspt.docTuBang();
			for (PhieuThue pt : listpt) {
				String rowData[] = {pt.getSoPhieu(), new SimpleDateFormat("dd/MM/yyyy").format(pt.getNgaythue()), pt.getMaTv() };
				tbModel.addRow(rowData);
			}
		}
		
		if (index == 2) {
			dstv = new DanhSachThanhVien();
			listtv = dstv.docTuBang();
			for (ThanhVien tv : listtv) {
				String rowData[] = { tv.getMaTV(), tv.getHoTen(), tv.isGioitinh() ? "Nam" : "Nữ", tv.getSdt(),
						tv.getDchi(), new SimpleDateFormat("dd/MM/yyyy").format(tv.getNgayhh()) };
				tbModel.addRow(rowData);
			}
		}
		
		if (index == 3) {
			dsnv = new DanhSachNhanVien();
			listnv = dsnv.docTuBang();
			for (NhanVien nv : listnv) {
				String rowData[] = { nv.getMaNV(), nv.getTenNV(), nv.getSdt(), nv.getMota() };
				tbModel.addRow(rowData);
			}
		}
	}
	
	
	// ======================Set dữ liệu cho các trường nhập=================================
	private void setInfo(int tindex, int tabindex) {
		if (tabindex == 0) {
			CD cd = DsCD.getCD(tindex);
			inputcd.txtMaCD.setEditable(false);
			inputcd.txtMaCD.setText(cd.getMaCD());
			inputcd.txtTenCD.setText(cd.getTenCD());
			inputcd.txtHangsx.setText(cd.getHangSX());
			inputcd.txtghichu.setText(cd.getGhichu());
			inputcd.txtDonGia.setText(String.valueOf(cd.getDonGia()));

			for (int j = 0; j < inputcd.cbTheLoai.getItemCount(); j++)
				if (inputcd.cbTheLoai.getItemAt(j).toString().equals(cd.getTheloai()))
					inputcd.cbTheLoai.setSelectedItem(cd.getTheloai());
			inputcd.radtt.setSelected(cd.isTinhtrang());
		}

		if (tabindex == 2) {
			ThanhVien tv = dstv.getTV(tindex);
			inputtv.txtMa.setEditable(false);
			inputtv.txtMa.setText(tv.getMaTV());
			inputtv.txtTen.setText(tv.getHoTen());
			inputtv.radgioitinh.setSelected(tv.isGioitinh());
			inputtv.txtsdt.setText(tv.getSdt());
			inputtv.txtdiachi.setText(tv.getDchi());
			inputtv.model.setValue(tv.getNgayhh());
		}

		if (tabindex == 3) {
			NhanVien nv = dsnv.getNV(tindex);
			inputnv.txtMa.setEditable(false);
			inputnv.txtpass.setEditable(false);
			inputnv.txtrepass.setEditable(false);
			inputnv.txtMa.setText(nv.getMaNV());
			inputnv.txtTen.setText(nv.getTenNV());
			inputnv.txtSTD.setText(nv.getSdt());
			inputnv.txtpass.setText(String.valueOf(Integer.MAX_VALUE));
			inputnv.txtrepass.setText(String.valueOf(Integer.MAX_VALUE));
		}
	}

	// ============================Tìm kiếm trong JTable===========================
	public boolean Search(String s, int index) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>((TableModel) table.getModel());
		table.setRowSorter(sorter);
		if (s.trim().length() == 0) {
			sorter.setRowFilter(null);
			return false;
		}
		else if(index!=-1){
			sorter.setRowFilter(RowFilter.regexFilter(s, index));
			return true;
		}
		else {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + s));
			return true;
		}
	}

	// =================================Mouse CLick=================================
	@Override
	public void mouseClicked(MouseEvent e) {
		int index = table.getSelectedRow();
		if ((index != -1) && e.getSource().equals(table)) {
			if (tab.getSelectedIndex() == 0) {
				btnDelete.setEnabled(true);
				btnUpdate.setEnabled(true);
				setInfo(index, 0);
			}
			else if(tab.getSelectedIndex()==1) {
				btnremovePT.setEnabled(true);
			}
			else if (tab.getSelectedIndex() == 2) {
				btndeleteTV.setEnabled(true);
				btneditTV.setEnabled(true);
				setInfo(index, 2);
			} else if (tab.getSelectedIndex() == 3) {
				setInfo(index, 3);
				btnDeletenv.setEnabled(true);
				btnUpdatenv.setEnabled(true);
			}
		}
		else {
			int tabindex = tab.getSelectedIndex();
			if ((tabindex != -1) && e.getSource().equals(tab)) {
				if (tabindex == 0) {
					inputcd.clean();
					inputpt.setVisible(false);
					btnthem.setEnabled(false);
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnsearch.setEnabled(false);
					updateTableheader(tabindex);
				}
				else if(tabindex == 1) {
					inputpt.setVisible(true);
					inputpt.clean();
					btncreatePT.setEnabled(false);
					btnremovePT.setEnabled(false);
					btnSearchPT.setEnabled(false);
					updateTableheader(tabindex);
				}
				else if (tabindex == 2) {
					inputtv.clean();
					btnaddTV.setEnabled(false);
					btneditTV.setEnabled(false);
					btndeleteTV.setEnabled(false);
					btnSearchTV.setEnabled(false);
					inputpt.setVisible(false);
					updateTableheader(tabindex);
				}
				else if (tabindex == 3) {
					if(tab.getTitleAt(3).equals("Thành Viên")) {
						inputnv.clean();
						btnDeletenv.setEnabled(false);
						btnUpdatenv.setEnabled(false);
						btnSearchNV.setEnabled(false);
					}
					inputpt.setVisible(false);
					updateTableheader(tabindex);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}