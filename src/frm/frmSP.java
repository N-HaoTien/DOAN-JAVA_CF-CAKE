package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.util.DirectionalGradientPaintTransformer;

import DAO.LoaiSanPhamDAO;
import DAO.SANPHAMModel;
import DAO.SanPhamDAO;
import entities.LoaiSp;
import entities.SanPham;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class frmSP extends JFrame {

	private JPanel contentPane;
	private JTextField txtGiaBan;
	private JTextField txtTenSanPham;
	private JComboBox cbbLoaiSP;
	private DefaultTableModel dtm;
	private JTable table;
	private SanPhamDAO DAO = new SanPhamDAO();
	private JLabel lblHinhAnh;
	public static String GetPath = null;
	public static Integer IDLOAISP ;
	public static String TenLOAISP ;
	public static String GetHinh = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSP frame = new frmSP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ImageIcon ResizeImage(String Path) {
		ImageIcon a = new ImageIcon(Path);
		Image img = a.getImage();
		Image newImg = img.getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);

		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	public void Clear() {
		txtTenSanPham.setText("");
		txtGiaBan.setText("");
		GetPath = "";
		lblHinhAnh.setIcon(ResizeImage(""));
	}

	private void LoadData() {

		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Sản Phẩm");
		dtm.addColumn("Tên Sản Phẩm");
		dtm.addColumn("Giá");
		dtm.addColumn("Ảnh");
		dtm.addColumn("Loại Sản Phẩm");
		for (SanPham sp : DAO.findAll()) {
			dtm.addRow(new Object[] { sp.getIdsp(), sp.getTenSp(), sp.getGiaBan(), sp.getHinh(),
					sp.getLoaiSp().getTenLoaiSp() });
		}
		this.table.setModel(dtm);
	}

	/**
	 * Create the frame.
	 */
	public frmSP() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1006, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				LoadData();
				BindJTable();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				MenuBar frm = new MenuBar();
				frm.setVisible(true);
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setBounds(0, 0, 987, 505);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtTenSanPham.getText().isEmpty() && txtGiaBan.getText().isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Mời bạn điền đầy đủ thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
					}
					else {
						LoaiSp loaisp = new LoaiSanPhamDAO().getById(IDLOAISP);
						SanPham sp = new SanPham();
						sp.setTenSp(txtTenSanPham.getText());
						sp.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
						sp.setLoaiSp(loaisp);
						sp.setHinh(GetPath);
						if (DAO.SaveorUpdate(sp)) {
							JOptionPane.showMessageDialog(rootPane, "Thêm Mới Thành Công", "Thông báo", JOptionPane.DEFAULT_OPTION);
							LoadData();
							Clear();
						} else {
							JOptionPane.showMessageDialog(rootPane, "Tên Sản Phẩm Đã Tồn Tại ", "Thông báo", JOptionPane.DEFAULT_OPTION);
						}
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(rootPane, ex.toString(), "Thông báo", JOptionPane.DEFAULT_OPTION);
				}
			}

		});
		btnThem.setForeground(Color.RED);
		btnThem.setBackground(Color.WHITE);
		btnThem.setFont(new Font("Arial", btnThem.getFont().getStyle(), btnThem.getFont().getSize() + 8));
		btnThem.setBounds(151, 254, 146, 42);
		panel.add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					SanPham sp = new SanPham();
					sp.setIdsp(id);
					if(DAO.delete(sp) == true) {
						JOptionPane.showMessageDialog(rootPane, "Xóa sản phẩm thành công !", "Thông báo", JOptionPane.DEFAULT_OPTION);

					}
					else
					{
						JOptionPane.showMessageDialog(rootPane, "Xóa Thất Bại Vì Sản Phẩm Đã Nằm Trong Hóa Đơn", "Thông báo", JOptionPane.DEFAULT_OPTION);
					}
					LoadData();
					Clear();
				}
				catch (Exception ex){
					JOptionPane.showMessageDialog(rootPane, "Xóa Thất Bại Vì Đã Nằm Trong Hóa Đơn", "Thông báo", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		btnXoa.setForeground(Color.RED);
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", btnXoa.getFont().getStyle(), btnXoa.getFont().getSize() + 8));
		btnXoa.setBounds(360, 254, 146, 42);
		panel.add(btnXoa);
		JButton btnChonAnh = new JButton("Chọn");
		btnChonAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser f = new JFileChooser("D:\\C#\\DoAn-CNPM\\DoAn_Java\\AnhCuaJava");
					f.setDialogTitle("Mở File");
					f.showOpenDialog(null);
					File tenAnh = f.getSelectedFile();
					String filePath = tenAnh.getAbsolutePath();
					if (filePath != null) {
						lblHinhAnh.setIcon(ResizeImage(String.valueOf(filePath)));
					}
					// Lưu Trữ vào Ảnh
					String newPath = "D:\\C\\ElteeStore\\src\\Anh";
					File Dicrection = new File(newPath);
					if (!Dicrection.exists()) {
						Dicrection.mkdir();
					}
					File Source = null;
					File Destin = null;
					// Get Đuôi
					// tenAnh.getName() lấy tên file
					String ex = filePath.substring(filePath.lastIndexOf('.') + 1);
					GetPath = newPath + "/" + tenAnh.getName() + ".";
					Source = new File(filePath);
					Destin = new File(GetPath);
					Files.copy(Source.toPath(), Destin.toPath());
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
		});
		btnChonAnh.setForeground(Color.RED);
		btnChonAnh.setBackground(Color.WHITE);
		btnChonAnh.setFont(new Font("Arial", btnChonAnh.getFont().getStyle(), btnChonAnh.getFont().getSize() + 8));
		btnChonAnh.setBounds(847, 109, 125, 42);
		panel.add(btnChonAnh);

		JButton btnSua = new JButton("Edit");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTenSanPham.getText().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Mời Bạn chọn sản phẩm cần sửa", "Thông báo", JOptionPane.DEFAULT_OPTION);
				}
				else {
					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					SANPHAMModel sp = new SANPHAMModel();
					sp.setIdsp(id);
					sp.setTenSp(txtTenSanPham.getText());
					sp.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
					if(GetPath.isEmpty()) {
						sp.setHinh(table.getValueAt(table.getSelectedRow(), 3).toString());
					}
					else {
						sp.setHinh(GetPath);
					}
					sp.setIDloaiSp(IDLOAISP);
					if(DAO.Update(sp) == true) {
						JOptionPane.showMessageDialog(rootPane, "Sửa Thành Công", "Thông báo", JOptionPane.DEFAULT_OPTION);
						LoadData();
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "Sửa Thất Bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
						Clear();
					}
				}
				
			}
		});
		btnSua.setForeground(Color.RED);
		btnSua.setBackground(Color.WHITE);
		btnSua.setFont(new Font("Arial", btnSua.getFont().getStyle(), btnSua.getFont().getSize() + 8));
		btnSua.setBounds(547, 254, 146, 42);
		panel.add(btnSua);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnClear.setForeground(Color.RED);
		btnClear.setBackground(Color.WHITE);
		btnClear.setFont(new Font("Arial", btnClear.getFont().getStyle(), btnClear.getFont().getSize() + 8));
		btnClear.setBounds(747, 254, 146, 42);
		panel.add(btnClear);

		JLabel lblNewLabel_1 = new JLabel("Gi\u00E1 B\u00E1n");
		lblNewLabel_1
				.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 8));
		lblNewLabel_1.setBounds(49, 114, 155, 20);
		panel.add(lblNewLabel_1);

		txtGiaBan = new JTextField();
		txtGiaBan.setFont(new Font("Arial", txtGiaBan.getFont().getStyle(), txtGiaBan.getFont().getSize() + 8));
		txtGiaBan.setBounds(250, 109, 170, 26);
		panel.add(txtGiaBan);

		JLabel lblNewLabel_1_2 = new JLabel("T\u00EAn S\u1EA3n Ph\u1EA9m");
		lblNewLabel_1_2.setFont(
				new Font("Arial", lblNewLabel_1_2.getFont().getStyle(), lblNewLabel_1_2.getFont().getSize() + 8));
		lblNewLabel_1_2.setBounds(49, 39, 155, 34);
		panel.add(lblNewLabel_1_2);

		txtTenSanPham = new JTextField();
		txtTenSanPham
				.setFont(new Font("Arial", txtTenSanPham.getFont().getStyle(), txtTenSanPham.getFont().getSize() + 8));
		txtTenSanPham.setBounds(250, 39, 170, 37);
		panel.add(txtTenSanPham);

		JLabel lblNewLabel_1_23 = new JLabel("Lo\u1EA1i S\u1EA3n Ph\u1EA9m");
		lblNewLabel_1_23.setFont(
				new Font("Arial", lblNewLabel_1_23.getFont().getStyle(), lblNewLabel_1_23.getFont().getSize() + 8));
		lblNewLabel_1_23.setBounds(49, 184, 155, 20);
		panel.add(lblNewLabel_1_23);

		cbbLoaiSP = new JComboBox();
		cbbLoaiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TenLOAISP = (String) cbbLoaiSP.getSelectedItem();
				LoaiSp loai = new LoaiSanPhamDAO().getAllCategory(TenLOAISP);
				IDLOAISP = loai.getIdloaiSp();
			}
		});
		cbbLoaiSP.setFont(new Font("Arial", cbbLoaiSP.getFont().getStyle(), cbbLoaiSP.getFont().getSize() + 8));
		cbbLoaiSP.setBounds(250, 181, 170, 26);
		panel.add(cbbLoaiSP);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setBounds(460, 39, 377, 205);
		panel.add(lblHinhAnh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 371, 982, 250);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				SanPham sp = DAO.find(id);
				txtTenSanPham.setText(sp.getTenSp());
				lblHinhAnh.setIcon(ResizeImage(String.valueOf(sp.getHinh())));
				txtGiaBan.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				cbbLoaiSP.setSelectedItem(sp.getLoaiSp().getTenLoaiSp());
				GetPath = sp.getHinh();
			}
		});
		scrollPane.setViewportView(table);
	}

	public void BindJTable() { // TODO Auto-generated method stub

		List<LoaiSp> listSP = new LoaiSanPhamDAO().findAll();
		ArrayList<LoaiSp> list = new LoaiSanPhamDAO().getAllCategory();
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		//cbbLoaiSP.setModel(model);
		for(LoaiSp loai : list) { cbbLoaiSP.addItem(loai.getTenLoaiSp()); }
		/*
		  for(LoaiSp loai : list) { cbbLoaiSP.addItem(model); }
		  cbbLoaiSP.setModel(model);
		 */
	}
}
