package frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.CTHDModel;
import DAO.LoaiSanPhamDAO;
import DAO.TaiKhoanDAO;
import entities.LoaiSp;
import entities.NhanVien;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmNhanVien extends JFrame {

	private JPanel contentPane;
	private JPanel ao;
	private JTable table;
	private JTextField txtTenNV;
	private JTextField txtEmail;
	private Integer IDNV = frmLogin.IDNV;
	/**
	 * Create the panel.
	 */
	private TaiKhoanDAO TK = new TaiKhoanDAO();

	public void ShowThongTin() {
		NhanVien a = TK.findbyID(IDNV);
		txtTenNV.setText(a.getTenNv());
		txtEmail.setText(a.getEmail());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNhanVien frame = new frmNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void ShowList() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		ArrayList<NhanVien> ct = new TaiKhoanDAO().getAll();
		Iterator<NhanVien> itr = ct.iterator();
		while (itr.hasNext()) {
			NhanVien pro = itr.next();
			dtm.addRow(new Object[] { pro.getIdnv(), pro.getTenNv(), pro.getEmail(), pro.getQuyen() });
		}
	}

	/**
	 * Create the frame.
	 */
	public frmNhanVien() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				TaiKhoanDAO DAO = new TaiKhoanDAO();
				NhanVien nv = DAO.findbyID(frmLogin.IDNV);
				ShowList();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				MenuBar frm = new MenuBar();
				frm.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 888, 636);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 307, 874, 292);
		contentPane.add(scrollPane);
		JButton btnSua = new JButton("Xóa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaiKhoanDAO DAO = new TaiKhoanDAO();
				try {
					NhanVien nv = DAO.findbyID(frmLogin.IDNV);
					if(nv.getTenNv().equals(txtTenNV.getText())) {
						JOptionPane.showMessageDialog(rootPane, "Tài Khoản Đang đc đăng nhập không thể xóa ", "Thông báo", JOptionPane.DEFAULT_OPTION);
					}
					else {
						if(DAO.DeleteNV(txtTenNV.getText())) {
							JOptionPane.showMessageDialog(rootPane, "Xóa Thành Công", "Thông báo", JOptionPane.DEFAULT_OPTION);
							txtTenNV.setText("");
							txtEmail.setText("");
							ShowList();
						}
						else {
							JOptionPane.showMessageDialog(rootPane, "Vì Nhân Viên Đã Thanh toán 1 trong các hóa đơn ", "Thông báo", JOptionPane.DEFAULT_OPTION);
						}
					}
				}
				catch (Exception exx) {
				}
				
			}
		});
		btnSua.setForeground(Color.RED);
		btnSua.setBackground(Color.WHITE);
		btnSua.setFont(new Font("Arial", btnSua.getFont().getStyle(), btnSua.getFont().getSize() + 8));
		btnSua.setBounds(314, 225, 146, 42);
		contentPane.add(btnSua);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTenNV.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				txtEmail.setText(table.getValueAt(table.getSelectedRow(),2).toString());
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 NV", "T\u00EAn Nh\u00E2n Vi\u00EAn", "Email", "Quy\u1EC1n" }));
		scrollPane.setViewportView(table);
		JLabel lblNewLabel = new JLabel("T\u00EAn Nh\u00E2n Vi\u00EAn");
		lblNewLabel.setFont(new Font("Arial", lblNewLabel.getFont().getStyle(), lblNewLabel.getFont().getSize() + 10));
		lblNewLabel.setBounds(140, 55, 171, 37);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1
				.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 10));
		lblNewLabel_1.setBounds(140, 126, 171, 37);
		contentPane.add(lblNewLabel_1);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Arial", txtTenNV.getFont().getStyle(), txtTenNV.getFont().getSize() + 10));
		txtTenNV.setBounds(365, 55, 186, 27);
		contentPane.add(txtTenNV);
		txtTenNV.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", txtEmail.getFont().getStyle(), txtEmail.getFont().getSize() + 10));
		txtEmail.setColumns(10);
		txtEmail.setBounds(365, 126, 186, 31);
		contentPane.add(txtEmail);
	}
}
