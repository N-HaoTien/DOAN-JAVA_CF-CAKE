package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.SessionFactory;

import DAO.ConnectionProvider;
import DAO.DbOperations;
import DAO.HibUtil;
import DAO.LoaiSanPhamDAO;
import DAO.SanPhamDAO;
import entities.LoaiSp;
import entities.SanPham;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class frmLoaiSP extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenLoaiSP;
	private JTable table;
	private DefaultTableModel dtm;
	private LoaiSanPhamDAO DAO = new LoaiSanPhamDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLoaiSP frame = new frmLoaiSP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void LoadData() {

		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Loại Sản Phẩm");
		dtm.addColumn("Tên Loại Sản Phẩm");
		for (LoaiSp sp : DAO.findAll()) {
			dtm.addRow(new Object[] { sp.getIdloaiSp(), sp.getTenLoaiSp() });
		}
		this.table.setModel(dtm);
	}

	private void Clear() {
		txtTenLoaiSP.setText("");
	}

	/**
	 * Create the frame.
	 */
	public frmLoaiSP() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				LoadData();
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
				LoaiSanPhamDAO loai = new LoaiSanPhamDAO();
				LoaiSp loaisp = new LoaiSp();
				loaisp.setTenLoaiSp(txtTenLoaiSP.getText());
				if (loai.Save(loaisp)) {
					JOptionPane.showMessageDialog(rootPane, "Thêm Thành Công", "Thông báo", JOptionPane.DEFAULT_OPTION);
					LoadData();
					Clear();
				} else {
					JOptionPane.showMessageDialog(rootPane, "Đã Tồn Tại Tên Loại", "Thông báo", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		btnThem.setForeground(Color.RED);
		btnThem.setBackground(Color.WHITE);
		btnThem.setFont(new Font("Arial", btnThem.getFont().getStyle(), btnThem.getFont().getSize() + 8));
		btnThem.setBounds(140, 211, 146, 42);
		panel.add(btnThem);

		JButton btnXoa = new JButton("Delete");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa không!", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0)
				 {
					if(LoaiSanPhamDAO.delete(txtTenLoaiSP.getText()) == true) {
						LoadData();
						Clear();
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "Xóa Thất Bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
					}
					
				}
			}
		});
		btnXoa.setForeground(Color.RED);
		btnXoa.setBackground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", btnXoa.getFont().getStyle(), btnXoa.getFont().getSize() + 8));
		btnXoa.setBounds(333, 211, 146, 42);
		panel.add(btnXoa);

		JButton btnSua = new JButton("Edit");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				LoaiSp sp = new LoaiSp();
				sp.setIdloaiSp(id);
				sp.setTenLoaiSp(txtTenLoaiSP.getText());
				LoaiSanPhamDAO.Update(sp);
				LoadData();

			}
		});
		btnSua.setForeground(Color.RED);
		btnSua.setBackground(Color.WHITE);
		btnSua.setFont(new Font("Arial", btnSua.getFont().getStyle(), btnSua.getFont().getSize() + 8));
		btnSua.setBounds(536, 211, 146, 42);
		panel.add(btnSua);

		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.RED);
		btnClear.setBackground(Color.WHITE);
		btnClear.setFont(new Font("Arial", btnClear.getFont().getStyle(), btnClear.getFont().getSize() + 8));
		btnClear.setBounds(736, 211, 146, 42);
		panel.add(btnClear);

		JLabel lblNewLabel_1 = new JLabel("Tên Loại SP");
		lblNewLabel_1
				.setFont(new Font("Arial", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 8));
		lblNewLabel_1.setBounds(217, 96, 169, 20);
		panel.add(lblNewLabel_1);

		txtTenLoaiSP = new JTextField();
		txtTenLoaiSP.setFont(new Font("Arial", txtTenLoaiSP.getFont().getStyle(), txtTenLoaiSP.getFont().getSize() + 8));
		txtTenLoaiSP.setBounds(454, 93, 220, 26);
		panel.add(txtTenLoaiSP);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 301, 982, 240);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				LoaiSp sp = DAO.getById(id);
				txtTenLoaiSP.setText(sp.getTenLoaiSp());
			}
		});
		scrollPane.setViewportView(table);
	}
}
