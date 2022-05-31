package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DAO.CTHDModel;
import DAO.ConnectionProvider;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.LoaiSanPhamDAO;
import DAO.SanPhamDAO;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.KhachHang;
import entities.LoaiSp;
import entities.SanPham;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class frmBanHang extends JFrame {

	private JPanel contentPane;
	private JTextField txtTongTien;
	private JTextField txtTenSanPham;
	private JTextField txtGiaBan;
	private JComboBox cbbLoaiSP;
	private JTextField txtSoLuong;
	private JTextField txtSearch;
	private JTable tableSanPham;
	private JTable tableCTHD;
	private JTextField txtMaHD;
	private JTextField txtTenNhanVien;
	public static Integer IDSP;
	public static Integer IDNV = frmLogin.IDNV;
	public static String TenNV = frmLogin.TenNV;
	public static Integer IDHD;
	public static int GiaBan;
	public static String SDT;
	public double TongTien;
	public static Integer IDKH;
	private static Integer Levellayluon;
	public JLabel lblHinh;
	public HoaDonDAO HDDAO = new HoaDonDAO();
	public ConnectionProvider cn = new ConnectionProvider();
	public JComboBox cbbKH;
	private JTextField txtTongTienDay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBanHang frame = new frmBanHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void Clear() {
		cbbKH.setSelectedIndex(0);
		txtTongTien.setText(String.valueOf(0));
		ShowCTHD(0);
		txtSearch.setText("");
		txtTenSanPham.setText("");
		txtGiaBan.setText("");
		txtMaHD.setText("");
		lblHinh.setText("");
	}
	public ImageIcon ResizeImage(String Path) {
		ImageIcon a = new ImageIcon(Path);
		Image img = a.getImage();
		Image newImg = img.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);

		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	public void Loadcombobox() {
		ArrayList<LoaiSp> sp = LoaiSanPhamDAO.getAllCategory();
		Iterator<LoaiSp> itr = sp.iterator();
		while (itr.hasNext()) {
			LoaiSp pro = itr.next();
			cbbLoaiSP.addItem(pro.getTenLoaiSp());
		}
		String Name = (String) cbbLoaiSP.getSelectedItem();
		FilterNameSanPhamByCategory(Name);
	}

	public void LoadCbbKH() {
		cbbKH.addItem("Khách Hàng Vãng Lai");
		ArrayList<KhachHang> sp = new KhachHangDAO().getAll();
		Iterator<KhachHang> itr = sp.iterator();
		while (itr.hasNext()) {
			KhachHang pro = itr.next();
			cbbKH.addItem("0" + pro.getSdt());
		}

	}

	public void showMoneyGetdate() {
		double a = new HoaDonDAO().GetMoneyofDay();
		txtTongTienDay.setText(String.valueOf(a));
	}

	public void FilterNameSanPhamByCategory(String Name, String Namecategory) {
		DefaultTableModel dtm = (DefaultTableModel) tableSanPham.getModel();
		dtm.setRowCount(0);
		ArrayList<SanPham> sp = SanPhamDAO.getAllbyNameProduct(Name, Namecategory);
		Iterator<SanPham> itr = sp.iterator();
		while (itr.hasNext()) {
			SanPham pro = itr.next();
			dtm.addRow(new Object[] { pro.getTenSp() });
		}
	}

	public void FilterNameSanPhamByCategory(String Name) {
		DefaultTableModel dtm = (DefaultTableModel) tableSanPham.getModel();
		dtm.setRowCount(0);
		ArrayList<SanPham> sp = SanPhamDAO.getAllbyNameCategory(Name);
		Iterator<SanPham> itr = sp.iterator();
		while (itr.hasNext()) {
			SanPham pro = itr.next();
			dtm.addRow(new Object[] { pro.getTenSp() });
		}
	}

	public void ShowCTHD(int ID) {
		DefaultTableModel dtm = (DefaultTableModel) tableCTHD.getModel();
		dtm.setRowCount(0);
		ArrayList<CTHDModel> ct = HDDAO.getCTHDbyID(ID);
		Iterator<CTHDModel> itr = ct.iterator();
		double tam = 0;
		while (itr.hasNext()) {
			CTHDModel pro = itr.next();
			dtm.addRow(new Object[] { pro.getTenSP(), pro.getSoluong(), pro.getGiaBan(), pro.getThanhTien() });
			tam += pro.getThanhTien();
		}
		TongTien = tam;
		if (Levellayluon == 1) {
			TongTien -= (TongTien / 20);
		} else if (Levellayluon == 2) {
			TongTien -= (TongTien / 10);
			;
		} else if (Levellayluon == 3) {
			TongTien -= (TongTien / 5);
		} else if (Levellayluon == null) {
			TongTien = tam;
		}
		txtTongTien.setText(String.valueOf(TongTien));
	}

	public void XuatHoaDon(Integer idhd) {
		try {
			Hashtable map = new Hashtable();

			JasperReport Report = JasperCompileManager.compileReport("src\\frm\\HoaDon.jrxml");

			map.put("MaHD", idhd);

			JasperPrint p = JasperFillManager.fillReport(Report, map, cn.getCon());

			JasperViewer.viewReport(p, false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public frmBanHang() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				Loadcombobox();
				showMoneyGetdate();
				LoadCbbKH();
				AutoCompleteDecorator.decorate(cbbKH);
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtTenNhanVien.setText(TenNV);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				if(Double.parseDouble(txtTongTien.getText()) > 0) {
					if(JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
						dispose();
					}
				}
				else {
					setVisible(false);
					MenuBar frm = new MenuBar();
					frm.setVisible(true);
				}
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1268, 750);
		this.setResizable(false);
		Toolkit tool = getToolkit();
		Dimension size = tool.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setBounds(0, 0, 987, 505);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnThem = new JButton("Thêm Món");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMaHD.getText().isEmpty()) {
					if (IDKH == null) {
						HDDAO.Save(IDNV, IDKH);
					} else {
						HDDAO.Save(IDNV, IDKH);
					}
				}
				HoaDon hd = HDDAO.GetBillMaxID();
				IDHD = hd.getIdhd();
				txtMaHD.setText(IDHD.toString());
				CTHDModel ctModel = new CTHDModel();
				ctModel.setIDSP(IDSP);
				ctModel.setIDHD(IDHD);
				ctModel.setThanhTien(Double.parseDouble(txtTongTien.getText()));
				ctModel.setSoluong(Integer.parseInt(txtSoLuong.getText()));
				Integer GiamGia = 0;
				if (Levellayluon == 1) {
					GiamGia = 5;
				} else if (Levellayluon == 2) {
					GiamGia = 10;
				} else if (Levellayluon == 3) {
					GiamGia = 20;
				} else if (Levellayluon == null) {
					GiamGia = 0;
				}
				ctModel.setGiamGia(GiamGia);
				ctModel.setIDKH(IDKH);
				CTHDModel check = HDDAO.findCTHD(IDHD, IDSP);
				if(check == null)
				{
					HDDAO.SaveCTHD(ctModel);
				}
				else {
					double TongTienSua = Double.parseDouble(txtTongTien.getText());
					TongTienSua -= (double) (Integer.parseInt(txtSoLuong.getText())
							* Integer.parseInt(txtGiaBan.getText()));
					txtTongTien.setText(String.valueOf(TongTienSua));
					ctModel.setIDSP(check.getIDSP());
					ctModel.setSoluong(Integer.parseInt(txtSoLuong.getText()) + check.getSoluong());
					HDDAO.UpdateCTHD(ctModel);
					TongTienSua += (double) (ctModel.getSoluong()
							* Integer.parseInt(txtGiaBan.getText()));
					txtTongTien.setText(String.valueOf(TongTienSua));
					ctModel.setThanhTien(Double.parseDouble(txtTongTien.getText()));
					HDDAO.UpdateHD(ctModel);
					
				}
				ShowCTHD(IDHD);
			}
		});
		btnThem.setForeground(Color.RED);
		btnThem.setBackground(Color.WHITE);
		btnThem.setFont(new Font("Arial", btnThem.getFont().getStyle(), btnThem.getFont().getSize() + 8));
		btnThem.setBounds(430, 0, 132, 42);
		panel.add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double TongTienSua = Double.parseDouble(txtTongTien.getText());
				TongTienSua -= (double) (Integer.parseInt(txtSoLuong.getText())
						* Integer.parseInt(txtGiaBan.getText()));
				txtTongTien.setText(String.valueOf(TongTienSua));
				CTHDModel ctModel = new CTHDModel();
				ctModel.setIDSP(IDSP);
				HDDAO.Delete(ctModel);
				ShowCTHD(IDHD);
			}
		});
		btnXoa.setForeground(Color.RED);
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", btnXoa.getFont().getStyle(), btnXoa.getFont().getSize() + 8));
		btnXoa.setBounds(590, 0, 152, 42);
		panel.add(btnXoa);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double TongTienSua = Double.parseDouble(txtTongTien.getText());
				TongTienSua -= (double) (Integer.parseInt(txtSoLuong.getText())
						* Integer.parseInt(txtGiaBan.getText()));
				txtTongTien.setText(String.valueOf(TongTienSua));
				CTHDModel ctModel = new CTHDModel();
				ctModel.setIDSP(IDSP);
				ctModel.setIDHD(IDHD);
				ctModel.setSoluong(Integer.parseInt(txtSoLuong.getText()));
				HDDAO.UpdateCTHD(ctModel);
				TongTienSua += (double) (Integer.parseInt(txtSoLuong.getText())
						* Integer.parseInt(txtGiaBan.getText()));
				txtTongTien.setText(String.valueOf(TongTienSua));
				ShowCTHD(IDHD);
			}
		});
		btnSua.setForeground(Color.RED);
		btnSua.setBackground(Color.WHITE);
		btnSua.setFont(new Font("Arial", btnSua.getFont().getStyle(), btnSua.getFont().getSize() + 8));
		btnSua.setBounds(430, 61, 132, 42);
		panel.add(btnSua);

		JButton btnClear = new JButton("Thanh Toán");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (IDHD == null) {
					JOptionPane.showMessageDialog(rootPane, "Hiện Tại Không Có Hóa Đơn ", "Thông báo",
							JOptionPane.DEFAULT_OPTION);
				} else {
					CTHDModel ctModel = new CTHDModel();
					ctModel.setIDHD(IDHD);
					Integer GiamGia = 0;
					if (Levellayluon == 1) {
						GiamGia = 5;
					} else if (Levellayluon == 2) {
						GiamGia = 10;
					} else if (Levellayluon == 3) {
						GiamGia = 20;
					} else if (Levellayluon == null) {
						GiamGia = 0;
					}
					ctModel.setThanhTien(Double.parseDouble(txtTongTien.getText()));
					ctModel.setGiamGia(GiamGia);
					ctModel.setIDKH(IDKH);
					HDDAO.UpdateHD(ctModel);
					ShowCTHD(IDHD);
					XuatHoaDon(IDHD);
					showMoneyGetdate();
					Clear();
				}
			}
		});
		btnClear.setForeground(Color.RED);
		btnClear.setBackground(Color.WHITE);
		btnClear.setFont(new Font("Arial", btnClear.getFont().getStyle(), btnClear.getFont().getSize() + 8));
		btnClear.setBounds(590, 61, 152, 42);
		panel.add(btnClear);

		JLabel lblNewLabel_1 = new JLabel("Gi\u00E1 B\u00E1n");
		lblNewLabel_1
				.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 8));
		lblNewLabel_1.setBounds(856, 105, 119, 20);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_12 = new JLabel("S\u1ED1 L\u01B0\u1EE3ng");
		lblNewLabel_12.setFont(
				new Font("Arial", lblNewLabel_12.getFont().getStyle(), lblNewLabel_12.getFont().getSize() + 8));
		lblNewLabel_12.setBounds(856, 155, 158, 20);
		panel.add(lblNewLabel_12);

		txtTongTien = new JTextField();
		txtTongTien.setText("0");
		txtTongTien.setForeground(Color.RED);
		txtTongTien.setEnabled(false);
		txtTongTien.setFont(new Font("Arial", txtTongTien.getFont().getStyle(), txtTongTien.getFont().getSize() + 8));
		txtTongTien.setBounds(1024, 659, 220, 29);
		panel.add(txtTongTien);

		JLabel lblNewLabel_1_1 = new JLabel("T\u1ED5ng");
		lblNewLabel_1_1.setFont(
				new Font("Arial", lblNewLabel_1_1.getFont().getStyle(), lblNewLabel_1_1.getFont().getSize() + 8));
		lblNewLabel_1_1.setBounds(932, 660, 82, 26);
		panel.add(lblNewLabel_1_1);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setEditable(false);
		txtTenSanPham
				.setFont(new Font("Arial", txtTenSanPham.getFont().getStyle(), txtTenSanPham.getFont().getSize() + 8));
		txtTenSanPham.setBounds(1024, 60, 220, 26);
		panel.add(txtTenSanPham);

		JLabel lblNewLabel_1_2 = new JLabel("T\u00EAn S\u1EA3n Ph\u1EA9m");
		lblNewLabel_1_2.setFont(
				new Font("Arial", lblNewLabel_1_2.getFont().getStyle(), lblNewLabel_1_2.getFont().getSize() + 8));
		lblNewLabel_1_2.setBounds(856, 60, 158, 20);
		panel.add(lblNewLabel_1_2);

		txtGiaBan = new JTextField();
		txtGiaBan.setEditable(false);
		txtGiaBan.setFont(new Font("Arial", txtGiaBan.getFont().getStyle(), txtGiaBan.getFont().getSize() + 8));
		txtGiaBan.setBounds(1024, 105, 220, 26);
		panel.add(txtGiaBan);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Arial", txtSoLuong.getFont().getStyle(), txtSoLuong.getFont().getSize() + 8));
		txtSoLuong.setBounds(1024, 155, 220, 26);
		panel.add(txtSoLuong);

		JLabel lblNewLabel_1_23 = new JLabel("Lo\u1EA1i S\u1EA3n Ph\u1EA9m");
		lblNewLabel_1_23.setFont(
				new Font("Arial", lblNewLabel_1_23.getFont().getStyle(), lblNewLabel_1_23.getFont().getSize() + 8));
		lblNewLabel_1_23.setBounds(11, 102, 158, 20);
		panel.add(lblNewLabel_1_23);

		cbbLoaiSP = new JComboBox();
		cbbLoaiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String Name = (String) cbbLoaiSP.getSelectedItem();

					FilterNameSanPhamByCategory(Name);
				} catch (Exception ex) {

				}
			}

		});
		cbbLoaiSP.setFont(new Font("Arial", cbbLoaiSP.getFont().getStyle(), cbbLoaiSP.getFont().getSize() + 8));
		cbbLoaiSP.setBounds(201, 99, 220, 26);
		panel.add(cbbLoaiSP);

		JLabel lblNewLabel_12_1 = new JLabel("T\u00ECm S\u1EA3n Ph\u1EA9m");
		lblNewLabel_12_1.setFont(
				new Font("Arial", lblNewLabel_12_1.getFont().getStyle(), lblNewLabel_12_1.getFont().getSize() + 8));
		lblNewLabel_12_1.setBounds(11, 152, 158, 20);
		panel.add(lblNewLabel_12_1);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String NameCate = (String) cbbLoaiSP.getSelectedItem();

				String Name = txtSearch.getText();

				FilterNameSanPhamByCategory(Name, NameCate);
			}
		});

		txtSearch.setFont(new Font("Arial", txtSearch.getFont().getStyle(), txtSearch.getFont().getSize() + 8));
		txtSearch.setBounds(200, 149, 220, 26);
		panel.add(txtSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 266, 466);
		panel.add(scrollPane);

		tableSanPham = new JTable();
		tableSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableSanPham.getSelectedRow();
				TableModel model = tableSanPham.getModel();
				String TenSp = model.getValueAt(index, 0).toString();

				SanPham sp = SanPhamDAO.getSanPhamByName(TenSp);
				IDSP = sp.getIdsp();
				txtTenSanPham.setText(sp.getTenSp());
				txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
				lblHinh.setIcon(ResizeImage(String.valueOf(sp.getHinh())));

			}
		});
		tableSanPham.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00EAn S\u1EA3n Ph\u1EA9m" }));
		tableSanPham.setFont(new Font("Arial", tableSanPham.getFont().getStyle(), tableSanPham.getFont().getSize()));
		scrollPane.setViewportView(tableSanPham);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(697, 191, 547, 458);
		panel.add(scrollPane_1);

		tableCTHD = new JTable();
		tableCTHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableCTHD.getSelectedRow();
				TableModel model = tableCTHD.getModel();
				int SoLuong = Integer.parseInt(model.getValueAt(index, 1).toString());
				String TenSP = (String) model.getValueAt(index, 0);
				SanPham sp = SanPhamDAO.getSanPhamByName(TenSP);
				lblHinh.setIcon(ResizeImage(String.valueOf(sp.getHinh())));
				int GiaBan = Integer.parseInt(model.getValueAt(index, 2).toString());
				txtSoLuong.setText(String.valueOf(SoLuong));
				txtTenSanPham.setText(TenSP);
				txtGiaBan.setText(String.valueOf(GiaBan));
				lblHinh.setIcon(ResizeImage(String.valueOf(sp.getHinh())));
				IDSP = sp.getIdsp();
			}
		});
		tableCTHD.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00EAn S\u1EA3n Ph\u1EA9m",
				"S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1", "Th\u00E0nh Ti\u1EC1n" }));
		tableCTHD.getColumnModel().getColumn(0).setPreferredWidth(98);
		tableCTHD.setFont(new Font("Arial", Font.PLAIN, 10));
		scrollPane_1.setViewportView(tableCTHD);

		JLabel lblNewLabel_1_23_1 = new JLabel("Mã Hóa Đơn");
		lblNewLabel_1_23_1.setFont(
				new Font("Arial", lblNewLabel_1_23_1.getFont().getStyle(), lblNewLabel_1_23_1.getFont().getSize() + 8));
		lblNewLabel_1_23_1.setBounds(10, 19, 125, 20);
		panel.add(lblNewLabel_1_23_1);

		txtMaHD = new JTextField();
		txtMaHD.setEditable(false);
		txtMaHD.setFont(new Font("Arial", txtMaHD.getFont().getStyle(), txtMaHD.getFont().getSize() + 8));
		txtMaHD.setBounds(200, 16, 220, 26);
		panel.add(txtMaHD);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setEditable(false);
		txtTenNhanVien.setFont(
				new Font("Arial", txtTenNhanVien.getFont().getStyle(), txtTenNhanVien.getFont().getSize() + 8));
		txtTenNhanVien.setBounds(200, 60, 220, 26);
		panel.add(txtTenNhanVien);

		JLabel lblNewLabel_12_1_1 = new JLabel("Tên Nhân Viên");
		lblNewLabel_12_1_1.setFont(
				new Font("Arial", lblNewLabel_12_1_1.getFont().getStyle(), lblNewLabel_12_1_1.getFont().getSize() + 8));
		lblNewLabel_12_1_1.setBounds(11, 63, 158, 20);
		panel.add(lblNewLabel_12_1_1);

		lblHinh = new JLabel("");
		lblHinh.setBounds(286, 199, 367, 458);
		panel.add(lblHinh);

		JLabel lblNewLabel_1_23_2 = new JLabel("Khách Hàng");
		lblNewLabel_1_23_2.setFont(new Font("Arial", lblNewLabel_1_23_2.getFont().getStyle(),
				lblNewLabel_1_23_2.getFont().getSize() + 10));
		lblNewLabel_1_23_2.setBounds(853, 19, 161, 20);
		panel.add(lblNewLabel_1_23_2);

		cbbKH = new JComboBox();
		cbbKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					SDT = (String) cbbKH.getSelectedItem();
					String a = "";
					if (SDT.startsWith("0")) {
						a = SDT.substring(1);
					}
					Levellayluon = 0;
					IDKH = null;

					KhachHang kh = new KhachHangDAO().FindbySDT(a);
					if (kh == null) {
						
					} else {

						Levellayluon = Integer.parseInt(kh.getLevel());
						IDKH = kh.getIdkh();
						if (Levellayluon == 1) {
							TongTien -= (TongTien / 20);
						} else if (Levellayluon == 2) {
							TongTien -= (TongTien / 10);
						} else if (Levellayluon == 3) {
							TongTien -= (TongTien / 5);
						} 
					}
					ShowCTHD(IDHD);
					
				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
		});
		cbbKH.setEditable(true);
		cbbKH.setFont(new Font("Arial", cbbKH.getFont().getStyle(), cbbKH.getFont().getSize() + 7));
		cbbKH.setBounds(1024, 16, 220, 26);
		panel.add(cbbKH);

		txtTongTienDay = new JTextField();
		txtTongTienDay.setFont(
				new Font("Arial", txtTongTienDay.getFont().getStyle(), txtTongTienDay.getFont().getSize() + 10));
		txtTongTienDay.setEditable(false);
		txtTongTienDay.setBounds(686, 663, 220, 26);
		panel.add(txtTongTienDay);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tổng Doanh Thu Trong Ngày");
		lblNewLabel_1_1_1.setFont(
				new Font("Arial", lblNewLabel_1_1_1.getFont().getStyle(), lblNewLabel_1_1_1.getFont().getSize() + 10));
		lblNewLabel_1_1_1.setBounds(359, 659, 317, 26);
		panel.add(lblNewLabel_1_1_1);
	}
}
