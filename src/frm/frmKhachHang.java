package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.CTHDModel;
import DAO.KhachHangDAO;
import DAO.SanPhamDAO;
import entities.KhachHang;
import entities.SanPham;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmKhachHang extends JFrame {

	private JPanel contentPane;
	private JTextField txtLevel;
	private JTextField txtDiaChi;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTable table;
	public static String TenKH ="";
	public static String SDT = "";
	
	public KhachHangDAO DAO = new KhachHangDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmKhachHang frame = new frmKhachHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void ShowKH() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<KhachHang> ct = new KhachHangDAO().getAll();
		Iterator<KhachHang> itr = ct.iterator();
		double tam = 0;
		while (itr.hasNext()) {
			KhachHang pro = itr.next();
			dtm.addRow(new Object[] { pro.getTenKh(),pro.getDiaChi(),"0"+ pro.getSdt(),pro.getLevel()});
		}
	}
	/**
	 * Create the frame.
	 */
	public frmKhachHang() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				ShowKH();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				MenuBar frm = new MenuBar();
				frm.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1006, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setBounds(0, 0, 987, 505);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnThem = new JButton("Create");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					KhachHang kh = new KhachHang();
					
					kh.setTenKh(txtTenKH.getText());
					kh.setDiaChi(txtDiaChi.getText());
					kh.setSdt(Integer.parseInt(String.valueOf(txtSDT.getText())));
					kh.setLevel(txtLevel.getText());
					
					if(DAO.Save(kh) == false) {
						JOptionPane.showMessageDialog(null, "Đã Trùng Số Điện Thoại");
					}
					else {
						JOptionPane.showMessageDialog(null, "Thêm Thành Công");
						ShowKH();
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnThem.setForeground(Color.RED);
		btnThem.setBackground(Color.WHITE);
		btnThem.setFont(new Font("Arial", btnThem.getFont().getStyle(), btnThem.getFont().getSize() + 8));
		btnThem.setBounds(145, 255, 146, 42);
		panel.add(btnThem);
		
		JButton btnXoa = new JButton("Delete");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(SDT.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Người Cần Xóa");
				}
				else {
					DAO.delete(SDT);
					ShowKH();
				}
				
			}
		});
		btnXoa.setForeground(Color.RED);
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", btnXoa.getFont().getStyle(), btnXoa.getFont().getSize() + 8));
		btnXoa.setBounds(338, 255, 146, 42);
		panel.add(btnXoa);
		
		JButton btnSua = new JButton("Edit");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = new KhachHang();
				kh.setTenKh(txtTenKH.getText());
				kh.setDiaChi(txtDiaChi.getText());
				kh.setSdt(Integer.parseInt(String.valueOf(txtSDT.getText())));
				kh.setLevel(txtLevel.getText());
				if(DAO.Update(kh,TenKH) == false) {
					JOptionPane.showMessageDialog(null, "Đã Trùng Số Điện Thoại");
				}
				else {
					JOptionPane.showMessageDialog(null, "Sửa Thành Công");
				}
				ShowKH();
			}
		});
		btnSua.setForeground(Color.RED);
		btnSua.setBackground(Color.WHITE);
		btnSua.setFont(new Font("Arial", btnSua.getFont().getStyle(), btnSua.getFont().getSize() + 8));
		btnSua.setBounds(541, 255, 146, 42);
		panel.add(btnSua);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnClear.setForeground(Color.RED);
		btnClear.setBackground(Color.WHITE);
		btnClear.setFont(new Font("Arial", btnClear.getFont().getStyle(), btnClear.getFont().getSize() + 8));
		btnClear.setBounds(741, 255, 146, 42);
		panel.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("\u0110\u1ECBa Ch\u1EC9");
		lblNewLabel_1.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 8));
		lblNewLabel_1.setBounds(264, 96, 75, 20);
		panel.add(lblNewLabel_1);
		
		txtLevel = new JTextField();
		txtLevel.setFont(new Font("Arial", txtLevel.getFont().getStyle(), txtLevel.getFont().getSize() + 8));
		txtLevel.setBounds(454, 207, 220, 26);
		panel.add(txtLevel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Level");
		lblNewLabel_1_1.setFont(new Font("Arial", lblNewLabel_1_1.getFont().getStyle(), lblNewLabel_1_1.getFont().getSize() + 8));
		lblNewLabel_1_1.setBounds(264, 210, 125, 20);
		panel.add(lblNewLabel_1_1);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", txtDiaChi.getFont().getStyle(), txtDiaChi.getFont().getSize() + 8));
		txtDiaChi.setBounds(454, 93, 220, 26);
		panel.add(txtDiaChi);
		
		JLabel lblNewLabel_1_2 = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng");
		lblNewLabel_1_2.setFont(new Font("Arial", lblNewLabel_1_2.getFont().getStyle(), lblNewLabel_1_2.getFont().getSize() + 8));
		lblNewLabel_1_2.setBounds(264, 42, 163, 20);
		panel.add(lblNewLabel_1_2);
		
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Arial", txtTenKH.getFont().getStyle(), txtTenKH.getFont().getSize() + 8));
		txtTenKH.setBounds(454, 39, 220, 26);
		panel.add(txtTenKH);
		
		JLabel lblNewLabel_1_23 = new JLabel("S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i");
		lblNewLabel_1_23.setFont(new Font("Arial", lblNewLabel_1_23.getFont().getStyle(), lblNewLabel_1_23.getFont().getSize() + 8));
		lblNewLabel_1_23.setBounds(264, 152, 180, 20);
		panel.add(lblNewLabel_1_23);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Arial", txtSDT.getFont().getStyle(), txtSDT.getFont().getSize() + 8));
		txtSDT.setBounds(454, 149, 220, 26);
		panel.add(txtSDT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 326, 982, 215);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				TenKH = model.getValueAt(index, 0).toString();
				SDT = model.getValueAt(index, 2).toString();
				KhachHang kh = DAO.FindbyName(TenKH);
				txtTenKH.setText(kh.getTenKh());
				txtSDT.setText(model.getValueAt(index, 2).toString());
				txtLevel.setText(kh.getLevel());
				txtDiaChi.setText(kh.getDiaChi());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T\u00EAn Kh\u00E1ch H\u00E0ng", "\u0110\u1ECBa Ch\u1EC9", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "Level"
			}
		));
		table.setFont(new Font("Arial", table.getFont().getStyle(), table.getFont().getSize()));
		scrollPane.setViewportView(table);
	}
}
